package com.xq.crowd_funding.partfinancing.service.impl;

import com.xq.crowd_funding.partfinancing.dao.TmpfDao;
import com.xq.crowd_funding.partfinancing.service.ITmpfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
ClassName: TmpfServiceimpl
实现ITmpfService接口中的方法
@Description: TODO
@Author: GuoXinZhang
@Date: 11:33
@Time: 2019/11/1
@Version: 1.0
*/
@Service
public class TmpfServiceimpl implements ITmpfService {

    @Resource
    TmpfDao tmpfDao;

    @Override
    public int addTmpf(Long memberid, Long projectid) {
        int i = tmpfDao.addTmpf(memberid, projectid);
        return  i;
    }

    @Override
    public int delTemf(Long memberid, Long projectid) {
        int i = tmpfDao.addTmpf(memberid, projectid);
        return i;
    }
}
