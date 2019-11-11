package com.xq.crowd_funding.login.alxxUtil;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendCode {
    static  final  String product="Dysmsapi";
    static  final  String domain="dysmsapi.aliyuncs.com";
    static  final  String accesssKeyId="LTAI4FjcM4yPMfGbj4LAe5MM";
    static  final  String getAccesssKeySecret="8S7c4pojStoCXC86gCqqcuOiilWTzw";
    public static SendSmsResponse sendSms(String phone, String code)throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout","5000");
        System.setProperty("sun.net.client.defaultReadTimeout","5000");
        IClientProfile profile= DefaultProfile.getProfile("cn-hangzhou",accesssKeyId,getAccesssKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",product,domain);
        IAcsClient acsClient=new DefaultAcsClient(profile);
        SendSmsRequest request=new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("三全小同");
        request.setTemplateCode("SMS_176927886");
        request.setTemplateParam("{\"code\":\""+code+"\"}");
        SendSmsResponse sendSmsResponse=acsClient.getAcsResponse(request);
        return sendSmsResponse;
}

}
