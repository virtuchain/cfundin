package com.xq.crowd_funding.manager.service;

import com.xq.crowd_funding.common.pojo.TPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TPermissionService {

    int insert(TPermission pojo);

    int insertList(List<TPermission> pojo);

    List<TPermission> select(TPermission pojo);

    int update(TPermission pojo);

    TPermission getRootPermission();

    List<TPermission> getChildrenPermissionByPid(Integer id);

    TPermission getPermissionById(Integer id);

    int delete(TPermission pojo);

    List<Integer> queryPermissionByRoleId(Integer roleid);

}
