package com.atguigu.cloud.test;

import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: StorageTest
 * Package: com.atguigu.cloud.test
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/13 8:01
 * @Version 1.0
 */
@SpringBootTest
public class StorageTest {
    @Resource
    private StorageService storageService;
    
    @Test
    public void test1(){
        storageService.decrease(1L,20);
    }
}
