package com.xq.crowd_funding.common.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import javax.mail.MessagingException;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

@Controller
@RequestMapping("/mail")
public class MailsUtils {

    static String str;
    static StringBuilder sb;
    static char ch;
    static String msg;
    public String  sendmsg(){
        msg = sb.toString();
        return  msg;
    }

//        //此处将不可变的或者是指定的无法改变的变量修饰为常量
         private static final String AUTH="true";
         private static final String HOST="localhost";
         private static final String UserName="service@weixin.com";
         private static final String protocol = "SMTP";
         private static final String PassWord="123";


      //  @RequestMapping("/sendMail")
        public static String sendMail(String address)throws MessagingException, GeneralSecurityException{

            //随机码
            str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            sb=new StringBuilder(6);
            for(int i=0;i<6;i++){

                ch=str.charAt(new Random().nextInt(str.length()));
                sb.append(ch);
        }
            System.out.println(sb.toString());
            String send=sb.toString();
            // 创建会话对象
            //1、获得邮箱服务的连接(会话对象)
            Properties props = new Properties();//封装数据
            props.setProperty("mail.transport.protocol",protocol );//设置发邮件的协议
            props.setProperty("mail.host", HOST);//设置发邮件的地址(smtp邮箱服务器地址)
            props.setProperty("mail.smtp.auth", AUTH);// 指定验证为true

            // 创建验证器
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    //验证邮箱用户名和密码
                    return new PasswordAuthentication("service", "123");//密码
                }
            };

            Session session = Session.getInstance(props, auth);//会话对象

            // 创建邮件对象
            Message message = new MimeMessage(session);//创建邮件对象
            message.setFrom(new InternetAddress(UserName)); // 设置发送者
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(address)); // 设置发送方式与接收者
            message.setSubject("激活邮件");//邮件主题
            message.setContent("你本次的验证码为："+send,"text/html;charset=utf-8");//设置邮件的正文

            // 邮件发送
            Transport.send(message);
            return send;
        }
        //  @ResponseBody
     //   @RequestMapping("/test")
        public static String sendMailUtils(String address)throws MessagingException, GeneralSecurityException{
            System.out.println(address);
            String sendMail = MailsUtils.sendMail(address);
            if (CrowdUtils.strEffectiveCheck(sendMail)){
                return sendMail;
            }
            return "error";
        }

}
 