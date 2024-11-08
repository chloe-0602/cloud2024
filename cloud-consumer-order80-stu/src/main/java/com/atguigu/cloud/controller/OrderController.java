package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: OrderController
 * Package: com.atguigu.cloud.controller
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 22:34
 * @Version 1.0
 */
@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    public static final String PAY_URL="http://localhost:8001";

    @GetMapping("/consumer/pay/add")
    public ResultData addPayApi(PayDTO payDTO){
        return restTemplate.postForObject(PAY_URL + "/pay/add", payDTO, ResultData.class);
    }
}
