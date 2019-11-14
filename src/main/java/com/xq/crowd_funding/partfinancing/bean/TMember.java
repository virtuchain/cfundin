package com.xq.crowd_funding.partfinancing.bean;

import lombok.Data;
import lombok.ToString;
/**
ClassName: TMember
          会员实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:20
@Time: 2019/11/4
@Version: 1.0
*/
@Data
@ToString
public class TMember {

  private Long id;
  private String loginacct;
  private String userpswd;
  private String username;
  private String email;
  private String authstatus;
  private String usertype;
  private String realname;
  private String cardnum;
  private String accttype;
  private  String phone;

}
