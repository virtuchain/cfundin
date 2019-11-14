package com.xq.crowd_funding.raisefunding.controller;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.configrations.redisconfigration.RedisOperation;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseDataBaseService;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseRedisService;
import com.xq.crowd_funding.raisefunding.servieces.impl.PictureSerImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 控制层 公共的属性
 */
public class ConParentProperties {
    // 操作数据库
    @Autowired
    public IRaiseDataBaseService raiseDataImp;
    // 操作 redis
    @Autowired
    public IRaiseRedisService redisServiceImp;
    // redis的方法
    @Autowired
    public RedisOperation redisOperation;
    // 上传图片
    @Autowired
    public PictureSerImpl pictureSerImp;
}
