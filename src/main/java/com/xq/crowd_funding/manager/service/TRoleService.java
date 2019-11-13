package com.xq.crowd_funding.manager.service;

import com.xq.crowd_funding.common.pojo.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRoleService {

    int insert(TRole pojo);

    int insertList(List<TRole> pojo);

    List<TRole> select(TRole pojo);

    int update(TRole pojo);


}
