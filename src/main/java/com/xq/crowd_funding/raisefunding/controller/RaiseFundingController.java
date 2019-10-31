package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/
import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.configrations.redisconfigration.RedisOperation;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseDataBaseService;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseRedisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 发起众筹
 */
@RestController
// @CrossOrigin(origins = "*" , maxAge = 3600) 跨域 只在这个类里面
public class RaiseFundingController {

      @Autowired
      IRaiseDataBaseService raiseDataImp;

      @Autowired
      IRaiseRedisService redisServiceImp;

      @Autowired
      RedisOperation redisOperation;

      @GetMapping("gethtmldata")
      public ResultEntity getTtmlData(){
          return null;
      }

    /**
     * @param memberSignToken 用于验证用户是否登录
     * @return
     */
    @PostMapping("raisefunding/createProjectVO")
    public ResultEntity<ProjectVO> initCrestion(
            @RequestParam("memberSignToken") String memberSignToken ){
        System.out.println("进入了这个方法了 。。。。。");
        // 这里没有验证用户登录

        return redisServiceImp.initProjectVOToRedis();
    }

    @PostMapping("raisefunding/uploadheadpicture")
    public  ResultEntity<String> saveHeadPicture(@Param("headFile")MultipartFile headFile){

            return  null;
    }


    /**
     *  将start-step -1 里面的信息放入到 projectvo里面
     * @param projectVOFront 页面一的vo 数据
     * @return  ResultEntity<String>
     */
    @RequestMapping("raisefunding/saveinfostepone")
    public  ResultEntity<String> saveProjectInfo(@RequestBody ProjectVO projectVOFront ){
        // 从 projectVOFront 获取 projectTempToken
        String projectTempToken = projectVOFront.getProjectTempToken();
        // 判断是否是失败的状态
        ResultEntity<String> resultEntity = redisOperation.readRedisValueByKey(projectTempToken);
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        return  redisServiceImp.saveProjectInfoToRedis(projectVOFront,resultEntity);
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
        return redisServiceImp.saveProjectReturnToRedis(returnVO,resultEntity);
    }
    /**
     * 保存用户信息
     * @param memberConfirmInfoVO
     * @return
     */
    public  ResultEntity<String> saveMemberConfirmInfo(
            @RequestBody MemberConfirmInfoVO memberConfirmInfoVO){
        // 得到 project
        String proToken = memberConfirmInfoVO.getProjectTempToken();
        ResultEntity<String> resultEntity  = redisOperation.readRedisValueByKey(proToken);
        // 判断状态
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        return redisServiceImp.saveMemberConfiInfoToRedis(memberConfirmInfoVO,resultEntity);
    }
    /**
     *  保存 projectpro到数据库
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

        if (ResultEntity.FAILED.equals(resultEntity.getResult())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }
        // 得到 JSON数据，转化 成peojectpo对象
        ProjectVO projectVO = JSON.parseObject(resultEntity.getData(),ProjectVO.class);

        // 调用srevice 保存到数据库
        return raiseDataImp.saveAllProToDatabase(projectVO, 17);
    }
}
