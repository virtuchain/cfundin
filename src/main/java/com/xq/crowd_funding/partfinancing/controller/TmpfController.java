package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.partfinancing.bean.TMember;
import com.xq.crowd_funding.partfinancing.service.ITmpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
ClassName: TmpfController
 关注的Controller
 addTmpf:把项目Id和用户Id添加进关注表中
 delTmpf:通过Id将关注表中的数据删除
@Description: TODO
@Author: GuoXinZhang
@Date: 11:36
@Time: 2019/11/1
@Version: 1.0
*/
@RestController
public class TmpfController {

    @Autowired
    ITmpfService iTmpfService;

    @PostMapping("addTmpf")
    public ResultEntity<String> addTmpf(Long projectid, HttpServletRequest request){
        //从session中获取用户
        HttpSession session = request.getSession();
        TMember member = (TMember) session.getAttribute("member");
        //将用户Id和项目Id加入关注表
        int i = iTmpfService.addTmpf(member.getId(), projectid);
        //如果添加的数据>0则代表删除成功
        if (i>0){
            return ResultEntity.successWithData("关注成功");
        }
        return  ResultEntity.successNoData();
    }

    @DeleteMapping("delTmpf")
    public  ResultEntity<String> delTmpf(Long projectid, HttpServletRequest request){
        //从session中获取用户
        HttpSession session = request.getSession();
        TMember member = (TMember) session.getAttribute("member");
        //通过用户Id将关注表中的数据删除
        int i = iTmpfService.delTemf(member.getId(), projectid);
        //如果删除的数据>0则代表删除成功
        if (i>0){
            return ResultEntity.successWithData("成功取消关注");
        }
        return  ResultEntity.successNoData();
    }

}
