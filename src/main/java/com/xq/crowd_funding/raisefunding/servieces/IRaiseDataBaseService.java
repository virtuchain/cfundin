package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TTag;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;

import java.util.List;
import java.util.Set;

public interface IRaiseDataBaseService {

    ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO,Integer memberid);

    List getHtMalDataToMap();

    Set<TTag> getTagByTypeId(List<Integer> typeIdArray);

}
