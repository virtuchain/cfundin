package com.xq.crowd_funding.realname.controller;


import com.xq.crowd_funding.common.pojo.TMember;
import com.xq.crowd_funding.realname.service.TMemberService;
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
    @ResponseBody
    public void UpdateReName(HttpServletRequest request){

        //获取用户信息
        HttpSession session=request.getSession();

        TMember Member=(TMember) session.getAttribute("member");

        TMember tMember = tMemberService.queryById(Member.getId());
        //调用修改方法
        int update=tMemberService.update(tMember);

        if(update>0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
}
 