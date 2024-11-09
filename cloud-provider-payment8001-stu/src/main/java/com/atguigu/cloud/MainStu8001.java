package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName: ${NAME}
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 18:03
 * @Version 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")
public class MainStu8001 {
    public static void main(String[] args) {
        SpringApplication.run(MainStu8001.class, args);
    }
}