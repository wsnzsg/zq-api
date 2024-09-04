package com.example.zqinterface;

import com.example.zqapiclientsdk.Client.ZqApiClient;
import com.example.zqapiclientsdk.Dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ZqInterfaceApplicationTests {

    @Resource
    private ZqApiClient zqApiClient;
    @Test
    void contextLoads() {
        String result1 = zqApiClient.getNameByGet("zq");
        User user=new User();
        user.setName("zq");
        String result2 = zqApiClient.getNameByPost(user);

        System.out.println(result1);
        System.out.println(result2);


    }

}
