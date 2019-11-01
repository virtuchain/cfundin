package com.xq.crowd_funding.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.manager.bean.TUser;
import org.springframework.stereotype.Component;

@Mapper
public interface TUserDao {

    int insert(@Param("pojo") TUser pojo);//插入接口

    int insertList(@Param("pojos") List< TUser> pojo);

    List<TUser> select(@Param("pojo") TUser pojo);//查询所有接口

    int update(@Param("pojo") TUser pojo);//修改接口

    int delete(@Param("pojo") TUser pojo);

    int deleteUsers(@Param("pojo") TUser pojo);
}
