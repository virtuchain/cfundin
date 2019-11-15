package com.xq.crowd_funding.login.bean.service;


import com.xq.crowd_funding.partfinancing.bean.TMember;

public interface IService{
    void insert(TMember tMember);

    TMember selectUser( String loginacct);

    void setUserType(Integer userType);

    String selectUsername(String logginacct);

    String selectPhone(String Phone);
}
