package com.xq.crowd_funding.manager.service;

import com.xq.crowd_funding.common.pojo.TUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserRoleService {

    int insert(TUserRole pojo);

    int insertList(List<TUserRole> pojo);

    List<TUserRole> select(TUserRole pojo);

    List<TUserRole> selectUserRole(Integer id);

    int update(TUserRole pojo);

    int deleteAssign(TUserRole pojo);
}
