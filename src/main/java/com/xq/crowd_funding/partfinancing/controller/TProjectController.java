package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.partfinancing.bean.TProject;
import com.xq.crowd_funding.partfinancing.service.TProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
ClassName: TProjectController
 商品的Controller

@Description: TODO
项目的Controller
queryPro：通过Id获取项目详情
@Author: GuoXinZhang
@Date: 8:24
@Time: 2019/11/4
@Version: 1.0
*/

@RestController
public class TProjectController {
    @Autowired
    TProjectService tProjectService;

    @GetMapping("queryPro")
    public ResultEntity<TProject> queryPro(Long id){
        //通过Id获取项目
        TProject tProject = tProjectService.queryPro(id);
        //将获取到的项目传到下一个界面
        return ResultEntity.successWithData(tProject);
    }
}
