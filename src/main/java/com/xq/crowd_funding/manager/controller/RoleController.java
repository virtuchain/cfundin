package com.xq.crowd_funding.manager.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TRole;
import com.xq.crowd_funding.manager.service.Imp.TRoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Maozhihao
 **/
@RestController
@RequestMapping("/userrole")
public class RoleController {
    @Autowired
    TRoleServiceImp tRoleServiceImp;

    @PostMapping("/getList")
    public ResultEntity getlist(TRole pojo){
        List<TRole> list = tRoleServiceImp.select(pojo);
        if(list!=null){
           return ResultEntity.successWithData(list);
        }else{
           return ResultEntity.failed("失败");
        }
    }
}
