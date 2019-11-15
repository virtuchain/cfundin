package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.common.pojo.TOrder;
import com.xq.crowd_funding.partfinancing.dao.TOrderDao;
import com.xq.crowd_funding.partfinancing.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceimpl implements IOrderService {

    @Resource
    TOrderDao tOrderDao;

    @Override
    public int addOrder(TOrder tOrder) {
        return tOrderDao.addOrder(tOrder);
    }

    @Override
    public List<TOrder> queryTOrderById(Integer id) {
        return tOrderDao.queryTOrderById(id);
    }

    @Override
    public int savaTOrder(TOrder tOrder, Integer memberid) {
        return tOrderDao.savaTOrder(tOrder,memberid);
    }

    @Override
    public int updateStatus(Integer id) {
        return tOrderDao.updateStatus(id);
    }
}