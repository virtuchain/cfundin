package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/

import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.common.utils.myconfigration.redisconfigration.RedisOperation;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseFundingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 发起众筹
 */
@RestController
public class RaiseFundingController {

      @Autowired
      IRaiseFundingService raiseFundingImp;

      @Autowired
      RedisOperation redisOperation;

    /**
     * @param memberSignToken 用于验证用户是否登录
     * @return
     */
    @PostMapping("raisefunding/createProjectVO")

    public ResultEntity<ProjectVO> initCrestion(
            @RequestParam("memberSignToken") String memberSignToken ){
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
    /**
     *  将start-step -1 里面的信息放入到 projectvo里面
     * @param projectVOFront 页面一的vo 数据
     * @return  ResultEntity<String>
     */
    @RequestMapping("raisefunding/saveinfostepone")
    public  ResultEntity<String> saveProjectInfo(
                             @RequestBody ProjectVO projectVOFront ){
        // 从 projectVOFront 获取 projectTempToken
        String projectTempToken = projectVOFront.getProjectTempToken();
        // 判断是否是失败的状态
        ResultEntity<String> resultEntity = redisOperation.readRedisValueByKey(projectTempToken);
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        // 转化为 project 对象
       ProjectVO projectVO =  JSON.parseObject(resultEntity.getData(),ProjectVO.class);
       // 将 projectVOFront里面的属性值放入到  project里面
        BeanUtils.copyProperties(projectVOFront,projectVO);
        // 将  projectVO 转化成 JSON数据，存入 redis
        String ProjectJSON = JSON.toJSONString(projectVO);
        return redisOperation.saveRedisKeyAndValue(projectTempToken,ProjectJSON,-1);
    }
    /**
     * 这里是将回报加入redis
     * @param returnVO
     * @return  ResultEntity<String>
     */
    public  ResultEntity<String> saveProjectReturn(@RequestBody ReturnVO returnVO){
        // 得到 project
        String proToken = returnVO.getProjectTempToken();
        //  从redis取出 pro
        ResultEntity<String> resultEntity  = redisOperation.readRedisValueByKey(proToken);
        // 判断状态
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        // 转化为 project
        ProjectVO projectVO = JSON.parseObject(resultEntity.getData(),ProjectVO.class);

        List<ReturnVO> returnVOList = projectVO.getReturnVOList();

        if (!CrowdUtils.conllectionCkeck(returnVOList)){
            returnVOList = new ArrayList<>();
        }
        returnVOList.add(returnVO);
        // 将  returnVOList set 到  returnVO
        projectVO.setReturnVOList(returnVOList);
        // 转化为 JSON 放入 redis
        String  projectStr = JSON.toJSONString(projectVO);
        return redisOperation.saveRedisKeyAndValue(proToken,projectStr,-1);
    }

    /**
     * 保存用户信息
     * @param memberConfirmInfoVO
     * @return
     */
    public  ResultEntity<String> saveMemberConfirmInfo(@RequestBody MemberConfirmInfoVO memberConfirmInfoVO){
        // 得到 project
        String proToken = memberConfirmInfoVO.getProjectTempToken();
        // 从redis 取出
        ResultEntity<String> resultEntity  = redisOperation.readRedisValueByKey(proToken);
        // 判断状态
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        // 转化为 project
        ProjectVO projectVO = JSON.parseObject(resultEntity.getData(),ProjectVO.class);
        // 将 projectVOFront里面的属性值放入到  project里面
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);
        // 将  projectVO 转化成 JSON数据，存入 redis
        String ProjectJSON = JSON.toJSONString(projectVO);
        return redisOperation.saveRedisKeyAndValue(proToken,ProjectJSON,-1);
    }

    /**
     *  保存 projectpro到数据库
     *
     * @param memberSignToken
     * @param projectTempToken
     * @return
     */
    public  ResultEntity<String> saveAllProjectPro(
            @RequestParam("memberSignToken") String memberSignToken,
            @RequestParam("projectTempToken") String projectTempToken){
        // 验证用户是否登录

        // 从 projectTempToken
        ResultEntity<String> resultEntity = redisOperation.readRedisValueByKey(projectTempToken);

        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        // 得到 JSON数据，转化 成peojectpo对象

        ProjectVO projectVO = JSON.parseObject(resultEntity.getData(),ProjectVO.class);

        // 调用srevice 保存到数据库

        return raiseFundingImp.saveAllProToDatabase(projectVO);
    }
}
