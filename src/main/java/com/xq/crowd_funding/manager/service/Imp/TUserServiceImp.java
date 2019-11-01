package com.xq.crowd_funding.manager.service.Imp;


import com.xq.crowd_funding.manager.bean.TUser;
import com.xq.crowd_funding.manager.dao.TUserDao;
import com.xq.crowd_funding.manager.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Maozhihao
 **/
@Service
public class TUserServiceImp implements TUserService{
    @Resource
    private TUserDao tUserDao;
    @Override
    public int insert(TUser pojo) {

        return tUserDao.insert(pojo);
    }

    @Override
    public int insertList(List<TUser> pojo) {
        return 0;
    }

    @Override
    public List<TUser> select(TUser pojo) {
        return tUserDao.select(pojo);
    }
    @Override
    public int update(TUser pojo) {
        return tUserDao.update(pojo);
    }

    @Override
    public int delete(TUser pojo) {
        return tUserDao.delete(pojo);
    }

    @Override
    public int deleteUsers(TUser pojo) {
        return tUserDao.deleteUsers(pojo);
    }
}
