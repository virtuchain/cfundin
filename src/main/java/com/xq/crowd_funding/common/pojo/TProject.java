package com.xq.crowd_funding.common.pojo;

import lombok.Data;

@Data
public class TProject {

  private long id;
  private String name;
  private String remark;
  private long money;
  private long day;
  private String status;
  private String deploydate;
  private long supportmoney;
  private long supporter;
  private long completion;
  private long memberid;
  private String createdate;
  private long follower;
  private String headerPicturePath;

}
