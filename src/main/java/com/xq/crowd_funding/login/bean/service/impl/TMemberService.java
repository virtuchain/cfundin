package com.xq.crowd_funding.login.bean.service.impl;

import com.xq.crowd_funding.login.bean.dao.TMemberDaoo;
import com.xq.crowd_funding.login.bean.service.IService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.xq.crowd_funding.partfinancing.bean.TMember;


@Service
public class TMemberService implements IService {
    private Integer usertype;
    @Resource
    private TMemberDaoo tMemberDao;

    public void insert(TMember tMember){
         tMemberDao.insert(tMember);
    }


    public TMember selectUser( String loginacct){
        TMember tMember= null;
        System.out.println("---------usertype的值"+usertype);
        System.out.println("---------loginacct的值"+loginacct);
        //如果用户为1 查询前台会员，否则查询后台管理员表
        if (usertype==1) {
            tMember=tMemberDao.SelectUser(loginacct,usertype);
        } else {
            tMember=tMemberDao.SelectUser(loginacct,usertype);
        }
        System.out.println("user type为1查出的tMember"+tMember);
        return tMember;
    }

    public void setUserType(Integer userType) {
        usertype=userType;
        System.out.println("---------service层的usertype"+usertype);
    }

    @Override
    public String selectUsername(String logginacct) {
        String username=tMemberDao.selectUsername(logginacct);
        return username;
    }

    @Override
    public String selectPhone(String Phone) {
         Phone =tMemberDao.selectPhone(Phone);
        return Phone;
    }
}
