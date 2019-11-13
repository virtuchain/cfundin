package com.xq.crowd_funding.manager.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TPermission;
import com.xq.crowd_funding.manager.service.Imp.TPermissionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Maozhihao
 **/
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    TPermissionServiceImp tPermissionServiceImp;
    //==================删除节点====================================
    @PostMapping("/deletepermission")
    public ResultEntity delete(TPermission pojo){
        System.out.println("id:"+pojo);
        int i=tPermissionServiceImp.delete(pojo);
        if (i>=1){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("删除失败");
        }
    }
    //==================修改树形结构节点============================
    @PostMapping("/doUpdate")
    public  ResultEntity updata(TPermission pojo){
        int i = tPermissionServiceImp.update(pojo);
        if (i>=1){
            return ResultEntity.successNoData();
        }else {
            return ResultEntity.failed("查询失败");
        }
    }
    //==================修改前数据回显==============================
    @RequestMapping("/toUpdate")
    public ResultEntity toUpdata(Integer id, Map map){
        TPermission tPermission=tPermissionServiceImp.getPermissionById(id);
        if (tPermission!=null){
            return ResultEntity.successWithData(tPermission);
        }else {
            return ResultEntity.failed("查询失败");
        }
    }
    //==================添加树形结构节点============================
    @PostMapping("/toadd")
    @ResponseBody
    public ResultEntity toadd(TPermission pojo){
        //传递数据调用添加方法
        System.out.println("传递进来的pojo"+pojo);
        int i = tPermissionServiceImp.insert(pojo);
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else{
            return ResultEntity.failed("添加许可失败");
        }
    }
    //=================加载树形结构数据=============================
    @PostMapping("/loadData")
    @ResponseBody
    public ResultEntity loadData(){
        //定义一个集合对象
        List<TPermission> root=new ArrayList<>();
        //父：调用查询根节点方法；
        TPermission permission=tPermissionServiceImp.getRootPermission();
        //默认展开元素
        permission.setOpen(true);
        //将查到的父节点装入
        root.add(permission);
        //根据父节点id查询子节点
        List<TPermission> children=tPermissionServiceImp.getChildrenPermissionByPid(permission.getId());
        //设置父子关系
        permission.setChildren(children);
        for (TPermission child : children) {
            //默认打开父节点
            child.setOpen(true);
            //查询叶子节点
            List<TPermission> innerChildren=tPermissionServiceImp.getChildrenPermissionByPid(child.getId());
            //将叶子节点装入子节点中
            child.setChildren(innerChildren);
        }
        return ResultEntity.successWithData(root);
    }
}
