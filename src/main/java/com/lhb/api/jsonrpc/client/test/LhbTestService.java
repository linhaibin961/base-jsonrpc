package com.lhb.api.jsonrpc.client.test;

import com.yzmy.jsonrpc4j.JsonRpcMethod;
import com.yzmy.jsonrpc4j.JsonRpcParam;

/**
 * Created by Linhaibin on 2017/6/23.
 */
public interface LhbTestService {
    @JsonRpcMethod("get")
    int get(@JsonRpcParam("a") int a, @JsonRpcParam("b") int b);

    @JsonRpcMethod("sub")
    int sub(@JsonRpcParam("a") int a, @JsonRpcParam("b") int b);
}
