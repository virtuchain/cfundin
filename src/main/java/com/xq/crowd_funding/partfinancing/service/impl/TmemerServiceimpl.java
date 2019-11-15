package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.partfinancing.dao.TMemberDao;
import com.xq.crowd_funding.partfinancing.pojo.TMember;
import com.xq.crowd_funding.partfinancing.service.ITmemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TmemerServiceimpl implements ITmemberService {

    @Resource
    TMemberDao tMemberDao;

    @Override
    public TMember queryById(Integer projectid) {
        return tMemberDao.queryById(projectid);
    }

    @Override
    public int savaPro(TMember tMember) {
        return tMemberDao.savaPro(tMember);
    }

    @Override
    public List<TMember> queryTMemberById(Integer id) {
        return tMemberDao.queryTMemberById(id);
    }
}
