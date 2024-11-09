package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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
    public ResultData addPay(@RequestBody Pay pay) {
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @DeleteMapping(value = "/del/{id}")
    public ResultData deletePay(@PathVariable("id") Integer id) {
        Integer rows = payService.delete(id);
        return ResultData.success("删除数据： " + rows + "行");
    }

    @PutMapping(value = "/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @GetMapping(value = "/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/getAll")
    public ResultData getAll() {
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }

    @GetMapping("/error/{id}")
    public ResultData error(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("id不能为负数");
        return ResultData.fail("201", "saa");
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/get/info")
    public ResultData getInfoByConsul(@Value("${atguigu.info}") String info) {
        return ResultData.success("Atguigu Iinfo: " + info +  "    port: " + port);
    }
}
