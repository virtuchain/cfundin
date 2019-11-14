package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/

import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yj
 * @date 2019-11-05
 * @description 发起 众筹 页面 三  的 控制层
 * @version V1.0
 *
 */
@RestController
public class RaiseThreeTepController extends  ConParentProperties {

    /**
     * 返回当前登录的用户信息
     */
    @GetMapping("raisefunding/retuenUserInfo")
    public  ResultEntity retuenUserInfo(HttpServletRequest request){
        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);
        return ResultEntity.successWithData(userToken);
    }

    /**
     * 保存用户信息
     * @param memberConfirmInfoVO
     * @return
     */
    @PostMapping("raisefunding/saveMemberConfirmInfo")
    public ResultEntity saveMemberConfirmInfo(
            MemberConfirmInfoVO memberConfirmInfoVO,HttpServletRequest request){
        System.out.println("memberConfirmInfoVO "+memberConfirmInfoVO.toString());

        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        ResultEntity<String> resultEntity  =
                redisOperation.readRedisValueByKey(userToken.getRaiseToken());

        // 判断状态
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }

        return redisServiceImp.saveMemberConfiInfoToRedis(memberConfirmInfoVO,resultEntity);
    }

    /**
     *  保存 projectpro到数据库
     * @return
     */
    @PostMapping("raisefunding/saveAllProjectPro")
    public  ResultEntity<String> saveAllProjectPro(HttpServletRequest request){

        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        // 从 projectTempToken
        ResultEntity<String> resultEntity =
                redisOperation.readRedisValueByKey(userToken.getRaiseToken());

        // 验证用户是否登录
        if (ResultEntity.FAILED.equals(resultEntity.getResult())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }

        // 得到 JSON数据，转化 成peojectpo对象
        ProjectVO projectVO = JSON.parseObject(resultEntity.getData(),ProjectVO.class);

        // 调用srevice 保存到数据库
        return raiseDataImp.saveAllProToDatabase(projectVO,userToken.getId());
    }
}
