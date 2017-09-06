package com.lhb.api.jsonrpc.client.test.impl;

import com.lhb.api.jsonrpc.client.test.LhbTestService;

/**
 * Created by Linhaibin on 2017/6/23.
 */
public class LhbTestServiceImpl implements LhbTestService {
    @Override
    public int get(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }
}
