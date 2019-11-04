package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.common.configrations.redisconfigration.RedisOperation;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseRedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IRaiRedisSerImpl implements IRaiseRedisService {

    @Autowired
    RedisOperation redisOperation;

    /**
     * 初始化 projectVO
     *
     * @return ResultEntity
     */
    @Override
    public ResultEntity<ProjectVO> initProjectVOToRedis() {
        // 创建 空  ProjectVO
        ProjectVO projectVO = new ProjectVO();
        // 将token 放入  projectVO
        String raiseToken = TokenKeyUtils.getTokenAndUUID(TokenKeyUtils.REDIS_RAISE_KEY_PREFIX);
        projectVO.setProjectTempToken(raiseToken);
        // 将 projectVO 转化成 json 字符存入 redis
        String projectVOJSON = JSON.toJSONString(projectVO);
        // 将 projectVOJSON 装入 redis
        redisOperation.saveRedisKeyAndValue(raiseToken, projectVOJSON, -1);
        return ResultEntity.successWithData(projectVO);
    }

    /**
     * 将start-step -1 里面的信息放入到 projectvo里面
     * projectVOFront 页面一的vo 数据
     *
     * @return ResultEntity<String>
     */
    @Override
    public ResultEntity<String> saveProjectInfoToRedis(
            ProjectVO projectVOFront, ResultEntity resultEntity) {

        ProjectVO projectVO = JSON.parseObject(resultEntity.getData().toString(),ProjectVO.class);

        // 将 projectVOFront里面的属性值放入到  project里面
        BeanUtils.copyProperties(projectVOFront, projectVO);

        // 将  projectVO 转化成 JSON数据，存入 redis
        String ProjectJSON = JSON.toJSONString(projectVO);

        return redisOperation.saveRedisKeyAndValue(
                projectVOFront.getProjectTempToken(), ProjectJSON, -1);
    }

    /**
     * 这里是将回报加入redis
     *  returnVO
     * @return  ResultEntity<String>
     */
    @Override
    public ResultEntity<String> saveProjectReturnToRedis(
            ReturnVO returnVO, ResultEntity resultEntity) {

        // 转化为 project
        ProjectVO projectVO = JSON.parseObject(resultEntity.getData().toString(),ProjectVO.class);

        List<ReturnVO> returnVOList = projectVO.getReturnVOList();

        if (!CrowdUtils.conllectionCkeck(returnVOList)){
            returnVOList = new ArrayList<>();
        }
        returnVOList.add(returnVO);

        // 将  returnVOList set 到  returnVO
        projectVO.setReturnVOList(returnVOList);

        // 转化为 JSON 放入 redis
        String  projectStr = JSON.toJSONString(projectVO);

        return redisOperation.saveRedisKeyAndValue(
                returnVO.getProjectTempToken(),projectStr,-1);
    }
    /**
     * 保存用户信息
     *  memberConfirmInfoVO
     * @return
     */
    @Override
    public ResultEntity<String> saveMemberConfiInfoToRedis(
            MemberConfirmInfoVO memberConfirmInfoVO, ResultEntity resultEntity) {

        // 转化为 project
        ProjectVO projectVO = JSON.parseObject(resultEntity.getData().toString(),ProjectVO.class);

        // 将 projectVOFront里面的属性值放入到  project里面
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        // 将  projectVO 转化成 JSON数据，存入 redis
        String ProjectJSON = JSON.toJSONString(projectVO);

        return redisOperation.saveRedisKeyAndValue(
                memberConfirmInfoVO.getProjectTempToken(),ProjectJSON,-1);
    }

}

