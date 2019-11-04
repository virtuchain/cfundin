package com.xq.crowd_funding.partfinancing.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
ClassName: TmpfDao
 addTmpf:将用户ID和项目ID添加进关注表
 delTemf:用户取消关注
@Description: TODO
@Author: GuoXinZhang
@Date: 11:21
@Time: 2019/11/1
@Version: 1.0
*/
@Mapper
public interface TmpfDao {
    @Insert("insert into t_member_project_follow values(null,#{projectid},#{memberid})")
    int addTmpf(Long memberid,Long projectid);

    @Delete("delete from t_member_project_follow where projectid=#{projectid} and memberid=#{memberid}")
    int delTemf(Long memberid,Long projectid);

}
