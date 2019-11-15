package com.xq.crowd_funding.partfinancing.service;

import com.xq.crowd_funding.partfinancing.pojo.TReturn;

import java.util.List;

/**
ClassName: ITReturnService
 queryReturn:查看回报集合
 queryReturnById:通过项目Id和支持金额获取回报详情
@Description: TODO
@Author: GuoXinZhang
@Date: 16:11
@Time: 2019/11/7
@Version: 1.0
*/
public interface ITReturnService {
    List<TReturn> queryReturn(Integer projectid);

    TReturn queryReturnById(Integer projectid,Integer rsupportmoney);
}
