package com.lhb.base;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lhb.common.utils.CertStatus;
import com.yzmy.jsonrpc4j.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Linhaibin on 2017/6/23.
 */
public class LhbJsonRpcMultiServer {
    private PatchedJsonRpcMultiServer jsonRpcMultiServer;

    public LhbJsonRpcMultiServer() {
        // 注册自定义的数据绑定. 之所以不使用注解来做数据绑定, 是由于这种方法能比较自由的使用, 使用
        // 注解就无法变动了.
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(CertStatus.class, new CertStatusSerializer());
        objectMapper.registerModule(module);
        objectMapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        jsonRpcMultiServer = new PatchedJsonRpcMultiServer(objectMapper);

        ErrorResolver xwErrorResolver = new MultipleErrorResolver(
                ModelExceptionResolver.INSTANCE,
                AnnotationsErrorResolver.INSTANCE,
                DefaultErrorResolver.INSTANCE
        );

        jsonRpcMultiServer.setErrorResolver(xwErrorResolver);
        jsonRpcMultiServer.setSeparator('_');
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream output = response.getOutputStream();

        if (request.getMethod().equals("OPTIONS")) {
            output.flush();
            return;
        }

        if (!request.getMethod().equals("POST") && !request.getMethod().equals("GET")) {
            throw new IOException("仅支持POST和GET方法.");
        }

        response.setContentType("application/json-rpc ; charset=utf-8");
        jsonRpcMultiServer.handle(request, response);
    }

    public void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        jsonRpcMultiServer.handle(inputStream, outputStream);
    }

    public JsonRpcMultiServer addService(String name, Object handler, Class<?> remoteInterface) {
        return jsonRpcMultiServer.addService(name, handler, remoteInterface);
    }
}
