package com.xq.crowd_funding.login.bean.controller;

import com.xq.crowd_funding.login.bean.service.IService;
import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/reg")
public class RegController {

    IService service;
    @RequestMapping("/on1")
    public String subb(String loginacct,String password,String phone,String usertype,String email){
        TMember tMember=new TMember();
          tMember.setLoginacct(loginacct);//账号
          tMember.setUserpswd(password);//密码
          tMember.setUsername("张三");//名字添加为定值
          tMember.setEmail(email);//email
          tMember.setAuthstatus("1");//实名认证状态
          tMember.setUsertype(usertype);//用户类型/用户。管理员
        service.insert(tMember);
        System.out.println("注册成功");
        return "/views/login.html";
    }
    @RequestMapping("/on2")
    public String getvcode(String phone){
        System.out.println("账号" + phone);

        return null;
    }
}
