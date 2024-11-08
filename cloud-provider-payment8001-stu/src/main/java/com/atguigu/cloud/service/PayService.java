package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * ClassName: PayService
 * Package: com.atguigu.cloud.service
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 18:08
 * @Version 1.0
 */
public interface PayService {
    Integer add(Pay pay);
    Integer delete(Integer id);
    Integer update(Pay pay);
    Pay getById(Integer id);
    public List<Pay> getAll();
}
