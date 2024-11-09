package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: ${NAME}
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 22:31
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MainStu80 {
    public static void main(String[] args) {
        SpringApplication.run(MainStu80.class, args);
    }
}