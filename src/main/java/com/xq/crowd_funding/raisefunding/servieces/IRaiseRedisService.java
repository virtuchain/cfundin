package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;

public interface IRaiseRedisService {
    // 初始化  ProjectVO 放入 redis
    ResultEntity<ProjectVO> initProjectVOToRedis();

    // 保存信息放入redis
    ResultEntity<String> saveProjectInfoToRedis(
            ProjectVO projectVOFront,ResultEntity resultEntity);

    // 保存头图片到redis
    //ResultEntity<String> saveHeadPicture();

    // 保存 回报放入redis
    ResultEntity<String> saveProjectReturnToRedis(
            ReturnVO returnVO,ResultEntity resultEntity);

    // 保存用户信息放入 redis
    ResultEntity<String>  saveMemberConfiInfoToRedis(
            MemberConfirmInfoVO memberConfirmInfoVO,ResultEntity resultEntity);
}
