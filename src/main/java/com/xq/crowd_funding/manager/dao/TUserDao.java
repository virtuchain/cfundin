package com.xq.crowd_funding.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.manager.bean.TUser;
import org.springframework.stereotype.Component;

@Mapper
public interface TUserDao {

    int insert(@Param("pojo") TUser pojo);

    int insertList(@Param("pojos") List< TUser> pojo);

    List<TUser> select(@Param("pojo") TUser pojo);

    int update(@Param("pojo") TUser pojo);

}
