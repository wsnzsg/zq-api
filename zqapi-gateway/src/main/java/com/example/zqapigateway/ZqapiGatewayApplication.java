package com.example.zqapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZqapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqapiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
//                //id 随便起或者根据业务指定
//                .route("to bai du", r -> r.path("/baidu")
//                        .uri("https://www.baidu.com"))
//                .route("to4399", r -> r.path("/4399")
//                        .uri("https://www.4399.com"))

                .build();
    }
}
