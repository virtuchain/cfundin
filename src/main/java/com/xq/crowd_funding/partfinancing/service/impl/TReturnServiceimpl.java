package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.partfinancing.dao.TReturnDao;
import com.xq.crowd_funding.partfinancing.pojo.TReturn;
import com.xq.crowd_funding.partfinancing.service.ITReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TReturnServiceimpl implements ITReturnService {
    @Resource
    TReturnDao tReturnDao;

    @Override
    public List<TReturn> queryReturn(Integer projectid) {
        return tReturnDao.queryReturn(projectid);
    }

    @Override
    public TReturn queryReturnById(Integer projectid, Integer rsupportmoney) {
        return tReturnDao.queryReturnById(projectid,rsupportmoney);
    }
}
