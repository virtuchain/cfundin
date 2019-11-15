package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.partfinancing.pojo.TReturn;
import com.xq.crowd_funding.partfinancing.service.ITReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
ClassName: TReturnController
 queryReturn:返回项目回报列表
@Description: TODO
@Author: GuoXinZhang
@Date: 16:17
@Time: 2019/11/7
@Version: 1.0
*/

@RestController
public class TReturnController {

    @Autowired
    ITReturnService itReturnService;

    @GetMapping("/queryReturn/{projectid}")
    public ResultEntity queryReturn(@PathVariable("projectid") Integer projecid){
        List<TReturn> tReturns = itReturnService.queryReturn(projecid);
        return ResultEntity.successWithData(tReturns);
    }

    @GetMapping("/queryReturnById/{projectid}/{rsupportmoney}")
    public ResultEntity queryReturnById(@PathVariable("projectid")Integer projectid,@PathVariable("rsupportmoney")Integer rsupportmoney){
        TReturn tReturn = itReturnService.queryReturnById(projectid, rsupportmoney);
        return ResultEntity.successWithData(tReturn);
    }

}
