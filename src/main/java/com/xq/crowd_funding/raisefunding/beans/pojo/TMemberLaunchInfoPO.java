package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.Data;

@Data
public class TMemberLaunchInfoPO {

  private Integer id;
  private Integer memberid;
  // 简单介绍
  private String descriptionSimple;
  private String descriptionDetail;
  private String phoneNum;
  private String serviceNum;

}
