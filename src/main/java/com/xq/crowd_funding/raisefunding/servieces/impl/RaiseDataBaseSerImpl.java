package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.utils.myconfigration.redisconfigration.RedisOperation;
import com.xq.crowd_funding.raisefunding.beans.pojo.TMemberConfirmInfoPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TMemberLaunchInfoPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TProjectPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TReturnPO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.dao.RaiseDao;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseDataBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RaiseDataBaseSerImpl implements IRaiseDataBaseService {

      @Resource
      private RaiseDao raiseDao;

      @Autowired
      RedisOperation redisOperation;

    /**
     * 这个方法将申请众筹项目的所有数据添加到数据库
     * @param projectVO 申请众筹项目的所有信息数据
     * @param memberid 用户 id
     * @return 返回成功或异常通知
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO,Integer memberid) {

        TProjectPO tProjectPO = new TProjectPO();
        BeanUtils.copyProperties(projectVO,tProjectPO);
        tProjectPO.setMemberid(memberid);
        try {
            // 添加 project 数据到数据库
            raiseDao.inserPeojectInfo(tProjectPO);
            Integer proId = tProjectPO.getId();

            // 添加 TProjectType  数据到数据库
            List<Integer> proTypeList = projectVO.getTypeIdList();
            raiseDao.insertProTypeInfo(proId,proTypeList);

            // 添加 TProjectTag 到数据库
            List<Integer> proTagList = projectVO.getTagIdList();
            raiseDao.insertProTagInfo(proId,proTagList);

            // 添加 TMemberLaunchInfoPO 到数据库
            TMemberLaunchInfoPO tMemberLaunchInfoPO = new TMemberLaunchInfoPO();
            BeanUtils.copyProperties(projectVO.getMemberLauchInfoVO(),tMemberLaunchInfoPO);
            tMemberLaunchInfoPO.setMemberid(memberid);

            // 添加回报
            List<ReturnVO> returnPOList =  projectVO.getReturnVOList();
            TReturnPO returnPO = new  TReturnPO();
            for (ReturnVO retuenVo : returnPOList ) {
                BeanUtils.copyProperties(retuenVo,returnPO);
                returnPO.setProjectid(proId);
                raiseDao.insertReturnInfo(returnPO);
            }
            // 添加 发起人确认信息
            TMemberConfirmInfoPO memberConfirmInfoPO =new TMemberConfirmInfoPO();
            BeanUtils.copyProperties(projectVO.getMemberConfirmInfoVO(),memberConfirmInfoPO);
            memberConfirmInfoPO.setMemberid(memberid);
            raiseDao.insertMemberConfirmInfo(memberConfirmInfoPO);
        }catch (Exception e){
            e.printStackTrace();
            return  ResultEntity.failed("添加数据失败");
        }
        // 数据保存成功，则删除 redis 中的数据
        return  redisOperation.removeRedisByKey(projectVO.getProjectTempToken());
    }


}
