package com.example.zqapigateway.rpc;

import org.apache.dubbo.config.annotation.DubboReference;

public interface RpcDemoService {
    String sayHello(String name);
}
