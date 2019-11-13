package com.xq.crowd_funding.realname.service.imp;


import com.xq.crowd_funding.common.pojo.TMember;
import com.xq.crowd_funding.realname.dao.RealNameTMemberDao;
import com.xq.crowd_funding.realname.service.TMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TMemberServiceImp implements TMemberService {

    @Resource
    private RealNameTMemberDao tMemberDao;

    @Override
    public TMember queryById(Long id) {
        return tMemberDao.queryById(id);
    }

    @Override
    public int update(TMember pojo) {
        pojo.setAuthstatus("2");
        System.out.println("TMember pojo "+pojo.toString());
        return tMemberDao.update(pojo);
    }

}
 