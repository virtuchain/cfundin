package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.raisefunding.beans.pojo.TProjectPO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.dao.RaiseDao;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseFundingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RaiseFundingServiceImpl implements IRaiseFundingService {

    @Autowired
    private RaiseDao raiseDao;

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO,Integer memberid) {
        TProjectPO tProjectPO = new TProjectPO();
        BeanUtils.copyProperties(projectVO,tProjectPO);
        tProjectPO.setMemberid(memberid);
        try {
            raiseDao.inserPeojectInfo(tProjectPO);
        }catch (Exception e){
            e.printStackTrace();
            return  ResultEntity.failed("TProjectPO 添加数据失败");
        }


        return  null;
    }
}
