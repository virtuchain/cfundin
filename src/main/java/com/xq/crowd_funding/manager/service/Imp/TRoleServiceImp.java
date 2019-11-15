package com.xq.crowd_funding.manager.service.Imp;

import com.xq.crowd_funding.common.pojo.TRole;
import com.xq.crowd_funding.manager.service.TRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.xq.crowd_funding.manager.dao.TRoleDao;

@Service
public class TRoleServiceImp implements TRoleService {

    @Resource
    private TRoleDao tRoleDao;

    public int insert(TRole pojo){
        return tRoleDao.insert(pojo);
    }

    public int insertList(List< TRole> pojos){
        return tRoleDao.insertList(pojos);
    }

    public List<TRole> select(TRole pojo){
        return tRoleDao.select(pojo);
    }

    public int update(TRole pojo){
        return tRoleDao.update(pojo);
    }

}
