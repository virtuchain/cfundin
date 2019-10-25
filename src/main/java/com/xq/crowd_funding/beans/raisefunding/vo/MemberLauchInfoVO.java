package com.xq.crowd_funding.beans.raisefunding.vo;/*
    @auther yangjie
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 无参数构造
@NoArgsConstructor
// 全参数构造
@AllArgsConstructor
public class MemberLauchInfoVO {
    // 用户登录系统后，系统分配的token值，用于识别用户身份。
    // 用户的id可以根据token值查询Redis得到
    private  String memberSignToken;
    // 在Redis中临时存储项目数据的token值
    private String projectTempToken;
    // 简单介绍
    private String descriptionSimple;
    // 详细介绍
    private String descriptionDetail;
    // 联系电话
    private String phoneNum;
    // 客服电话
    private String serviceNum;
}
