package com.example.zqinterface.Controller;

import cn.hutool.http.HttpRequest;

import com.example.zqapiclientsdk.Dto.User;
import com.example.zqapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.function.ToDoubleBiFunction;

@RestController
@RequestMapping("/name")
@ResponseBody
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name){

        return name;

    }

    @PostMapping("/user")
    public String getNameByPost(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String nonce = request.getHeader("nonce");
        String timeStamp = request.getHeader("timeStamp");
        String sign = request.getHeader("sign");
//        String secretKey = request.getHeader("secretKey");
        //TODO 正确方式是从数据库中查ak 和sk

        String sign1 = SignUtils.getSign(body, "abcdefg");
        //模拟校验，真实校验应该是查数据库
        if(!accessKey.equals("zq")){
            throw new RuntimeException("无权限");
        }
        //TODO 时间戳校验，不可超过当前时间的五分钟,随机数校验

        if(!sign.equals(sign1)){
            throw new RuntimeException("无权限");
        }
        return user.getName();
    }

}
