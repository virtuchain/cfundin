package com.xq.crowd_funding.realname.controller;


import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TMember;
import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.realname.service.TMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/realname")
public class TMemberController {

    @Autowired
    private TMemberService tMemberService;

    @RequestMapping("/UpdateReName")
    public ResultEntity UpdateReName(HttpServletRequest request){

        //获取用户信息
        UserToken userToken = TokenKeyUtils.getUserTokenByRequest(request);

        TMember tMember = new TMember();
        tMember.setId(userToken.getId());
        //调用修改方法
        int update=tMemberService.update(tMember);

        if(update>0){
            System.out.println("修改成功");
            return  ResultEntity.successNoData();
        }else{
            System.out.println("修改失败");
            return ResultEntity.failed("修改失败");
        }
    }
}
 