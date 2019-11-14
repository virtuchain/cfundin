package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/

import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.configrations.redisconfigration.RedisOperation;
import com.xq.crowd_funding.common.pojo.TTag;
import com.xq.crowd_funding.common.pojo.TType;
import com.xq.crowd_funding.common.utils.Const;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseDataBaseService;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseRedisService;
import com.xq.crowd_funding.raisefunding.servieces.impl.PictureSerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 发起众筹
 */
@RestController
// @CrossOrigin(origins = "*" , maxAge = 3600) 跨域 只在这个类里面
public class RaiseFundingController {

    // 操作数据库
      @Autowired
      private IRaiseDataBaseService raiseDataImp;
    // 操作 redis
      @Autowired
      private IRaiseRedisService redisServiceImp;
      // redis的方法
      @Autowired
      private RedisOperation redisOperation;
      // 上传图片
      @Autowired
      private PictureSerImpl pictureSerImp;

    /**
     * 获取页面的数据信息
     * @return
     */
      @GetMapping ("raisefunding/gethtmldata")
      public ResultEntity getTtmlData(){
         List<TType>  typeList =  raiseDataImp.getHtMalDataToMap();
          return ResultEntity.successWithData(typeList);
      }
    /**
     * 选中按钮，找到 type 对应的 标签
     */
    @PostMapping("raisefunding/gettag")
    public  ResultEntity getTag(@RequestBody List<Integer> typeIdArray){
        Set<TTag> tagByTypeId = raiseDataImp.getTagByTypeId(typeIdArray);
        return  ResultEntity.successWithData(tagByTypeId);
    }


    /**
     *  创建  ProjectVO 所有页面的数据存放的对象
    // * @param memberSignToken 用于验证用户是否登录
     * @return
     */
    @PostMapping("raisefunding/createProjectVO")
    public ResultEntity initCrestion(HttpServletRequest request){

        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        if (userToken == null){
            ResultEntity.failed(Const.RAISE_LOGIN);
        }
        // 创建 peojectvo 返回 peojectvo
        ResultEntity<ProjectVO> projectVOResultEntity =
                redisServiceImp.initProjectVOToRedis(userToken.getUsertoken());

         // 得到  peojectvo 里面的  Token
        String projectToken =  projectVOResultEntity.getData().getProjectTempToken();
        //  Token 放入  userToken
        userToken.setRaiseToken(projectToken);

        System.out.println("初始化后返回的userToken "+userToken.toString());

        //  userToken 放入 session
        request.getSession().setAttribute("userToken",userToken);
        // 成功返回
        return ResultEntity.successNoData();
    }
    /**
     * 上传头图片
     * @param headFile
     * @return
     */
    @PostMapping("raisefunding/uploadheadpicture")
    public  ResultEntity<String> saveHeadPicture(
            @RequestParam("headFile")MultipartFile headFile,HttpServletRequest request){

         // 排除空文件
        if (headFile.isEmpty()){
            return  ResultEntity.failed("文件为空");
        }
        ResultEntity<String> resultEntity = pictureSerImp.uploadHeadPicture(headFile);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())){
            return ResultEntity.failed("上传失败");
        }
        // 头图片的储存路径
        String headPicturePath = (String) resultEntity.getData();

        //  得到session
        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        // 将 路径 存入 redis
        return  redisServiceImp.saveHeadPicture(userToken,headPicturePath);
    }
    /**
     * 上传详情图片
     */
    @PostMapping("raisefunding/uploaddetailpicture")
    public  ResultEntity<String> saveDetailPicture(
            @RequestParam("detailFiles") List<MultipartFile> detailFiles,HttpServletRequest request){

        // 排除空文件
        if (detailFiles.isEmpty()){
            return  ResultEntity.failed("文件为空");
        }

        ResultEntity resultEntity = pictureSerImp.uploadDetailPicture(detailFiles);

        if (ResultEntity.FAILED.equals(resultEntity.getResult())){
            return ResultEntity.failed("上传失败");
        }

        // 得到详情图片的存储路径
        List<String> detailicturePathList  = (List<String>) resultEntity.getData();

        //  得到session
        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        // 将 路径 存入 redis
        return  redisServiceImp.saveDetailPicture(userToken,detailicturePathList);
    }

    /**
     *  将start-step -1 里面的信息放入到 projectvo里面
     * @param projectVOFront 页面一的vo 数据
     * @return  ResultEntity<String>
     *
     */
    @PostMapping("raisefunding/saveinfostepone")
    public  ResultEntity<String> saveProjectInfo(
            ProjectVO projectVOFront,HttpServletRequest request){

        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        if (userToken == null){
            ResultEntity.failed(Const.RAISE_LOGIN);
        }

        // 判断是否是失败的状态
       ResultEntity<String> resultEntity =
               redisOperation.readRedisValueByKey(userToken.getRaiseToken());

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
    @PostMapping("raisefunding/savereturn")
    public  ResultEntity saveProjectReturn(ReturnVO returnVO){
        System.out.println("returnVO "+returnVO.toString());
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
