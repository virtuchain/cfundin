package com.xq.crowd_funding.partfinancing.alipay;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
ClassName: AlipayConfig
 支付宝沙箱支付配置类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:14
@Time: 2019/11/11
@Version: 1.0
*/
@Data
@Component
public class AlipayConfig {
    // 作为身份标识的应用ID
    public static String app_id = "2016092800619153";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCa4SXqfJjPtVfdX0uAGvLmahb42TIkFpsUJ0tLIi0o5OAN3uZOLp7EJGcUwX4HCFs2r/mNpzqLm1vWuAbvBI5bADHjaSiDQTiLmCEBsmgFBXB2qZECBgo/GIT2c0Yv4a0Z/AAVydEaV20Hv3B/h41A0Hwk2t4GvWWO5IQOd55pfp4uj62mtIC3rdcM1wuVNXjhitu7yAwUr7XRaRd6f8y1I97Xk/lD5EKu4tpJnosr8vZZjue/0hvmUH1dfT0q/DiyHZAFGbkqtL4COuMgoeJ7cUGyLEFXLqGlWFlHzEfDhmNT7JCXhWsw0l9D0/KutSv6XMZnQ91Hfe+sfKyrI4pLAgMBAAECggEAWG6t45F4/0AgBzpeNastffqdW0FIq+psabl1+FRvTKZCeCkJfyKHbJ8MbIF+oC1ZR4R7DTKq7wOqEJPLAbasQbF+ysZEZ41Z5/rY/csMMbHFYTu4nFsuhpBNexqevMAywl9s/QPa7Rt2GbJpXafcM2HXQIPnuhlyX4CDFm+ed8T7XVboMCQaZk5iPEaXWVfNoZPcavAIJ6hbRv2k2ttbnozkvhRk3RJiCUPdfAD6LzGvUtxfbRvAveJdQ5dxEN+hXFnVOnyB8WBlnTPL4gNDu/VFG+rLd1F7UFEiLT0yM2V2swzrUlw9dime1mlTFVqmK3mMTbq3a412sy/wW9K9QQKBgQDe7dEkBaiGDdVI0WRUKwOmRxle81WyIBqWIo9thp+mb26DgF9lUqGMNSsRtOlAVU26QexSHLQIz0aomnslbr+eFbybckee5jkxxNUmoLVw2Knf/iOsp4srV3FJw84LNG1VBdOtUtEk4W/zqiuHf+zKU0k6dDwgQ1lavLfhWtOq4wKBgQCx2wNLp126gNkEYFSeE0L80W/U9h2prAHFMPb5D8JU/62CA7T0Xees5m0c5LvtVLeUV0LKjREeRWahvWu+oe8lRQj3R80ehgiDFsaS5Hf3X8hHA6ObplPy25GAG0V4uwxmHbDpZy/pQKQW7rwVfAW8tX/p7QRBT4cNnKufF+s3eQKBgDn5GIFIo2mxIT0djvzAbuOH69Yga7G13ZQbircN4miy48d409YSpgZDMV8ab+oWBO+iAyXbZwo/dP2Vpt62LUicGH9IOc3dyxPhPxkbg/PDtHGWsopMy8lKpSBstiF90czhhgm11sPDiIySPV2wa9C11f5IifZN7WfGWf9V0mPHAoGAdUqOPdI+ScS3Wd99blIdl0U3jyZQIZlCAZ1tAouWCSAkeN2g9JcJNkkwJCHknXuFJdr2PUYSx/fU201kRO/eGqIQa6N6bmrOKkVW54CsbQNJvSt1NizWqA6NQgK6KgiRALcL+KEIQ7dRf54pg7GqxuybUtXwPHIKCrwLonvPr2ECgYEA3H8sgyc2dVhvfJOgpQM6kndZlYlDtJZxIeUN+JHitkmGC5Txi6f9FWDkEIV/K2qqcQ/QrPZdr8+ZriSirOn03aSVbj5Sd/MNTxh7XSO2Ke4iLxRqdnRyeek9PJYb973zLBYo4nWvcVjc2O8an2l6beu5uX8jiHCfiNmXnQHbAjA=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/views/project.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/views/project.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
