package com.example.zqapigateway;

import com.example.zqapigateway.rpc.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZqapiGatewayApplicationTests {
    @DubboReference
    private RpcDemoService rpcDemoService;

    @Test
    void contextLoads() {
    }
    //rpc测试
    @Test
    void testRpc() {
        System.out.println(rpcDemoService.sayHello("world"));
    }
}
