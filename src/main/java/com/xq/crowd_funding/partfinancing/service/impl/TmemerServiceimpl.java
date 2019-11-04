package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.partfinancing.bean.TMember;
import com.xq.crowd_funding.partfinancing.dao.TMemberDao;
import com.xq.crowd_funding.partfinancing.service.ITmemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TmemerServiceimpl implements ITmemberService {

    @Resource
    TMemberDao tMemberDao;

    @Override
    public TMember queryById(Long id) {
        return tMemberDao.queryById(id);
    }

    @Override
    public int savaPro(TMember tMember) {
        return tMemberDao.savaPro(tMember);
    }
}
