package com.xq.crowd_funding.raisefunding.beans.vo;/*
    @auther yangjie
*/

import lombok.Data;

@Data
public class MemberConfirmInfoVO {

    // 易付宝账号
    private String paynum;
    // 法人身份证号
    private String cardnum;
}
