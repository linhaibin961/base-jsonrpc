package com.lhb.base;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lhb.base.global.Configuration;
import com.lhb.common.ErrorCode;
import com.lhb.common.utils.Holder;
import com.lhb.common.utils.pay.VersionUtil;
import com.yzmy.jsonrpc4j.JsonRpcMultiServer;
import com.yzmy.jsonrpc4j.NoCloseOutputStream;
import com.yzmy.jsonrpc4j.ReadContext;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**

/**
 * Created by Linhaibin on 2017/6/23.
 */
public class PatchedJsonRpcMultiServer extends JsonRpcMultiServer {

    private ObjectMapper objectMapper;

    public PatchedJsonRpcMultiServer(ObjectMapper objectMapper) {
        super(objectMapper);
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json-rpc");
        Object input = null;
        ServletOutputStream output = response.getOutputStream();

        if(request.getMethod().equals("POST")) {
            input = request.getInputStream();
        } else {
            if(!request.getMethod().equals("GET")) {
                throw new IOException("Invalid request method, only POST and GET is supported");
            }

            input = createInputStream(request.getParameter("method"), request.getParameter("id"), request.getParameter("params"));
        }


        int result = this.handle((InputStream)input, (OutputStream)output,request);
        /*if(result != 0) {
            if(result == -32700 || result == -32602 || result == -32603 || result <= -32000 && result >= -32099) {
                response.setStatus(500);
            } else if(result == -32600) {
                response.setStatus(400);
            } else if(result == -32601) {
                response.setStatus(404);
            }
        }*/

        output.flush();
    }

    /**
     * 判断是否升级
     */
    private void upgrade(String versionCode,Object id,Holder<ObjectNode> value) throws Exception{
        //处理升级

        if(versionCode == null || "".equals(versionCode)){ //兼容之前的版本 暂时不处理
            return ;
        }

        int appVersion = Integer.parseInt(versionCode);
        int min_version =  Configuration.getInstance().getIntValue("min_version");
        int current_version =  Configuration.getInstance().getIntValue("current_version");

        VersionUtil.addVersion(appVersion);//加入版本号，区别处理支付

        String jsonRpc="2.0";
        if(appVersion<min_version){
            value.setValue(createErrorResponse(jsonRpc, id, ErrorCode.VERSION_TOO_LOW.getCode(), ErrorCode.VERSION_TOO_LOW.getMessage(),null));
            throw  new Exception("");
        }
        if(current_version<appVersion){
            Map<String,Integer> data = new HashMap<>();
            data.put("key",current_version);
            value.setValue(createErrorResponse(jsonRpc, id, ErrorCode.SERVER_UPGRADE.getCode(), ErrorCode.SERVER_UPGRADE.getMessage(),data));
            throw  new Exception("");
        }
    }

    /**
     * 基础数据是否需要升级
     * @param request
     * @return boolean
     */
    private boolean isBasicDataNeedUpgrade(HttpServletRequest request) {
        String basicDataVersionHeader = request.getHeader("basic_data_version");
        if (basicDataVersionHeader == null) {
            return false;
        }
        int requestBasicDataVersion = Integer.parseInt(basicDataVersionHeader);
        int currentBasicDataVersion = Configuration.getInstance().getIntValue("basicDataVersion");
        if (requestBasicDataVersion < currentBasicDataVersion) {
            return true;
        }
        return false;
    }

    /**
     * 获取基础数据下载地址
     * @return
     */
    private ObjectNode getBasicDataUrl() {
        int currentBasicDataVersion = Configuration.getInstance().getIntValue("basicDataVersion");
        String basicDataUrl = Configuration.getInstance().getStringValue("basicDataUrl");
        String districtChecksum = Configuration.getInstance().getStringValue("districtChecksum");
        String industryChecksum = Configuration.getInstance().getStringValue("industryChecksum");
        String positionChecksum = Configuration.getInstance().getStringValue("positionChecksum");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode data = mapper.createObjectNode();
        ObjectNode district = mapper.createObjectNode().put("url", basicDataUrl + districtChecksum + ".zip");
        ObjectNode industry = mapper.createObjectNode().put("url", basicDataUrl + industryChecksum + ".zip");
        ObjectNode position = mapper.createObjectNode().put("url", basicDataUrl + positionChecksum + ".zip");
        data.put("version", currentBasicDataVersion);
        data.set("district", district);
        data.set("industry", industry);
        data.set("position", position);
        return data;
    }

    /**
     *  获取 数据解析node
     * @param value
     * @param mapper
     * @param is
     * @return
     * @throws Exception
     */
    private ObjectNode getJsonNodes(Holder<ObjectNode> value, ObjectMapper mapper, ServletInputStream is) throws Exception {
        ReadContext ctx = ReadContext.getReadContext(is, mapper);
        // prcess
        JsonNode jsonNode;
        try {
            ctx.assertReadable();
            jsonNode = ctx.nextValue();
        } catch (JsonParseException e) {
            value.setValue(createErrorResponse("jsonrpc", "null", -32700, "Parse error", e));
            throw e;
        }

        ObjectNode node = ObjectNode.class.cast(jsonNode);
        if (!node.has("jsonrpc") || !node.has("method")) {
            value.setValue(createErrorResponse("2.0", "null", -32600, "Invalid Request", null));
            throw new Exception("");
        }
        return node;
    }

    /**
     * Parses an ID.
     * @param node
     * @return 处理后的id内容.
     */
    private Object parseId(JsonNode node) {
        if (node == null || node.isNull()) {
            return null;
        } else if (node.isDouble()) {
            return node.asDouble();
        } else if (node.isFloatingPointNumber()) {
            return node.asDouble();
        } else if (node.isInt()) {
            return node.asInt();
        } else if (node.isIntegralNumber()) {
            return node.asInt();
        } else if (node.isLong()) {
            return node.asLong();
        } else if (node.isTextual()) {
            return node.asText();
        }
        throw new IllegalArgumentException("Unknown id type");
    }

    private void writeAndFlushValue(OutputStream ops, Object value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new NoCloseOutputStream(ops), value);
        ops.write(10);
    }

    public int handle(InputStream ips, OutputStream ops,HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ReadContext ctx = ReadContext.getReadContext(ips, mapper);

        JsonNode jsonNode;
        try {
            ctx.assertReadable();
            jsonNode = ctx.nextValue();
        } catch (JsonParseException var6) {
            this.writeAndFlushValue(ops, this.createErrorResponse("jsonrpc", "null", -32700, "Parse error", (Object)null));
            return -32700;
        }
/*
        if(jsonNode.isObject()){
            ObjectNode node = (ObjectNode)ObjectNode.class.cast(jsonNode);
            Object id = this.parseId(node.get("id"));

            Holder<ObjectNode> value  = new Holder<>(null);
            try {
                String versionCode = request.getHeader("version_code");
                upgrade(versionCode,id,value); //处理升级问题
            }catch (Exception e){
                writeAndFlushValue(ops, value.getValue());
                return -32602 ;
            }

            //基础数据升级
            if (isBasicDataNeedUpgrade(request)) {
                ObjectNode data = getBasicDataUrl();
                writeAndFlushValue(ops, createErrorResponse("2.0", id, ErrorCode.BASIC_DATA_EXPIRED.getCode(), data.toString(), null));
                return -32602;
            }

        }*/



        return this.handleNode(jsonNode, ops);
    }

}
