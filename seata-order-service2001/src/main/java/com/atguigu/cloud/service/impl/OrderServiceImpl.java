package com.atguigu.cloud.service.impl;

import cn.hutool.extra.tokenizer.Word;
import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * ClassName: OrderServiceOmpl
 * Package: com.atguigu.cloud.service.impl
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/12 22:28
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;

    @Override
    @GlobalTransactional(name = "chloe-create-order",rollbackFor = Exception.class) //AT
    public void create(Order order) {
        String xid = RootContext.getXID();
        log.info("==================>开始新建订单" + "\t" + "xid_order:" + xid);

        /**
         * 1. 新建Order
         * 2. 扣减库存
         * 3. 扣减余额
         * 4. 修改订单状态：完成之后修改状态为1
         */
        order.setStatus(0);
        int rows = orderMapper.insertSelective(order);

        Order orderFromDB = null;
        if (rows > 0) {
//            orderFromDB = orderMapper.selectByPrimaryKey(order.getId());
            orderFromDB = orderMapper.selectOne(order);
            log.info("-------> 新建订单成功，orderFromDB info: "+orderFromDB);
            System.out.println();

            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();

            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();

            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);
            Example example = new Example(Order.class);
            Example.Criteria selectCondition = example.createCriteria();
            selectCondition.andEqualTo("userId",orderFromDB.getUserId());
            selectCondition.andEqualTo("status", 0);
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, example);

            log.info("-------> 修改订单状态完成"+"\t"+updateResult);
            log.info("-------> orderFromDB info: "+orderFromDB);
        }

        System.out.println();
        log.info("==================>结束新建订单"+"\t"+"xid_order:" +xid);
    }
}
