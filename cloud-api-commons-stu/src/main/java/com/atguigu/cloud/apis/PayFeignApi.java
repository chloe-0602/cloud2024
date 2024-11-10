package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: PayFeignApi
 * Package: com.atguigu.cloud.apis
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/9 21:10
 * @Version 1.0
 */
@FeignClient("cloud-gateway")
public interface PayFeignApi {
    /**
     * 新增一条支付相关流水记录
     * @param
     * @return
     */
    @PostMapping(value = "/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping("/pay/get/info")
    public ResultData mylb();

    /**
     * Resillisence4J CircuitBreaker 的案例
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resillience4J CircuitBreaker的案例演示
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4J Ratelimiter的案例演示
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer监控案例
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
