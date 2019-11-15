package com.xq.crowd_funding.raisefunding.dao;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface RaiseDao {

    void inserPeojectInfo(TProject tProjectPO);

    void insertProTypeInfo(@Param("proId") Long proId, @Param("proTypeList")List proTypeList);

    void insertProTagInfo(@Param("proId") Long proId, @Param("proTagList")List proTagList);

    void insertReturnInfo(TReturn returnPO);

    void insertMemberConfirmInfo(TMemberConfirmInfo memberConfirmInfoPO);

    List<TType> queryTypePO();

    Set<TTag> selectTagByTypeId(Integer[] typeIdArray);

    void insertDetailPicture(@Param("proId") Long proId,@Param("detailPicturePathList")List detailPicturePathList);

    void inserttMemberLaunch(TMemberLaunchInfo tMemberLaunchInfoPO);
}
