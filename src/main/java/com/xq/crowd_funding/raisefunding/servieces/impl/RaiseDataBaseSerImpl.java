package com.xq.crowd_funding.raisefunding.servieces.impl;/*
    @auther yangjie
*/

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.configrations.redisconfigration.RedisOperation;
import com.xq.crowd_funding.common.pojo.*;
import com.xq.crowd_funding.common.utils.CrowdUtils;
import com.xq.crowd_funding.raisefunding.beans.vo.ProjectVO;
import com.xq.crowd_funding.raisefunding.beans.vo.ReturnVO;
import com.xq.crowd_funding.raisefunding.dao.RaiseDao;
import com.xq.crowd_funding.raisefunding.servieces.IRaiseDataBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RaiseDataBaseSerImpl implements IRaiseDataBaseService {

      @Resource
      private RaiseDao raiseDao;

      @Autowired
      private RedisOperation redisOperation;

    /**
     * 这个方法将申请众筹项目的所有数据添加到数据库
     * @param projectVO 申请众筹项目的所有信息数据
     * @param memberid 用户 id
     * @return 返回成功或异常通知
     */
    @Transactional // 事务，出现错误全部回滚
    public ResultEntity<String> saveAllProToDatabase(ProjectVO projectVO,Long memberid) {

        TProject tProjectPO = new TProject();

        tProjectPO.setMoney(projectVO.getMoney());
        tProjectPO.setDay(projectVO.getDay());
        // 用户 id
        tProjectPO.setMemberid(memberid);
        // 项目状态
        tProjectPO.setStatus("0");
        // 项目发起时间
        String date  = CrowdUtils.returnDateStr();
        System.out.println("date "+date);
        tProjectPO.setDeploydate(date);

        BeanUtils.copyProperties(projectVO,tProjectPO);

        try {
            // 添加 project 数据到数据库
            System.out.println("project 数据到数据库 tProjectPO"+tProjectPO.toString());
            raiseDao.inserPeojectInfo(tProjectPO);
            long proId = tProjectPO.getId();
            System.out.println("tProject "+tProjectPO.toString());

            // 添加 TProjectType  数据到数据库
            List<Integer> proTypeList = projectVO.getTypeIdList();
            raiseDao.insertProTypeInfo(proId,proTypeList);

            // 添加 TProjectTag 到数据库
            List<Integer> proTagList = projectVO.getTagIdList();
            raiseDao.insertProTagInfo(proId,proTagList);

            // 添加详情图片到数据库   t_project_item_pic
            List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
            raiseDao.insertDetailPicture(proId,detailPicturePathList);

            // 添加 TMemberLaunchInfoPO 到数据库  t_member_launch_info
            TMemberLaunchInfo tMemberLaunchInfoPO = new TMemberLaunchInfo();
            BeanUtils.copyProperties(projectVO.getMemberLauchInfoVO(),tMemberLaunchInfoPO);
            tMemberLaunchInfoPO.setMemberid(memberid);
            raiseDao.inserttMemberLaunch(tMemberLaunchInfoPO);

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
            //

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return  ResultEntity.failed("添加数据失败");
        }
        // 数据保存成功，则删除 redis 中的数据
        return  redisOperation.removeRedisByKey(projectVO.getProjectTempToken());
    }

    /**
     * // 查询所有的页面数据 ，， 项目类型
     * @return
     */
    @Override
    public List getHtMalDataToMap() {
        Map<String,Object> map = new HashMap();
        return  raiseDao.queryTypePO();
    }

    /**
     *   据项目类型查询项目的标签
     * @param typeIdArray
     * @return
     */
    @Override
    public Set<TTag> getTagByTypeId(List<Integer> typeIdArray) {
        Integer[] array = new Integer[ typeIdArray.size()];
        for (int i = 0; i < typeIdArray.size(); i++) {
            array[i] = typeIdArray.get(i);
        }
        System.out.println("typeIdArray: "+typeIdArray.toString());
        return raiseDao.selectTagByTypeId(array);
    }


}
