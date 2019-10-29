package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.raisefunding.beans.pojo.TMemberLaunchInfoPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TProjectPO;
import com.xq.crowd_funding.raisefunding.beans.pojo.TReturnPO;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.dao.RaiseDao;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseFundingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RaiseFundingServiceImpl implements IRaiseFundingService {

      @Resource
      private RaiseDao raiseDao;

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
            }

        }catch (Exception e){
            e.printStackTrace();
            return  ResultEntity.failed("添加数据失败");
        }







        return  null;
    }
}
