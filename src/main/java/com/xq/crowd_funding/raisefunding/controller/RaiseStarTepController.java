package com.xq.crowd_funding.raisefunding.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.Const;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yj
 * @date 2019-11-05
 * @description 发起众筹 开始 的 控制层
 * @version V1.0
 *
 */
@RestController
public class RaiseStarTepController extends  ConParentProperties{

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


        //  userToken 放入 session
        request.getSession().setAttribute("userToken",userToken);

        // 成功返回
        return ResultEntity.successNoData();
    }

}
