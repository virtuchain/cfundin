package com.xq.crowd_funding.partfinancing.service;

import com.xq.crowd_funding.partfinancing.bean.TProject;
/**
ClassName: TProjectService
 queryPro:通过Id查询项目信息
@Description: TODO
@Author: GuoXinZhang
@Date: 8:11
@Time: 2019/11/4
@Version: 1.0
*/
public interface TProjectService {
    TProject queryPro(Long id);
}
