package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PayServiceImpl
 * Package: com.atguigu.cloud.service.imple
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 18:10
 * @Version 1.0
 */
@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayMapper payMapper;
    @Override
    public Integer add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public Integer delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
