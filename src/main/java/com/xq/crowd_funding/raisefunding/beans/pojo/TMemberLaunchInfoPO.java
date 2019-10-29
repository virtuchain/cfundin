package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.Data;

@Data
public class TMemberLaunchInfoPO {

  private Long id;
  private Long memberid;
  private String descriptionSimple;
  private String descriptionDetail;
  private String phoneNum;
  private String serviceNum;

}
