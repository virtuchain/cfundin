package com.xq.crowd_funding.partfinancing.dao;

import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
ClassName: TMemberDao
 queryById：通过ID查询到会员详情
 savaPro：修改用户的手机号码和姓名
@Description: TODO
@Author: GuoXinZhang
@Date: 8:19
@Time: 2019/11/4
@Version: 1.0
*/
@Mapper
public interface TMemberDao {

    @Select("select * from t_member where id = #{value}")
    TMember queryById(Long id);

    @Update("update t_member set phone=#{phone} where id=#{id} and username=#{username}")
    int savaPro(TMember tMember);

}
