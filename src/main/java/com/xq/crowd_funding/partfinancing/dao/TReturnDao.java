package com.xq.crowd_funding.partfinancing.dao;

import com.xq.crowd_funding.partfinancing.pojo.TReturn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
ClassName: TReturnDao
 queryReturn:查看回报集合
 queryReturnById:通过项目Id和支持金额获取回报详情
@Description: TODO
@Author: GuoXinZhang
@Date: 9:05
@Time: 2019/11/7
@Version: 1.0
*/
@Mapper
public interface TReturnDao {
    List<TReturn> queryReturn(Integer projectid);

    TReturn queryReturnById(Integer projectid,Integer rsupportmoney);
}
