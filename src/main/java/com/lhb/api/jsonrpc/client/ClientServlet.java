package com.lhb.api.jsonrpc.client;

import com.lhb.api.jsonrpc.client.test.LhbTestService;
import com.lhb.api.jsonrpc.client.test.impl.LhbTestServiceImpl;
import com.lhb.base.BaseServlet;
import com.lhb.base.LhbJsonRpcMultiServer;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Linhaibin on 2017/6/23.
 */
@WebServlet("/ClientService")
public class ClientServlet extends BaseServlet{
    @Override
    protected void initService(LhbJsonRpcMultiServer jsonServer) {
        jsonServer.addService("lhbTest", new LhbTestServiceImpl(), LhbTestService.class);
    }
}
