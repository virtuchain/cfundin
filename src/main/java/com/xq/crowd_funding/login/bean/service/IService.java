package com.xq.crowd_funding.login.bean.service;


import com.xq.crowd_funding.partfinancing.bean.TMember;

import java.util.List;

public interface IService{
    int insert(TMember pojo);

    int insertList( List< TMember> pojo);

    List<TMember> select(TMember pojo);

    int update( TMember pojo);

    TMember selectUser( String loginacct);

    void setUserType(Integer userType);
}
