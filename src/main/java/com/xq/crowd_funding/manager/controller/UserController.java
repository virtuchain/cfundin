package com.xq.crowd_funding.manager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.Const;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.common.utils.MD5Utils;
import com.xq.crowd_funding.manager.bean.TUser;
import com.xq.crowd_funding.manager.service.TUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maozhihao
 * 用户控制层
 */
@RestController
@RequestMapping("/manager")
public class UserController {
    @Autowired
    private TUserService tUserService;


   @GetMapping("/user") //查询所有用户方法
   @ResponseBody
    public PageInfo<TUser> queryUsers(TUser pojo){
       PageHelper.startPage(1,10);
       List<TUser> list = tUserService.select(pojo);
       PageInfo<TUser> p=new PageInfo<TUser>(list);
       return p;
    }
    @GetMapping("/likeLoginacct")//模糊查询用户
    @ResponseBody
    public ResultEntity<String> likeUser(){
       return null;
    }
    @DeleteMapping("/deleteUser")
    @ResponseBody
    public  ResultEntity<String> deleteUser(TUser pojo){
        System.out.println("id"+pojo);
       int i=tUserService.delete(pojo);
        System.out.println("删除数据条数"+i);
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("失败");
        }
    }
    @DeleteMapping("/deleteUsers")
    @ResponseBody
    public  ResultEntity<String> deleteUsers(Integer[] id){
        Integer index=0;//定义一个数组长度
        int count=0;//累计删除条数
        //循环删除传递进来的用户id
        if (id==null){
            return ResultEntity.failed("请选中要删除用户~");//如果没有选中删除用户
        }
        for (Integer ids : id) {
            TUser pojo=new TUser();
            pojo.setId(id[index]);
            System.out.println("删除的用户id为"+id[index]);
            int i=tUserService.deleteUsers(pojo);
            index++;
            count+=i;
        }
        if ( count == id.length){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("删除失败~");
        }
    }
    @PostMapping("/insertUser")
    @ResponseBody//添加用户
    public ResultEntity<String> insertUser(TUser pojo){
        pojo.setCreatetime(CrowdUtils.returnDateStr());//获取当前时间
        pojo.setUserpswd(MD5Utils.digest(Const.PASSWORD));//进行MD5加密
       int i = tUserService.insert(pojo);
       if ( i >= 1 ){
           return ResultEntity.successNoData();
       }else{
           return ResultEntity.failed("失败");
       }
    }

    @RequestMapping("/UpdateUser")
    @ResponseBody//修改用户信息
    public  ResultEntity toUpdate(TUser pojo){
       int i=tUserService.update(pojo);
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("失败");
        }
    }
}
