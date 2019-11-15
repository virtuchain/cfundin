package com.xq.crowd_funding.login.bean.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.login.alxxUtil.SendCode;
import com.xq.crowd_funding.login.bean.pojo.UserToken;
import com.xq.crowd_funding.login.bean.service.IService;
import com.xq.crowd_funding.partfinancing.bean.TMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller()
@RequestMapping("/reg")
public class RegController {
@Autowired
    private  IService TMemberService;
    @RequestMapping("/on1")
    @ResponseBody
    public Object subb(String loginacct,String password,String phone,String usertype,String email) {
        Map<String ,Object> resultMap=new HashMap<>();
        TMember tMember = new TMember();
        tMember.setLoginacct(loginacct);//账号
        tMember.setUserpswd(password);//密码
        tMember.setUsername("张三");//名字添加为定值
        tMember.setEmail(email);//email
        tMember.setAuthstatus("1");//实名认证状态
        tMember.setUsertype(usertype);//用户类型/用户。管理员
        tMember.setPhone(phone);
        System.out.println("tmenber "+tMember.toString());
        TMemberService.insert(tMember);
            try {
                System.out.println("注册成功");

            } catch (Exception e) {
                e.printStackTrace();
            }
        resultMap.put("status","success");
        return resultMap;
    }


    @RequestMapping("/on2")
    @ResponseBody
    public Object getvcode(String phone)throws ClientException {
        Map<String ,Object> resultMap=new HashMap<>();
        System.out.println("账号" + phone);
        String code="123456";
//        String code =getMsgCode();
//        SendSmsResponse response=SendCode.sendSms(phone,code);
        System.out.println("---------------短信返回数据"+code);
//        System.out.println("getCode"+response.getCode());
//        System.out.println("getBizId"+response.getBizId());
//        System.out.println("getMessage"+response.getMessage());
//        System.out.println("getRequestId"+response.getRequestId());
        resultMap.put("status","success");
        resultMap.put("code",code);
        return resultMap;
    }
    @RequestMapping("/on3")
    @ResponseBody
    public Object getUser(HttpServletRequest request){
        Map<String,Object> resultMap=new HashMap<>();
        HttpSession session = request.getSession();
        UserToken token= (UserToken)session.getAttribute("userToken");
        if(token!=null && token.getLoginacct()!=null) {
            String logginacct = token.getLoginacct();
            String username = TMemberService.selectUsername(logginacct);
            System.out.println("查到的username" + username);
            if (username != null) {
                resultMap.put("status", "success");
                resultMap.put("username", username);
            } else if (username == null) {
                resultMap.put("status", "false");
            }
        }else {
            resultMap.put("status", "false");
        }
        return resultMap;

    }
    @RequestMapping("/on4")
    @ResponseBody
    public Object getPhone(String phone){
        Map<String,Object> resultMap=new HashMap<>();
        String   Phone=TMemberService.selectPhone(phone);
        System.out.println("查到的phone"+Phone+","+phone);

        if(Phone==null){
            resultMap.put("status","false");
        }else {
            resultMap.put("status","success");
        }

        return resultMap;

    }
    @RequestMapping("/on5")
    @ResponseBody
    public ResultEntity<String> goback(HttpServletRequest request){
      HttpSession session=request.getSession();
      session.removeAttribute("userToken");
       // session.setMaxInactiveInterval(1);
        return   ResultEntity.successWithData("success");
    }
    //六位数的随机数
    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
