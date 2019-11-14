//package com.xq.crowd_funding.common.utils;
//
//import com.mysql.cj.Session;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.GeneralSecurityException;
//import java.util.Properties;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//@Controller
//public class MailsUtils {
//
//        private static final String PORT="25";
//        private static final String AUTH="true";
//        private static final String HOST="smtp.163.com";
//        private static final String UserName="mbsdxt@163.com";
//        private static final String PassWord="mbsdxt102910";
//
//        public static void main(String[] args){}
//
//        public static void sendMail(String address)throws MessagingException {
//
//                Properties properties=new Properties();
//                //设置属性
//                properties.setProperty("mail.smtp.port","PORT");
//                properties.setProperty("mail.smtp.host","HOST");
//                properties.setProperty("mail.smtp.auth","AUTH");
//                //获取邮箱session
//                Session session=Session.getInstance(properties, new Authenticator() {
//                        @Override
//                        protected PasswordAuthentication getPasswordAuthentication() {
//                                return new PasswordAuthentication(UserName,PassWord);
//                        }
//                });
//                //获取message对象
//                Message message=new MimeMessage(session);
//                //设置标题
//                message.setSubject("邮箱验证服务");
//                //设置发件人
//                message.setFrom(new InternetAddress(UserName));
//                //设置收件人
//                message.setRecipient(Message.RecipientType.TO,new InternetAddress(address));
//                //设置内容
//                message.setContent("你本次的验证码为","text/html;charset=utf-8");
//                //发送邮箱
//                Transport.send(message);
//
//
//        }
//
//        @ResponseBody
//        @RequestMapping("/sendMailUtils")
//        public String sendMailUtils(@RequestParam("text") String address)throws MessagingException, GeneralSecurityException{
//            System.out.println(address);
//            MailsUtils.sendMail(address);
//            return "SUCCESS";
//        }
//
//}
