package com.xq.crowd_funding.partfinancing.service;

/**
ClassName: ITmpfService
 addTmpf:用户关注
 delTemf:用户取消关注
@Description: TODO
@Author: GuoXinZhang
@Date: 11:31
@Time: 2019/11/1
@Version: 1.0
*/

public interface ITmpfService {

    int addTmpf(Long memberid,Long projectid);

    int delTemf(Long memberid,Long projectid);
}
