package com.atguigu.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RestTemplateConfig
 * Package: com.atguigu.cloud.config
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 23:02
 * @Version 1.0
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //Consul默认支持负载均衡，不加就会报错java.net.UnkownHostException
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
