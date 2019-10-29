package com.xq.crowd_funding.common.utils;/*
    @auther yangjie
*/

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

    /**
     * 判断集合类是否是有效的集合
     * *有效 返回true
     *  无效 返回 false
     */
    public static  <E> boolean conllectionCkeck(Collection<E> c){
        return (c !=null) && (c.size()>0) ;
    }

    /**
     * 返回 一个现在的日期字符
     * @return
     */
    public  static String returnDateStr(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
        return  simpleDateFormat.format(date);
    }
}
