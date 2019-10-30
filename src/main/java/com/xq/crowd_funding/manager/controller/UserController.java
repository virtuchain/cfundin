package com.xq.crowd_funding.manager.controller;

import com.github.pagehelper.PageHelper;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.manager.bean.TUser;
import com.xq.crowd_funding.manager.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maozhihao
 * 用户控制层
 */
@RestController
public class UserController {
    @Autowired
    private TUserService tUserService;

   @GetMapping("/user") //查询所有用户方法
    public List<TUser> queryUsers(TUser pojo){

       PageHelper.startPage(1,10);

       List<TUser> list = tUserService.select(pojo);

       return list;
    }
    @PostMapping("/insertUser")
    @ResponseBody//添加用户
    public Map<String,Object> insertUser(TUser pojo){
        pojo.setCreatetime(CrowdUtils.returnDateStr());
       int i = tUserService.insert(pojo);
        System.out.println(i);
       Map<String,Object> map=new HashMap();
       if ( i >= 1 ){
           map.put("status", 200);
           System.out.println(map);
       }else{
           map.put("status",400);
           System.out.println(map);
       }
       return map;
    }
}
