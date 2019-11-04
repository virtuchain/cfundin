package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.partfinancing.bean.TOrder;
import com.xq.crowd_funding.partfinancing.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceimpl implements IOrderService {

    @Resource
    IOrderService iOrderService;

    @Override
    public int addOrder(TOrder tOrder) {
        return iOrderService.addOrder(tOrder);
    }

    @Override
    public List<TOrder> queryTOrderById(Long id) {
        return iOrderService.queryTOrderById(id);
    }

    @Override
    public int savaTOrder(TOrder tOrder, Long memberid) {
        return iOrderService.savaTOrder(tOrder,memberid);
    }
}