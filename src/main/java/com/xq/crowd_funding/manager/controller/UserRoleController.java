package com.xq.crowd_funding.manager.controller;
import com.alibaba.fastjson.JSON;
import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TRole;
import com.xq.crowd_funding.common.pojo.TUser;
import com.xq.crowd_funding.common.pojo.TUserRole;
import com.xq.crowd_funding.manager.service.Imp.TRoleServiceImp;
import com.xq.crowd_funding.manager.service.Imp.TUserRoleServiceImp;
import com.xq.crowd_funding.manager.service.Imp.TUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Maozhihao
 **/
@Controller
@RequestMapping("/managerUserRole")
public class UserRoleController {
    @Autowired
    TUserServiceImp tUserServiceImp;
    @Autowired
    TUserRoleServiceImp tUserRoleServiceImp;
    @Autowired
    TRoleServiceImp tRoleServiceImp;

    @PostMapping("/userRole")
    @ResponseBody
    public Map userrole(TUserRole pojo, TRole tRole, Integer userid){
        Map<String,Object> map = new HashMap<>();
        //查询权限表所有权限
        List<TRole> allListRole= tRoleServiceImp.select(tRole);
        //查询单个用户的权限
        List<TUserRole> roleIds =  tUserRoleServiceImp.selectUserRole(userid);
        //将查询到的数据装入集合中
        ArrayList<TRole> leftRoleList = new ArrayList<TRole>();//未分配角色
        ArrayList<TRole> rightRoleList = new ArrayList<TRole>();//已分配的角色
        //迭代权限表
        for (TRole role : allListRole) {
          //判断role的id在没在roleIds里出现，contains:包不包含
                if (roleIds.toString().contains(String.valueOf(role.getId()))){
                    //如果包含，则分配进右边已分配角色里面
                    rightRoleList.add(role);
                }else {
                    //如果不包含，则分配进左边未分配的角色里面
                    leftRoleList.add(role);
                }

        }
        map.put("leftRoleList",leftRoleList);
        map.put("rightRoleList",rightRoleList);
        return map;
    }
    //分配权限
    @PostMapping("/assignRole")
    @ResponseBody
    public ResultEntity assignRole(Integer userid,Integer[] roleid){
        int i=0;
        TUserRole pojo=new TUserRole();
        pojo.setUserid(userid);//将用户id值传入对象
        for (Integer role:roleid) {
             pojo.setRoleid(role);//将权限id传入对象
             i= tUserRoleServiceImp.insert(pojo);
             i++;
        }
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else{
            return ResultEntity.failed("失败");
        }
    }
    //删除权限
    @PostMapping("/delassignRole")
    @ResponseBody
    public ResultEntity delassignRole(Integer userid,Integer[] roleid){
        int i=0;
        TUserRole pojo=new TUserRole();
        pojo.setUserid(userid);//将用户id值传入对象
        for (Integer role:roleid) {
            pojo.setRoleid(role);//将权限id传入对象
            i= tUserRoleServiceImp.deleteAssign(pojo);
            i++;
        }
        if ( i >= 1 ){
            return ResultEntity.successNoData();
        }else{
            return ResultEntity.failed("失败");
        }
    }
}
