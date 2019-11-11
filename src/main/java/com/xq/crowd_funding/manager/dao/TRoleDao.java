package com.xq.crowd_funding.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.common.pojo.TRole;
@Mapper
public interface TRoleDao {

    int insert(@Param("pojo") TRole pojo);

    int insertList(@Param("pojos") List< TRole> pojo);

    List<TRole> select(@Param("pojo") TRole pojo);

    List<TRole> selectUserRole(@Param("userid") Integer userid);

    int update(@Param("pojo") TRole pojo);

}
