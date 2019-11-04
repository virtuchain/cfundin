package com.xq.crowd_funding.manager.dao;

import com.xq.crowd_funding.common.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.manager.bean.TUser;
import org.springframework.stereotype.Component;

@Mapper
public interface TUserDao {

    int insert(@Param("pojo") TUser pojo);//插入接口

    List<TUser> select(@Param("pojo") TUser pojo);//查询所有

    int update(@Param("pojo") TUser pojo);//修改

    int deleteUsers(@Param("pojo") TUser pojo);//删除

    Page queryPage(@Param("pageno") Integer startIndex,
                   @Param("pagesize") Integer pagesize,
                   @Param("pojo") TUser pojo);

    List<TUser> queryList(@Param("startIndex")Integer startIndex,
                          @Param("pagesize")Integer pagesize);

    Integer queryCount(@Param("pojo") TUser pojo);

    List<TUser> queryLike(@Param("queryText") String queryText);
}
