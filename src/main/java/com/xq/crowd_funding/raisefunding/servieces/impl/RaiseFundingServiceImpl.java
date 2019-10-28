package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseFundingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RaiseFundingServiceImpl implements IRaiseFundingService {
    @Override
    @Transactional
    public ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO) {


        return  null;
    }
}
