package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.common.pojo.TProjectItemPic;
import com.xq.crowd_funding.partfinancing.dao.TpipDao;
import com.xq.crowd_funding.partfinancing.service.ITpipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TpipServiceimpl implements ITpipService {

    @Resource
    TpipDao tpipDao;

    @Override
    public List<TProjectItemPic> queryList(Integer projectid) {
        return tpipDao.queryList(projectid);
    }
}
