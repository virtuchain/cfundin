package com.xq.crowd_funding.realname.service;

import com.xq.crowd_funding.common.pojo.TMember;

public interface TMemberService {

    TMember queryById(Long id);

    //修改实名认证状态
    int update(TMember pojo);
}
