package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/

import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.Const;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yj
 * @date 2019-11-05
 * @description 发起 众筹 页面 二 的 控制层
 * @version V1.0
 *
 */
@RestController
public class RaiseTwoTepController extends  ConParentProperties {

    /**
     * 这里是将回报加入redis
     * @param returnVO
     * @return  ResultEntity<String>
     */
    @PostMapping("raisefunding/savereturn")
    public ResultEntity<Object> saveProjectReturn(
            ReturnVO returnVO, HttpServletRequest request){

        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        //  从redis取出 resultEntity
        ResultEntity<String> resultEntity  =
                redisOperation.readRedisValueByKey(userToken.getRaiseToken());

        // 判断状态
        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }

        return redisServiceImp.saveProjectReturnToRedis(returnVO, resultEntity);
    }

    /**
     * 保存回报里面的图片到 redis
     * @param sayFile
     * @param request
     * @return
     */
    @PostMapping("raisefunding/uploadsayFile")
    public  ResultEntity loadsayFile(
            @RequestParam("headFile") MultipartFile sayFile, HttpServletRequest request){

        // 排除空文件
        if (sayFile.isEmpty()){
            return  ResultEntity.failed("文件为空");
        }
        ResultEntity<String> resultEntity = pictureSerImp.uploadHeadPicture(sayFile);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())){
            return ResultEntity.failed("上传失败");
        }

        //  得到session
        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        return redisServiceImp.saveReturnsayFileToRedis(sayFile, userToken);
    }

    /**
     * 加载 start-2 页面的返回list集合
     * @param request
     * @return
     */
    @GetMapping("raisefunding/loaderstartTwo")
    public  ResultEntity loadStartTwo(HttpServletRequest request){
        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);
        if (userToken == null){
            return   ResultEntity.failed(Const.RAISE_LOGIN);
        }
        ResultEntity<String> entity = redisOperation.readRedisValueByKey(userToken.getRaiseToken());
        ProjectVO projectVO = JSON.parseObject(entity.getData().toString(),ProjectVO.class);
        return ResultEntity.successWithData(projectVO.getReturnVOList());
    }


    /**
     * 根据 回报信息的 id 删除
     */
    @GetMapping("raisefunding/removereturnbyid")
    public  ResultEntity removeReturnById(Integer listid,HttpServletRequest request){


        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        if (userToken == null){
            ResultEntity.failed("无法获取ID");
        }

        ResultEntity<String> resultEntity =
                redisOperation.readRedisValueByKey(userToken.getRaiseToken());

        if (ResultEntity.FAILED.equals(resultEntity.getResult())){
            return   ResultEntity.failed(Const.RAISE_LOGIN);
        }
        return  redisServiceImp.removeReturnById(resultEntity, listid);
    }

}
