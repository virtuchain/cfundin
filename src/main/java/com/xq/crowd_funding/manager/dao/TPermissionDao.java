package com.xq.crowd_funding.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.common.pojo.TPermission;
@Mapper
public interface TPermissionDao {

    int insert(@Param("pojo") TPermission pojo);

    int insertList(@Param("pojos") List< TPermission> pojo);

    List<TPermission> select(@Param("pojo") TPermission pojo);

    int update(@Param("pojo") TPermission pojo);

    TPermission getRootPermission();

    List<TPermission> getChildrenPermissionByPid(@Param("id") Integer id);

    TPermission getPermissionById(@Param("id") Integer id);

    int delete(@Param("pojo") TPermission pojo);
}
