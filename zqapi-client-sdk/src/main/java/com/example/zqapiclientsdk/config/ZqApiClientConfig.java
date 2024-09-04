package com.example.zqapiclientsdk.config;

import com.example.zqapiclientsdk.Client.ZqApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("zqapi.client")
@Data
@ComponentScan
public class ZqApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public ZqApiClient zqApiClient(){
        return new ZqApiClient(accessKey,secretKey);

    }
}
