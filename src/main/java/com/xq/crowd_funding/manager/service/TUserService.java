package com.xq.crowd_funding.manager.service;

import com.xq.crowd_funding.common.pojo.TUser;
import com.xq.crowd_funding.common.utils.Page;

import java.util.List;

public interface TUserService {
    //插入
    int insert(TUser pojo);
    //新增用户
    int update(TUser pojo);
    //删除数据
    int deleteUsers(TUser pojo);
    //分页列表查询
    Page queryPage(Integer pageno, Integer pagesize,TUser pojo);
    //返回查询结果
    List<TUser> queryList(Integer startIndex, Integer pagesize);
    //查询数据总条数
    Integer queryCount(TUser pojo);
    //模糊查询
    List<TUser> queryLike(String queryText);
}
