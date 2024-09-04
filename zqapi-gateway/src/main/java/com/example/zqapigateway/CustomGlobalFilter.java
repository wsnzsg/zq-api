package com.example.zqapigateway;


import cn.hutool.core.util.NumberUtil;


import cn.hutool.core.util.StrUtil;

import com.example.zqapiclientsdk.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 全局过滤器  //1. 用户发送请求到API网关 转发请求通过网关配置声明式实现
 *
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    //建议用白名单，更安全些
    //
    //如果这个来源地址不是白名单里面的，我们就直接设个状态码（这里设置403），
    // 然后拦截掉response.setComplete()** 可以理解为设置响应完成
    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1", "127.0.0.2");
    private static final long FIVE_MINUTES = 5*60;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //2. 请求日志
       //获取请求信息，exchange就是交换机，所有信息都存储在里面，chain是责任链模式的实现，有时是chain.next有时是chain.filter
        // 1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求id: {}", request.getId());
        log.info("请求路径: {}", request.getPath());
        log.info("请求方法: {}", request.getMethod());
        log.info("请求参数: {}", request.getQueryParams());
        log.info("请求头: {}", request.getHeaders());
        log.info("请求地址: {}", request.getRemoteAddress());
        //得到正确的源地址
        String remoteAddress = request.getRemoteAddress().getHostString();

        // 2. 访问控制 - 黑白名单
        if (!IP_WHITE_LIST.contains(remoteAddress)){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        //4. 用户鉴权（判断ak，sk是否合法）
        // 3. 用户鉴权
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        // 防止中文乱码
        String body = null;
        try {
            body = URLDecoder.decode(headers.getFirst("body"), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String sign = headers.getFirst("sign");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判断是否有空
        if (hasBlank) {
            return handleInvokeError(exchange.getResponse());
        }
        // TODO 使用accessKey去数据库查询secretKey
        // 假设查到的secret是abc 进行加密得到sign
        String secretKey = "abc";
        String sign1 =  SignUtils.getSign(body, secretKey);
        if (!StrUtil.equals(sign, sign1)) {
            return handleInvokeError(exchange.getResponse());
        }
        // TODO 判断随机数nonce
        // 时间戳是否为数字
        if (!NumberUtil.isNumber(timestamp)) {
            return handleInvokeError(exchange.getResponse());
        }
        // 五分钟内的请求有效
        if (System.currentTimeMillis() - Long.parseLong(timestamp) > FIVE_MINUTES) {
            return handleInvokeError(exchange.getResponse());
        }
        //5. 请求的模拟接口是否存在？
        //6. **请求转发，调用模拟接口**

        //7. 响应日志
        //8. 调用成功，接口调用次数+1
        //9. 调用失败，返回规范错误码

        log.info("custom global filter");
        return chain.filter(exchange);
    }
    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
