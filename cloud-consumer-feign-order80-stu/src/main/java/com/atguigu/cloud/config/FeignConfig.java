package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FeignConfig
 * Package: com.atguigu.cloud.config
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/10 8:48
 * @Version 1.0
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer retryer(){
        return Retryer.NEVER_RETRY;
//        return new Retryer.Default(100, 1,3);
    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
