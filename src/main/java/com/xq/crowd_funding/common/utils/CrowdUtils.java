package com.xq.crowd_funding.common.utils;/*
    @auther yangjie
*/

/**
 *  众筹项目一些常用的工具
 */
public class CrowdUtils {

    /**
     * 判断一个字符串是否有效，不为 null 长度大于 0
     * @param str
     * @return
     * 有效 返回true
     * 无效 返回 false
     */
    public static boolean strEffectiveCheck(String str){
         return (str !=null) && (str.length()>0) ;
    }
}
