package com.example.zqapigateway;


import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.zq.project.rpc.RpcDemoService;

@SpringBootTest
class ZqapiGatewayApplicationTests {

    @DubboReference
    private RpcDemoService rpcDemoService;

    @Test
    void getName() {
        rpcDemoService.sayHello("zq");
    }


}
