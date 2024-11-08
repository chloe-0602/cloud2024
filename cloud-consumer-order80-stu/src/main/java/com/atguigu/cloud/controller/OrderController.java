package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
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
@RestController
@RequestMapping("/consumer")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;
    public static final String PAY_URL = "http://localhost:8001";

    /**
     * 只能是Get请求
     *
     * @param payDTO
     * @return
     */
    @GetMapping("/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PAY_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAY_URL + "/pay/get/" + id, ResultData.class, id);
    }

    /**
     * 看官网， put方法没有返回值
     *
     * @param payDTO
     * @return
     */
    @GetMapping("/pay/update")
    public ResultData getPayInfo(PayDTO payDTO) {
        restTemplate.put(PAY_URL + "/pay/update", payDTO);
        return ResultData.success(null);
    }


    @GetMapping("/pay/del/{id}")
    public ResultData delPayById(@PathVariable("id") Integer id) {
        restTemplate.delete(PAY_URL + "/pay/del/" + id, ResultData.class, id);
        return ResultData.success(null);
    }

}
