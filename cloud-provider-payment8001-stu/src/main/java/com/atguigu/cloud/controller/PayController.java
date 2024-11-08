package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: PayController
 * Package: com.atguigu.cloud.controller
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 18:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    public String addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return "成功插入记录，返回值："+i;
    }
    @DeleteMapping(value = "/del/{id}")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }
    @PutMapping(value = "/update")
    public String updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return "成功修改记录，返回值："+i;
    }
    @GetMapping(value = "/get/{id}")
    public Pay getById(@PathVariable("id") Integer id){
        return payService.getById(id);
    }

    @GetMapping(value = "/getAll")
    public List<Pay> getAll(){
        return payService.getAll();
    }
}
