package com.xq.crowd_funding.webcontorller;/*
    @auther yangjie
*/

import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.beans.ResultEntity;
import com.xq.crowd_funding.beans.raisefunding.vo.ProjectVO;
import com.xq.crowd_funding.myconfigration.redis_configration.RedisOperation;
import com.xq.crowd_funding.myutils.TokenKeyUtils;
import com.xq.crowd_funding.servicepackage.impl.RaiseFundingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 发起众筹
 */
@RequestMapping("raisefunding")
@Controller
public class RaiseFundingController {

    @Autowired
    RaiseFundingServiceImpl raiseFundingImp;
    @Autowired
    RedisOperation redisOperation;

    @GetMapping("ok")
    public ResultEntity<ProjectVO> initCrestion(){
        System.out.println("进入了这个方法了 。。。。。");
        // 这里没有验证用户登录

        // 创建 空  ProjectVO
        ProjectVO projectVO = new ProjectVO();
        // 将token 放入  projectVO
        String raiseToken = TokenKeyUtils.getTokenAndUUID(TokenKeyUtils.REDIS_RAISE_KEY_PREFIX);
        projectVO.setProjectTempToken(raiseToken);
        // 将 projectVO 转化成 json 字符存入 redis
        String projectVOJSON =  JSON.toJSONString(projectVO);
        // 将 projectVOJSON 装入 redis
        redisOperation.saveRedisKeyAndValue(raiseToken,projectVOJSON,-1);
        return  ResultEntity.successWithData(projectVO);
    }

}
