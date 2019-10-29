package com.xq.crowd_funding.raisefunding.dao;/*
    @auther yangjie
*/

import com.xq.crowd_funding.raisefunding.beans.pojo.TProjectPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RaiseDao {

    void inserPeojectInfo(TProjectPO tProjectPO);

}
