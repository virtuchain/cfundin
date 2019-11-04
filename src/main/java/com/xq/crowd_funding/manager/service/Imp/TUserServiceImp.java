package com.xq.crowd_funding.manager.service.Imp;


import com.xq.crowd_funding.common.utils.Page;
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
    public int update(TUser pojo) {
        return tUserDao.update(pojo);
    }

    @Override
    public int deleteUsers(TUser pojo) {
        //调用删除方法
        return tUserDao.deleteUsers(pojo);
    }


    @Override
    public List<TUser> queryList(Integer startIndex, Integer pagesize) {
        return tUserDao.queryList(startIndex,pagesize);
    }

    @Override
    public Integer queryCount(TUser pojo) {
        //返回查数据条数方法
        return tUserDao.queryCount(pojo);
    }

    @Override
    public List<TUser> queryLike(String queryText) {
        //调用模糊查询方法
        return tUserDao.queryLike(queryText);
    }


    @Override
    public Page queryPage(Integer pageno, Integer pagesize,TUser pojo) {

        Page page = new Page(pageno,pagesize);

        Integer startIndex=page.getStartIndex();

        List<TUser> datas=tUserDao.queryList(startIndex,pagesize);

        page.setDatas(datas);

        Integer totalsize = tUserDao.queryCount(pojo);

        page.setTotalsize(totalsize);

        return page;
    }
}
