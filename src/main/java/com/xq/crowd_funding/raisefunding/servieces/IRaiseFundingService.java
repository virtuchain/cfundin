package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;

public interface IRaiseFundingService {

    ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO);
}
