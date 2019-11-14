package com.xq.crowd_funding.manager.service.Imp;

import com.xq.crowd_funding.common.pojo.TPermission;
import javax.annotation.Resource;
import java.util.List;

import com.xq.crowd_funding.manager.dao.TPermissionDao;
import com.xq.crowd_funding.manager.service.TPermissionService;
import org.springframework.stereotype.Service;

@Service
public class TPermissionServiceImp implements TPermissionService {

    @Resource
    private TPermissionDao tPermissionDao;


    public int insert(TPermission pojo){
        return tPermissionDao.insert(pojo);
    }

    public int insertList(List< TPermission> pojos){
        return tPermissionDao.insertList(pojos);
    }

    public List<TPermission> select(TPermission pojo){
        return tPermissionDao.select(pojo);
    }

    public int update(TPermission pojo){
        return tPermissionDao.update(pojo);
    }

    @Override
    public TPermission getRootPermission() {
        return tPermissionDao.getRootPermission();
    }

    @Override
    public List<TPermission> getChildrenPermissionByPid(Integer id) {
        return tPermissionDao.getChildrenPermissionByPid(id);
    }

    @Override
    public TPermission getPermissionById(Integer id) {
        return tPermissionDao.getPermissionById(id);
    }

    @Override
    public int delete(TPermission pojo) {
        return tPermissionDao.delete(pojo);
    }

    @Override
    public List<Integer> queryPermissionByRoleId(Integer roleid) {
        return tPermissionDao.queryPermissionByRoleId(roleid);
    }

}
