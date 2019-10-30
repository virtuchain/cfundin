package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.raisefunding.beans.vo.MemberConfirmInfoVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;

public interface IRaiseRedisService {

    ResultEntity<ProjectVO> initProjectVOToRedis();

    ResultEntity<String> saveProjectInfoToRedis(
            ProjectVO projectVOFront,ResultEntity resultEntity);

    ResultEntity<String> saveProjectReturnToRedis(
            ReturnVO returnVO,ResultEntity resultEntity);

    ResultEntity<String>  saveMemberConfiInfoToRedis(
            MemberConfirmInfoVO memberConfirmInfoVO,ResultEntity resultEntity);
}
