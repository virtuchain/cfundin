package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;

import java.util.Map;

public interface IRaiseDataBaseService {

    ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO,Integer memberid);

    Map getHtMalDataToMap();
}
