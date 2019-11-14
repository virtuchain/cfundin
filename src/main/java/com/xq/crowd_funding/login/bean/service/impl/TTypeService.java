package com.xq.crowd_funding.login.bean.service.impl;

import com.xq.crowd_funding.common.pojo.TType;
import com.xq.crowd_funding.login.bean.service.IServiceType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.xq.crowd_funding.login.bean.dao.TTypeDao;

@Service
public class TTypeService implements IServiceType {

    @Resource
    private TTypeDao tTypeDao;

    public int insert(TType pojo){
        return tTypeDao.insert(pojo);
    }

    public int insertList(List< TType> pojos){
        return tTypeDao.insertList(pojos);
    }

    public List<TType> select(TType pojo){
        return tTypeDao.select(pojo);
    }

    public int update(TType pojo){
        return tTypeDao.update(pojo);
    }

    //列表查询
    @Override
    public List<TType> selectList() {
        return tTypeDao.selectList();
    }

    @Override
    public void deleteid(Integer id) {
       tTypeDao.deleteid(id);
    }

    @Override
    public List<TType> selectBytext(String text) {
        return tTypeDao.selectBytext(text);
    }

}
