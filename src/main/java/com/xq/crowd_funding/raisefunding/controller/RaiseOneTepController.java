package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TTag;
import com.xq.crowd_funding.common.pojo.TType;
import com.xq.crowd_funding.common.utils.Const;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @author yj
 * @date 2019-11-05
 * @description 发起 众筹 页面 一的 控制层
 * @version V1.0
 *
 */
@RestController
public class RaiseOneTepController extends  ConParentProperties{

    /**
     * 获取页面的数据信息
     * @return
     */
    @GetMapping("raisefunding/gethtmldata")
    public ResultEntity getTtmlData(){
        List<TType> typeList =  raiseDataImp.getHtMalDataToMap();
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
     *  将start-step -1 里面的信息放入到 projectvo里面
     * @param projectVOFront 页面一的vo 数据
     * @return  ResultEntity<String>
     *
     */

    @PostMapping("raisefunding/saveinfostepone")
    public  ResultEntity<String> saveProjectInfo(
            ProjectVO projectVOFront, HttpServletRequest request){

        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        if (userToken == null){
            ResultEntity.failed(Const.RAISE_LOGIN);
        }

        // 判断是否是失败的状态
        ResultEntity<String> resultEntity = redisOperation.readRedisValueByKey(userToken.getRaiseToken());

        if (ResultEntity.FAILED.equals(resultEntity.getMessage())){
            return  ResultEntity.failed(resultEntity.getMessage());
        }

        projectVOFront.setMemberSignToken(userToken.getUsertoken());
        projectVOFront.setProjectTempToken(userToken.getRaiseToken());

        return  redisServiceImp.saveProjectInfoToRedis(projectVOFront, resultEntity);
    }


    /**
     * 上传头图片
     * @param headFile
     * @return
     */
    @PostMapping("raisefunding/uploadheadpicture")
    public  ResultEntity<String> saveHeadPicture(
            @RequestParam("headFile") MultipartFile headFile, HttpServletRequest request){

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


}
