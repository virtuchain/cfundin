package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.configrations.redisconfigration.RedisOperation;
import com.xq.crowd_funding.common.pojo.*;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.dao.RaiseDao;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseDataBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xq.crowd_funding.common.pojo.TMemberConfirmInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        TProject tProjectPO = new TProject();
        BeanUtils.copyProperties(projectVO,tProjectPO);
        tProjectPO.setMemberid(memberid);
        try {
            // 添加 project 数据到数据库
            raiseDao.inserPeojectInfo(tProjectPO);
            long proId = tProjectPO.getId();

            // 添加 TProjectType  数据到数据库
            List<Integer> proTypeList = projectVO.getTypeIdList();
            raiseDao.insertProTypeInfo(proId,proTypeList);

            // 添加 TProjectTag 到数据库
            List<Integer> proTagList = projectVO.getTagIdList();
            raiseDao.insertProTagInfo(proId,proTagList);

            // 添加 TMemberLaunchInfoPO 到数据库
            TMemberLaunchInfo tMemberLaunchInfoPO = new TMemberLaunchInfo();
            BeanUtils.copyProperties(projectVO.getMemberLauchInfoVO(),tMemberLaunchInfoPO);
            tMemberLaunchInfoPO.setMemberid(memberid);

            // 添加回报
            List<ReturnVO> returnPOList =  projectVO.getReturnVOList();
            TReturn returnPO = new TReturn();
            for (ReturnVO retuenVo : returnPOList ) {
                BeanUtils.copyProperties(retuenVo,returnPO);
                returnPO.setProjectid(proId);
                raiseDao.insertReturnInfo(returnPO);
            }
            // 添加 发起人确认信息
            TMemberConfirmInfo memberConfirmInfoPO =new TMemberConfirmInfo();
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

    @Override
    public Map getHtMalDataToMap() {
        Map<String,Object> map = new HashMap();
        List<TType> tTypePO = raiseDao.queryTypePO();
        map.put("ttypepo",tTypePO);
        List<TTag> tTagPO = raiseDao.queryTagePO();
        map.put("ttagpo",tTagPO);
        return map;
    }


}
