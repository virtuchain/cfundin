package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.partfinancing.bean.TProject;
import com.xq.crowd_funding.partfinancing.dao.TProjectDao;
import com.xq.crowd_funding.partfinancing.service.TProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TProjectServiceimpl implements TProjectService {

    @Resource
    TProjectDao tProjectDao;

    @Override
    public TProject queryPro(Long id) {
        return tProjectDao.queryPro(id);
    }
}
