package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.AccountMapper;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: AccountServiceImpl
 * Package: com.atguigu.cloud.service.impl
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/12 22:52
 * @Version 1.0
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, Long money) {
        log.info("------->account-service中扣减账户余额开始");

        accountMapper.decrease(userId, money);

//        myTimeOut();
//        int age = 10/0;
        log.info("------->account-service中扣减账户余额结束");
    }

    /**
     * 模拟超时异常，全局事务回滚
     */
    private static void myTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
