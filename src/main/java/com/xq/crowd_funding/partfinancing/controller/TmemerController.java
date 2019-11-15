package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TProject;
import com.xq.crowd_funding.partfinancing.pojo.TMember;
import com.xq.crowd_funding.partfinancing.service.ITmemberService;
import com.xq.crowd_funding.partfinancing.service.ITprojectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
ClassName:TmemerController
 queryTmemer:通过项目id查询到项目详情,通过获取的项目详情id来获取会员详情
 savaPro：修改用户的手机号码和姓名
 queryTMemberById:通过Id查看会员信息及其电话和地址
@Description: TODO
@Author: GuoXinZhang
@Date: 8:35
@Time: 2019/11/4
@Version: 1.0
*/
@RestController
public class TmemerController {

    @Autowired
    ITmemberService iTmemberService;
    @Autowired
    ITprojectService iTprojectService;

    @GetMapping("/queryTmemer/{projectid}")
    public ResultEntity<TMember> queryTmemer(@PathVariable("projectid") Integer projectid){
        //通过项目Id查询项目详情，取到memberid
        TProject tProject = iTprojectService.queryPro(projectid);
        //通过memberid查询会员详情
        TMember tMember = iTmemberService.queryById(new Long(tProject.getMemberid()).intValue());
        return ResultEntity.successWithData(tMember);
    }


    @PostMapping("/savaPro")
    public void savaPro(HttpServletRequest request){
        //从session中获取用户
        HttpSession session = request.getSession();
        TMember member = (TMember) session.getAttribute("member");
        //通过Id查询会员详情
        TMember tMember = iTmemberService.queryById(new Long(member.getId()).intValue());
        //修改会员信息的姓名和手机
        int sava = iTmemberService.savaPro(tMember);
        if (sava>0){
            System.out.println("更改成功");
        }
        System.out.println("更改失败");
    }

    @GetMapping("/queryTMemberById/{id}")
    public ResultEntity queryTMemberById(@PathVariable("id") Integer id){
        List<TMember> tMembers = iTmemberService.queryTMemberById(id);
        return ResultEntity.successWithData(tMembers);
    }

}
