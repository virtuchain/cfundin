package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.partfinancing.bean.TMember;
import com.xq.crowd_funding.partfinancing.service.ITmemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
ClassName:TmemerController
 savaPro：通过ID查询到会员详情,修改用户的手机号码和姓名
@Description: TODO
@Author: GuoXinZhang
@Date: 8:35
@Time: 2019/11/4
@Version: 1.0
*/
@RestController
public class TmemerController {

    @Autowired
    ITmemberService iTmemberService;

    @PostMapping("savaPro")
    public void savaPro(HttpServletRequest request){
        //从session中获取用户
        HttpSession session = request.getSession();
        TMember member = (TMember) session.getAttribute("member");
        //通过Id查询会员详情
        TMember tMember = iTmemberService.queryById(member.getId());
        //修改会员信息的姓名和手机
        int sava = iTmemberService.savaPro(tMember);
        if (sava>0){
            System.out.println("更改成功");
        }
        System.out.println("更改失败");
    }

}
