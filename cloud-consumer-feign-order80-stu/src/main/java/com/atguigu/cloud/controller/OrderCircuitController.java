package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: OrderCircuitController
 * Package: com.atguigu.cloud.controller
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/10 11:08
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * myCircuitFallback: 是服务熔断，导致进入服务降级之后的兜底处理
     *
     * @param id
     * @param t
     * @return
     */
    public String myCircuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myCircuitFallback, 系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }
/*
    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id){
        return payFeignApi.myBulkhead(id);
    }
    public String myBulkheadFallback(Integer id, Throwable t){
        return "myBulkheadFallback, ，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }*/

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkhead(@PathVariable("id") Integer id) {
        log.info("------进入myBulkhead，");
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { throw new RuntimeException(e); }
        log.info("-------准备离开......");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }

    public CompletableFuture<String> myBulkheadFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(()-> "------ Bulkhead.Type.THREADPOOL\t"+"myBulkheadFallback, ，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }

}
