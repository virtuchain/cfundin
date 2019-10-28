package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/
import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.common.utils.myconfigration.redisconfigration.RedisOperation;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseFundingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
     * @param projectVOFront
     * @return  ResultEntity<String>
     */
    @RequestMapping("raisefunding/saveinfo")
    public  ResultEntity<String> saveProjectInfo(@RequestBody ProjectVO projectVOFront ){
        // 从 projectVOFront 获取 projectTempToken
        String projectTempToken = projectVOFront.getProjectTempToken();
       // 判断是否是失败的状态
        ResultEntity<String> resultEntity = redisOperation.readRedisValueByKey(projectTempToken);
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        // 获取 data
        String getProjectJSON =  resultEntity.getData();
        // 转化为 project 对象
       ProjectVO projectVO =  JSON.parseObject(getProjectJSON,ProjectVO.class);
       // 将 projectVOFront里面的属性值放入到  project里面
        BeanUtils.copyProperties(projectVOFront,projectVO);
        // 将  projectVO 转化成 JSON数据，存入 redis
        String setProjectJSON = JSON.toJSONString(projectVO);
        return redisOperation.saveRedisKeyAndValue(projectTempToken,setProjectJSON,-1);
    }
}
