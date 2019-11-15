package com.xq.crowd_funding.partfinancing.dao;

import com.xq.crowd_funding.common.pojo.TProjectItemPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
ClassName: TpipDao
 queryList：通过项目Id查询图片详情
@Description: TODO
@Author: GuoXinZhang
@Date: 15:12
@Time: 2019/11/4
@Version: 1.0
*/
@Mapper
public interface TpipDao {
    @Select("select * from t_project_item_pic where projectid=#{value}")
    List<TProjectItemPic> queryList(Integer projectid);
}
