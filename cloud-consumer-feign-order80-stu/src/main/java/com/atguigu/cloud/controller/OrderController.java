package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
@Slf4j
@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        log.info("\"第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        log.info("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            log.info("---------> 调用开始： {}", DateUtil.now());
           resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("---------> 调用结束： {}", DateUtil.now());
        }
        return resultData;
    }

    @GetMapping("/feign/pay/mylb")
    public ResultData mylb() {
        return payFeignApi.mylb();
    }
}
