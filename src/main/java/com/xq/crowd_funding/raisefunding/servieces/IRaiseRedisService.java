package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRaiseRedisService {
    // 初始化  ProjectVO 放入 redis
    ResultEntity<ProjectVO> initProjectVOToRedis(String userToken);

    // 保存信息放入redis
    ResultEntity<String> saveProjectInfoToRedis(
            ProjectVO projectVOFront,ResultEntity resultEntity);

    // 保存头图片到redis
    ResultEntity<String> saveHeadPicture(UserToken userToken, String headFilePath);

    // 保存详情图片
    ResultEntity<String> saveDetailPicture (UserToken userToken, List<String> detailPicturePath);

    // 保存 回报放入redis
    ResultEntity<Object> saveProjectReturnToRedis(
            ReturnVO returnVO,ResultEntity resultEntity);

    // 保存用户信息放入 redis
    ResultEntity<String>  saveMemberConfiInfoToRedis(
            MemberConfirmInfoVO memberConfirmInfoVO,ResultEntity resultEntity);

    // 根据 回报 id 删除回报信息
    ResultEntity<Object> removeReturnById(ResultEntity<String> resultEntity, Integer listid);

    ResultEntity saveReturnsayFileToRedis(MultipartFile sayFile, UserToken userToken);
}
