package com.xq.crowd_funding.raisefunding.servieces;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TTag;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;

import java.util.List;
import java.util.Set;

public interface IRaiseDataBaseService {

    // 保存所有发起众筹的信息到数据库
    ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO,Long memberid);

    // 查询所有的页面数据 ，， 项目类型
    List getHtMalDataToMap();

    // 根据项目类型查询项目的标签
    Set<TTag> getTagByTypeId(List<Integer> typeIdArray);

}
