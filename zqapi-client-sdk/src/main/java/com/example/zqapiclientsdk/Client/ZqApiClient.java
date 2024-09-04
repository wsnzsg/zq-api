package com.example.zqapiclientsdk.Client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.zqapiclientsdk.Dto.User;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.example.zqapiclientsdk.utils.SignUtils.getSign;


/**
 * 调用第三方接口的客户端
 * @author zq
 *
 */
public class ZqApiClient {

    private String accessKey;
    private String secretKey;

    public ZqApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
//可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.get("http://127.0.0.1:8080/name/", paramMap);
        System.out.println(result);
        return result;

    }

    //封装ak sk进请求头
    private Map<String,String> getHeaderMap(String body){
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("accessKey",accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timeStamp",String.valueOf(System.currentTimeMillis()/1000));
        hashMap.put("body",body);
        hashMap.put("sign", getSign(body,secretKey));

        // 一定不能明文传递 hashMap.put("secretKey",secretKey); 即不放到请求头中
        return hashMap;
    }


    public String getNameByPost( User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://127.0.0.1:8080/name/user")
                .charset(StandardCharsets.UTF_8)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        System.out.println(httpResponse.body());
        return httpResponse.body();
    }
}
