package com.xq.crowd_funding.partfinancing.service;

import com.xq.crowd_funding.partfinancing.bean.TOrder;

import java.util.List;

/**
ClassName: IOrderService
 addOrder:添加订单
 queryTOrderById:根据用户ID查看订单
 savaTOrder:修改订单地址
@Description: TODO
@Author: GuoXinZhang
@Date: 8:09
@Time: 2019/11/4
@Version: 1.0
*/
public interface IOrderService {

    int addOrder(TOrder tOrder);

    List<TOrder> queryTOrderById(Long id);

    int savaTOrder(TOrder tOrder, Long memberid);

}
