package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.Data;

@Data
public class TMemberLaunchInfoPO {

  private long id;
  private long memberid;
  private String descriptionSimple;
  private String descriptionDetail;
  private String phoneNum;
  private String serviceNum;

}
