package com.xq.crowd_funding.login.bean.dao;

import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Select;
@Mapper
public interface TMemberDaoo{
    @Insert("insert into t_member(loginacct,userpswd,username,email,authstatus,usertype,phone) values(#{tMember.loginacct},#{tMember.userpswd},#{tMember.username},#{tMember.email},#{tMember.authstatus},#{tMember.usertype},#{tMember.phone})")
    void insert(@Param("tMember") TMember tMember);


    //查账号是否存在
    @Select("select *  from t_member where loginacct=#{loginacct} and usertype=#{usertype}")
    TMember SelectUser(@Param("loginacct")String loginacct, @Param("usertype")Integer usertype);

    @Select("select username from t_member where loginacct=#{loginacct}")
     String selectUsername(@Param("loginacct")String logginacct);

    @Select("select phone from t_member where phone=#{Phone}")
    String selectPhone(@Param("Phone")String Phone);
}
