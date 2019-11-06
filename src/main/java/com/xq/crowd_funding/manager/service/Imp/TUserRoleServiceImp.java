package com.xq.crowd_funding.manager.service.Imp;

import com.xq.crowd_funding.common.pojo.TUserRole;
import com.xq.crowd_funding.manager.service.TUserRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.xq.crowd_funding.manager.dao.TUserRoleDao;

@Service
public class TUserRoleServiceImp implements TUserRoleService{

    @Resource
    private TUserRoleDao tUserRoleDao;

    public int insert(TUserRole pojo){
        return tUserRoleDao.insert(pojo);
    }

    public int insertList(List< TUserRole> pojos){
        return tUserRoleDao.insertList(pojos);
    }

    public List<TUserRole> select(TUserRole pojo){
        return tUserRoleDao.select(pojo);
    }

    @Override
    public List<TUserRole> selectUserRole(Integer userid) {
        return tUserRoleDao.selectUserRole(userid);
    }

    public int update(TUserRole pojo){
        return tUserRoleDao.update(pojo);
    }

    @Override
    public int deleteAssign(TUserRole pojo) {
        return tUserRoleDao.deleteAssign(pojo);
    }

}
