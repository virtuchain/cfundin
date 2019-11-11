package com.xq.crowd_funding.login.bean.service;


import com.xq.crowd_funding.partfinancing.bean.TMember;

import java.util.List;

public interface IService{
    void insert(TMember tMember);

    TMember selectUser( String loginacct);

    void setUserType(Integer userType);

    String selectUsername(String logginacct);

    String selectPhone(String Phone);
}
