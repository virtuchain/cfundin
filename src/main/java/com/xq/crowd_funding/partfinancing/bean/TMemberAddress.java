package com.xq.crowd_funding.partfinancing.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
ClassName: TMemberAddress
            会员和地址实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:20
@Time: 2019/11/4
@Version: 1.0
*/
public class TMemberAddress {

  private Long id;
  private Long memberid;
  private String address;

}
