package com.xq.crowd_funding.manager.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TPermission;
import com.xq.crowd_funding.common.pojo.TRole;
import com.xq.crowd_funding.manager.service.Imp.TPermissionServiceImp;
import com.xq.crowd_funding.manager.service.Imp.TRoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Maozhihao
 **/
@RestController
@RequestMapping("/userrole")
public class RoleController {
    @Autowired
    TRoleServiceImp tRoleServiceImp;
    @Autowired
    TPermissionServiceImp tPermissionServiceImp;


    @PostMapping("/getList")
    public ResultEntity getlist(TRole pojo){
        List<TRole> list = tRoleServiceImp.select(pojo);
        if(list!=null){
           return ResultEntity.successWithData(list);
        }else{
           return ResultEntity.failed("失败");
        }
    }

    @PostMapping("/treeData")
    public Object treeData(Integer roleid){
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
        //根据角色id查询之前所分配过的许可
        List<Integer> PermissionIdsFroRole=tPermissionServiceImp.queryPermissionByRoleId(roleid);
        for (TPermission child : children) {
            //默认打开父节点
            child.setOpen(true);
            //查询叶子节点
            List<TPermission> innerChildren=tPermissionServiceImp.getChildrenPermissionByPid(child.getId());
            //如果根据角色查询出来的权限id被分配过
            if(PermissionIdsFroRole.contains(child.getId())){
                child.setChecked(true);
            }
            //将叶子节点装入子节点中
            child.setChildren(innerChildren);
        }
        return root;
    }
}
