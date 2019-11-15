package com.xq.crowd_funding.partfinancing.dao;

import com.xq.crowd_funding.common.pojo.TMemberProjectFollow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
ClassName: TmpfDao
 queryLength:查看此项目有多少人关注
 queryByProid:通过用户商品Id查询关注表中用户id是否与其对应，有则为已关注，反之未关注
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

    @Select("select * from t_member_project_follow where projectid=#{projectid} and memberid=#{memberid}")
    TMemberProjectFollow queryByProid(Integer projectid, Integer memberid);

    @Select("select count(1) from t_member_project_follow where projectid=#{value}")
    int queryLength(Integer projectid);

    @Insert("insert into t_member_project_follow values(null,#{projectid},#{memberid})")
    int addTmpf(Integer memberid,Integer projectid);

    @Delete("delete from t_member_project_follow where projectid=#{projectid} and memberid=#{memberid}")
    int delTemf(Integer memberid,Integer projectid);

}
