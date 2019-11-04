package com.xq.crowd_funding.partfinancing.dao;

import com.xq.crowd_funding.partfinancing.bean.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
ClassName: TOrder
 addOrder:添加订单
 queryTOrderById:根据用户ID查看订单
 savaTOrder:修改订单地址
@Description: TODO
@Author: GuoXinZhang
@Date: 10:45
@Time: 2019/11/1
@Version: 1.0
*/
@Mapper
public interface TOrderDao {
    @Insert("insert into t_order values(null,#{memberid},#{projectid},#{returnid},#{ordernum},#{createdate},#{money},#{rtncount},#{status},#{address},#{invoice},#{invoictitle},#{remark})")
    int addOrder(TOrder tOrder);

    @Select("select * from t_order where memberid=#{value}")
    List<TOrder> queryTOrderById(Long id);

    @Update("update t_order set address=#{address} where memberid=#{memberid}")
    int savaTOrder(TOrder tOrder, Long memberid);
}
