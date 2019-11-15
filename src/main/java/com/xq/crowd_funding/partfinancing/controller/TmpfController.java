package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TMember;
import com.xq.crowd_funding.common.pojo.TMemberProjectFollow;
import com.xq.crowd_funding.partfinancing.service.ITmpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
ClassName: TmpfController
 关注的Controller
 queryByProid:通过用户商品Id查询关注表中是否有用户id与其对应，有则为已关注，反之未关注
 queryLength:查看此项目有多少人关注
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

    @RequestMapping(value = "/queryByProid/{projectid}",method = RequestMethod.GET)
    public ResultEntity<TMemberProjectFollow> queryByProid(@PathVariable("projectid") Integer projectid, HttpServletRequest request){
        //创建session
//        HttpSession session = request.getSession();
        //从Session中获取会员Id
//        TMember tMember = (TMember) session.getAttribute("");
        //传入项目Id和会员Id
        TMemberProjectFollow tMemberProjectFollow = new TMemberProjectFollow();
        if (iTmpfService.queryByProid(projectid,123)==null){
            tMemberProjectFollow.setMemberid(000);
            return ResultEntity.successWithData(tMemberProjectFollow);
        }else{
            tMemberProjectFollow = iTmpfService.queryByProid(projectid,123);
        }
        return ResultEntity.successWithData(tMemberProjectFollow);
    }

    @RequestMapping(value = "/queryLength/{projectid}",method = RequestMethod.GET)
    public ResultEntity<Integer> queryLength(@PathVariable("projectid") Integer projectid){
        System.out.println("projectid:"+projectid);
        Integer i = 0;
        if (iTmpfService.queryLength(projectid)>0){
            i = iTmpfService.queryLength(projectid);
            return ResultEntity.successWithData(i);
        }
        i = 1000000000;
        return ResultEntity.successWithData(i);
    }

    @PostMapping(value = "/addTmpf/{projectid}")
    public ResultEntity<String> addTmpf(@PathVariable("projectid") Integer projectid, HttpServletRequest request){
        //从session中获取用户
        HttpSession session = request.getSession();
        TMember member = (TMember) session.getAttribute("member");
        //将用户Id和项目Id加入关注表
        //int i = iTmpfService.addTmpf(new Long(member.getId()).intValue(), projectid);
        int i = iTmpfService.addTmpf(123, projectid);
        return ResultEntity.successWithData("yes");
    }

    @DeleteMapping("/delTmpf/{projectid}")
    public  ResultEntity<String> delTmpf(@PathVariable("projectid")Integer projectid, HttpServletRequest request){
        //从session中获取用户
        HttpSession session = request.getSession();
        TMember member = (TMember) session.getAttribute("member");
        //通过用户Id将关注表中的数据删除
//        iTmpfService.queryByProid(projectid,new Long(member.getId()).intValue());
//        TMemberProjectFollow tMemberProjectFollow = iTmpfService.queryByProid(projectid,123);
        iTmpfService.delTemf(123, projectid);
        //如果删除的数据>0则代表删除成功
        return  ResultEntity.successNoData();
    }

}
