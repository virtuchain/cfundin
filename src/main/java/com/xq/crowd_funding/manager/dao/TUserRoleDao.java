package com.xq.crowd_funding.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.common.pojo.TUserRole;
@Mapper
public interface TUserRoleDao {

    int insert(@Param("pojo") TUserRole pojo);

    int insertList(@Param("pojos") List< TUserRole> pojo);
    //查询用户权限关联表
    List<TUserRole> select(@Param("pojo") TUserRole pojo);
    //查询单个用户权限
    List<TUserRole> selectUserRole(@Param("userid") Integer userid);

    int update(@Param("pojo") TUserRole pojo);

    int  deleteAssign(@Param("pojo") TUserRole pojo);




}
