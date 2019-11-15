package com.xq.crowd_funding.partfinancing.dao;

import com.xq.crowd_funding.common.pojo.TProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
ClassName: TProjectDao
 queryPro:通过Id查询项目信息
@Description: TODO
@Author: GuoXinZhang
@Date: 8:01
@Time: 2019/11/4
@Version: 1.0
*/
@Mapper
public interface TProjectDao {

    @Select("select * from t_project where id=#{value}")
    TProject queryPro(Integer id);

}
