package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.common.pojo.TProject;
import com.xq.crowd_funding.partfinancing.dao.TProjectDao;
import com.xq.crowd_funding.partfinancing.service.ITprojectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TProjectServiceimpl implements ITprojectService {

    @Resource
    TProjectDao tProjectDao;

    @Override
    public TProject queryPro(Integer id) {
        return tProjectDao.queryPro(id);
    }
}
