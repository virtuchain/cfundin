package com.xq.crowd_funding.common.utils;/*
    @auther yangjie
*/

import java.util.UUID;

/**
 * 这个类是用来生成唯一的token (key) 值
 */
public class TokenKeyUtils {

      // projectVO 的key前缀 , 识别唯一的对象
      public  static  final  String REDIS_RAISE_KEY_PREFIX = "RAISE_PROJECT_TONKEN";
      public  static  final  String USER_LOGIN_SUCCESS_PREFIX="LOGIN_SUCCESS_TOKEN";
      // 传入一个 token 前缀，在后面加入UUD返回
      public static String getTokenAndUUID(String token){
          return  token + (UUID.randomUUID().toString().replaceAll("-",""));
      }
}
