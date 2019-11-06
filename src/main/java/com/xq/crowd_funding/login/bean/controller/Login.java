package com.xq.crowd_funding.login.bean.controller;


import com.xq.crowd_funding.common.utils.TokenKeyUtils;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.login.bean.service.IService;
import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Login {
    /**
     * @author: lishiming
     * TODO:
     **/
    @Autowired
    private IService userService;
    @RequestMapping("login/a1")
    public Object Loginw(String loginacctt, String userpawd, Integer userType, HttpServletRequest request) {

        System.out.println("账号" + loginacctt);
        System.out.println("密码" + userpawd);
        System.out.println("usertype" + userType);
        Map<String, Object> resultMap = new HashMap<>();
            try {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(loginacctt, userpawd);
                System.out.println("token的用户名>"+token.getUsername());
                //设置用户登陆类型给service 层
                userService.setUserType(userType);
                //后台登陆
                subject.login(token);
                token.setRememberMe(true);
                TMember tMember = (TMember) subject.getPrincipal();
                resultMap.put("statuc", "success");
                resultMap.put("data", tMember);

            } catch (AuthenticationException e) {
                System.out.println("登陆失败———--》"+e.getMessage());
                String msg="你输入的账号密码有误";
                resultMap.put("statuc","error");
                resultMap.put("data", msg);
            }

        if (resultMap.get("statuc").equals("success")){
            UserToken userToken = new UserToken();
            userToken.setLoginacct(loginacctt);
            userToken.setUserpswd(userpawd);

            userToken.setUsertoken(
                    TokenKeyUtils.getTokenAndUUID(TokenKeyUtils.USER_LOGIN_SUCCESS_PREFIX));
                  HttpSession session = request.getSession();
            session.setAttribute("userToken",userToken);
        }
        return resultMap;
    }

        //------------------------------------------\
        @RequestMapping("login/on1")
        @ResponseBody
        public String ww (String loginacctt){
            String msg = "";
            System.out.println("账号" + loginacctt);

            if (loginacctt != null || loginacctt != "") {
                if ("123456".equals(loginacctt)) {
                    msg = "ok";
                } else {
                    msg = "账号有误";
                }
            } else {
                msg = "账号不能为空";
            }
            return msg;
        }
        @RequestMapping("login/on2")
        @ResponseBody
        public String www (String userpawd ){
            String msg = "";
            System.out.println("密码" + userpawd);
            if (userpawd != null || userpawd != "") {
                if ("123456".equals(userpawd)) {
                    msg = "ok";
                } else {
                    msg = "密码有误";
                }
            } else {
                msg = "密码不能为空";
            }
            return msg;
        }
    }


