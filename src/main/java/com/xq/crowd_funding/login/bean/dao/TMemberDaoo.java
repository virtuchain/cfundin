package com.xq.crowd_funding.login.bean.dao;

import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Select;
@Mapper
public interface TMemberDaoo{

    int insert(@Param("pojo") TMember pojo);

    int insertList(@Param("pojos") List<TMember> pojo);

    List<TMember> select(@Param("pojo") TMember pojo);

    int update(@Param("pojo") TMember pojo);

    //查账号是否存在
    @Select("select *  from t_member where loginacct=#{loginacct} and usertype=#{usertype}")
    TMember SelectUser(@Param("loginacct")String loginacct, @Param("usertype")Integer usertype);


}
