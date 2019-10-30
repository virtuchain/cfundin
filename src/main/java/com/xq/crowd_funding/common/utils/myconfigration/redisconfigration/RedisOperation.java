package com.xq.crowd_funding.common.utils.myconfigration.redisconfigration;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class RedisOperation {

     @Autowired
     private StringRedisTemplate redisTemplate;
    /**
     * 将字符串类型的键值对保存到Redis时调用的远程方法
     *  传入参数 normalKey
     *  传入参数 normalValue
     *  传入参数 timeoutMinute	超时时间（单位：分钟）
     * @return
     */
    // 保存数据
    public  ResultEntity<String> saveRedisKeyAndValue(
             String normalKey, String normalValue, Integer timeoutMinute){
         // 首先判断 key value 是否是有效字符
         if ( !CrowdUtils.strEffectiveCheck(normalKey) ||!CrowdUtils.strEffectiveCheck(normalValue)){
             // 无效字符返回失败
             return  ResultEntity.failed("无效的key 和 value");
         }
         // 获取字符串操作器
         ValueOperations<String,String> operations = redisTemplate.opsForValue();
         // 判断 timeoutMinute 是否有效
         if(timeoutMinute == null || timeoutMinute==0){
             return ResultEntity.failed("redis设置无效时间");
         }
         // 判断 timeoutMinute的值, 是否是不设置过期时间，
         if(timeoutMinute==-1){
             // 按照不设置时间保存
             try {
                 // 将 key,value 放入redis
                 operations.set(normalKey,normalValue);
             }catch (Exception e){
                 e.printStackTrace();
                 // 保存数据异常
                 return  ResultEntity.failed(e.getMessage());
             }
             // 保存不设置时间数据 返回结果
             return  ResultEntity.successNoData();
         }
         // 按照设置过期时间方式保存数据
         try {
             // 将 key,value 放入redis  TimeUnit.MINUTES 时间单位分钟
             operations.set(normalKey,normalValue,timeoutMinute, TimeUnit.MINUTES);
         }catch (Exception e){
             e.printStackTrace();
             // 保存数据异常
             return  ResultEntity.failed(e.getMessage());
         }
         // 保存设置时间数据 返回结果
         return  ResultEntity.successNoData();
     }
    /**
     * 根据key 查询对应的value
     * 传入参数 normalKey
     */
    public  ResultEntity<String> readRedisValueByKey(String normalKey){
        // 首先判断 key 是否是有效字符
        if ( !CrowdUtils.strEffectiveCheck(normalKey)){
            // 无效字符返回失败
            return  ResultEntity.failed("无效的key");
        }
        try {
            // 得到数据
            String value = redisTemplate.opsForValue().get(normalKey);

            return  ResultEntity.successWithData(value);
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
    /**
     * 根据 Key 删除对应的 value
     *  传入参数 normalKey
     */

    public  ResultEntity<String>  removeRedisByKey(String normalKey){
        // 首先判断 key 是否是有效字符
        if ( !CrowdUtils.strEffectiveCheck(normalKey)){
            // 无效字符返回失败
            return  ResultEntity.failed("无效的key");
        }
        try {
            // 删除数据
            redisTemplate.delete(normalKey);
            // 返回成功不返回数据
            return  ResultEntity.successNoData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
}
