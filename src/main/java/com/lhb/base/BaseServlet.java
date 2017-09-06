package com.lhb.base;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Linhaibin on 2017/6/23.
 */
public abstract class BaseServlet extends HttpServlet {
    protected LhbJsonRpcMultiServer jsonServer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jsonServer = new LhbJsonRpcMultiServer();
        initService(jsonServer);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jsonServer.handle(req, resp);
    }

    abstract protected void initService(LhbJsonRpcMultiServer jsonServer);
}
