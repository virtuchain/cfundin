package com.xq.crowd_funding.login.bean.service;

import com.xq.crowd_funding.common.pojo.TType;

import java.util.List;

public interface IServiceType {

    public int insert(TType pojo);

    public int insertList(List< TType> pojos);

    public List<TType> select(TType pojo);

    public int update(TType pojo);

    //列表查询
    public List<TType> selectList();
    //删除
    public void deleteid(Integer id);
    //模糊查询
    List<TType> selectBytext(String text);


}
