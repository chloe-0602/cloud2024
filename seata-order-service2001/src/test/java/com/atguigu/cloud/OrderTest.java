package com.atguigu.cloud;

import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: OrderTest
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/13 8:07
 * @Version 1.0
 */
@SpringBootTest
public class OrderTest {
    @Resource
    private OrderMapper orderMapper;
    @Test
    public void test1(){

    }
}
