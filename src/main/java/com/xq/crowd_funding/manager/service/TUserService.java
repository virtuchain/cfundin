package com.xq.crowd_funding.manager.service;

import com.xq.crowd_funding.manager.bean.TUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TUserService {

    int insert(TUser pojo);

    int insertList(List<TUser> pojo);

    List<TUser> select(TUser pojo);//查询所有用户

    int update(TUser pojo);

}
