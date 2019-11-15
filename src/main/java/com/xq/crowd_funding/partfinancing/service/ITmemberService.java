package com.xq.crowd_funding.partfinancing.service;


import com.xq.crowd_funding.partfinancing.pojo.TMember;

import java.util.List;

/**
ClassName: ITmemberService
 queryById：通过ID查询到会员详情
 savaPro：修改用户的手机号码和姓名
 queryTMemberById:通过Id查看会员信息及其电话和地址
@Description: TODO
@Author: GuoXinZhang
@Date: 14:50
@Time: 2019/11/1
@Version: 1.0
*/
public interface ITmemberService {

    TMember queryById(Integer projectid);

    int savaPro(TMember tMember);

    List<TMember> queryTMemberById(Integer id);
}
