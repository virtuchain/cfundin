package com.xq.crowd_funding.realname.dao;

import com.xq.crowd_funding.common.pojo.TMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RealNameTMemberDao {

    //查询id
    @Select("select * from t_member where id = #{value}")
    TMember queryById(Long id);

    //修改实名认证状态
    @Update("update t_member set authstatus=#{pojo.authstatus} where id=#{pojo.id}")
    int update(@Param("pojo") TMember pojo);
}
 