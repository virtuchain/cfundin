package com.xq.crowd_funding.partfinancing.service;

import com.xq.crowd_funding.common.pojo.TMemberProjectFollow;

/**
ClassName: ITmpfService
 queryLength:查看此项目有多少人关注
 queryByProid:查询用户是否已关注
 addTmpf:用户关注
 delTemf:用户取消关注
@Description: TODO
@Author: GuoXinZhang
@Date: 11:31
@Time: 2019/11/1
@Version: 1.0
*/

public interface ITmpfService {
    TMemberProjectFollow queryByProid(Integer projectid, Integer memberid);

    int queryLength(Integer projectid);

    int addTmpf(Integer memberid,Integer projectid);

    int delTemf(Integer memberid,Integer projectid);
}
