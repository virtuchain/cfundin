package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.Data;

@Data
public class TProjectPO {

  private Long id;
  private String name;
  private String remark;
  private Long money;
  private Long day;
  private String status;
  private String deploydate;
  private Long supportmoney;
  private Long supporter;
  private Long completion;
  private Long memberid;
  private String createdate;
  private Long follower;

}
