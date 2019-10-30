package com.xq.crowd_funding.raisefunding.dao;/*
    @auther yangjie
*/

import com.xq.crowd_funding.raisefunding.beans.pojo.TMemberConfirmInfoPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TProjectPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TReturnPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RaiseDao {

    TProjectPO inserPeojectInfo(TProjectPO tProjectPO);

    void insertProTypeInfo(@Param("proId") Integer proId, @Param("proTypeList")List proTypeList);

    void insertProTagInfo(@Param("proId") Integer proId, @Param("proTagList")List proTagList);

    void insertReturnInfo(TReturnPO returnPO);

    void insertMemberConfirmInfo(TMemberConfirmInfoPO memberConfirmInfoPO);
}
