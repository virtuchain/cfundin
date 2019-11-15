package com.xq.crowd_funding.partfinancing.service;

import com.xq.crowd_funding.common.pojo.TProjectItemPic;

import java.util.List;

/**
ClassName: ITpipService
 queryList：通过项目Id查询图片详情
@Description: TODO
@Author: GuoXinZhang
@Date: 15:19
@Time: 2019/11/4
@Version: 1.0
*/
public interface ITpipService {

    List<TProjectItemPic> queryList(Integer projectid);

}
