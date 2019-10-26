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
import org.springframework.web.bind.annotation.*;


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

    /**
     * @param memberSignToken 用于验证用户是否登录
     * @return
     */
    @PostMapping("createProjectVO")
    @ResponseBody
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
     * 上传图片
     *  memberSignToken 验证用户是否登录
     *  projectTempToken 从redis中取出 projectvo
     *  pictureHeaderPath 头图片路径
     * @return
     */
    @RequestMapping("uplaod")
    @ResponseBody
    public  ResultEntity<String> uploadHeaderPicturePath(
            @RequestParam("memberSignToken") String memberSignToken,
            @RequestParam("projectTempToken") String projectTempToken,
            @RequestParam("pictureHeaderPath") String pictureHeaderPath){
        // 根据  projectTempToken 从 redis中取出 结果集类
        ResultEntity<String> resultEntity = redisOperation.readRedisValueByKey(projectTempToken);
        // 判断状态是否是 成功的
        if ( ResultEntity.FAILED.equals(resultEntity.getMessage())){
            // 不成功返回失败
            return ResultEntity.failed(resultEntity.getMessage());
        }
        // 成功从结果集类拿出数据
         String getProjectJSON = resultEntity.getData();
        // 将  projectJSONStr 转化成对象
         ProjectVO projectVO = JSON.parseObject(getProjectJSON,ProjectVO.class);
         // 将路径放到 projectVO 里面
         projectVO.setHeaderPicturePath(pictureHeaderPath);
        // 将 projectVO 转化成 JSON
        String setProjectJSON = JSON.toJSONString(projectVO);
        // 将  setProjectJSON 放入  redis
        return  redisOperation.saveRedisKeyAndValue(projectTempToken,setProjectJSON,-1);
    }

}
