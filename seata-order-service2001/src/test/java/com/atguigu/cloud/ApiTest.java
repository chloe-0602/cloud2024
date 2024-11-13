package com.atguigu.cloud;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: ApiTest
 * Package: com.atguigu.cloud
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/13 8:03
 * @Version 1.0
 */
@SpringBootTest
public class ApiTest {
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;
    
    @Test
    public void test1(){
        accountFeignApi.decrease(1L, 200L);
    }
    
    @Test
    public void test2(){
        storageFeignApi.decrease(1L, 20);
    }
}
