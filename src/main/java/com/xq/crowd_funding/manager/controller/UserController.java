package com.xq.crowd_funding.manager.controller;


import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TUser;
import com.xq.crowd_funding.common.utils.Const;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.common.utils.MD5Utils;
import com.xq.crowd_funding.common.utils.Page;
import com.xq.crowd_funding.manager.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Maozhihao
 * 用户控制层
 */
@RestController
@RequestMapping("/manager")
public class UserController {

    @Autowired
    private TUserService tUserService;

    //==================分页查询======================================================
    @RequestMapping("/index")
    @ResponseBody
    public Page index(@RequestParam(value = "pageno")Integer pageno,
                      @RequestParam(value = "pagesize")Integer pagesize, TUser pojo){
        //调用查询page类里的内容
        Page page=tUserService.queryPage(pageno,pagesize,pojo);
        //返回page数据
        return page;
    }
//===========================模糊查询=============================================================
    @PostMapping("/queryLike")
    @ResponseBody
    public ResultEntity querylike(String queryText) {
        //判断模糊查询条件在空的条件下
        System.out.println("queryText======"+queryText);
        if (queryText == null || "".equals(queryText)) {
            return ResultEntity.failed("请输入查询内容");
        } else {
            System.out.println("进入控制层");
            List<TUser> tUserList = tUserService.queryLike(queryText);
            System.out.println(tUserList);
            return ResultEntity.successWithData(tUserList);
        }
    }
    //==========================删除用户==================================================
    @DeleteMapping("/deleteUser")
    @ResponseBody
    public  ResultEntity<String> deleteUser(TUser pojo){
        //调用删除方法
       int i=tUserService.deleteUsers(pojo);
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("失败");
        }
    }
    //=========================批量删除================================================
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
    //=========================新增用户======================================================
    @PostMapping("/insertUser")
    @ResponseBody//添加用户
    public ResultEntity<String> insertUser(TUser pojo){
        //获取当前时间
        pojo.setCreatetime(CrowdUtils.returnDateStr());
        //密码进行MD5加密
        pojo.setUserpswd(MD5Utils.digest(Const.PASSWORD));
        //调用添加方法
       int i = tUserService.insert(pojo);
       if ( i >= 1 ){
           return ResultEntity.successNoData();
       }else{
           return ResultEntity.failed("失败");
       }
    }
    //========================修改用户信息============================================
    @RequestMapping("/UpdateUser")
    @ResponseBody
    public  ResultEntity toUpdate(TUser pojo){
        //调用修改方法
       int i=tUserService.update(pojo);
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("失败");
        }
    }
}
