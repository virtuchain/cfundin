package com.xq.crowd_funding.raisefunding.beans.vo;/*
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
    // 简单介绍
    private String descriptionSimple;
    // 详细介绍
    private String descriptionDetail;
    // 联系电话
    private String phoneNum;
    // 客服电话
    private String serviceNum;
}
