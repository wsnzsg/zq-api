package com.example.zqinterface;


import com.example.zqapiclientsdk.Client.ZqApiClient;
import com.example.zqapiclientsdk.Dto.User;

public class Main {
    public static void main(String[] args) {
        String accessKey="zq";
        String secretKey="abcdefg";
        ZqApiClient zqApiClient=new ZqApiClient(accessKey,secretKey);
        String result1 = zqApiClient.getNameByGet("zq");
        User user=new User();
        user.setName("zq");
        String result2 = zqApiClient.getNameByPost(user);

        System.out.println(result1);
        System.out.println(result2);
    }

}
