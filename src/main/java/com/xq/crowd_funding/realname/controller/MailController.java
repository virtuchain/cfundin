package com.xq.crowd_funding.realname.controller;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.MailsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.InvocationTargetException;

@Controller
public class MailController {

    @RequestMapping("realname/sendMails")
    @ResponseBody
    public  ResultEntity<String> sendMailsDoMain(
            String text, HttpServletRequest request) throws InvocationTargetException {

        System.out.println("text: " + text);

        try {
            String status = MailsUtils.sendMailUtils(text);
            if (status.equals("error")) {
                return ResultEntity.failed("验证码发送失败");
            }
            request.getSession().setAttribute("yzmvalues", status);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultEntity.successWithData("验证码发送成功");
    }

    @ResponseBody
    @PostMapping("email/yanzheng")
    public ResultEntity  yanzheng(String yzm,HttpServletRequest request){
        String yzmvalues = (String) request.getSession().getAttribute("yzmvalues");
        System.out.println("yzm "+yzm +"  yzmvalues "+yzmvalues);
        if (yzm.equals(yzmvalues)){
            // 修改数据库
            return ResultEntity.successNoData();
        }
        return ResultEntity.failed("验证码错误");
    }
}
 