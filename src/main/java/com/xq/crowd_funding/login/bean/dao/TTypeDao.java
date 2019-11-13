package com.xq.crowd_funding.login.bean.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.xq.crowd_funding.common.pojo.TType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TTypeDao {

    int insert(@Param("pojo") TType pojo);

    int insertList(@Param("pojos") List< TType> pojo);

    List<TType> select(@Param("pojo") TType pojo);

    int update(@Param("pojo") TType pojo);

    @Select("select * from t_type")
    List<TType>  selectList();

    @Delete("delete  from t_type where id=#{id}")
    void deleteid(Integer id);

    @Select("select * from t_type where name like '%${text}%' ")
    List<TType> selectBytext(String text);
}
