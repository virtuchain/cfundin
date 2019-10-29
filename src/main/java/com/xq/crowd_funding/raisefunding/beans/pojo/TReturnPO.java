package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.Data;

@Data
public class TReturnPO {

  private Integer id;
  private Integer projectid;
  private String type;
  private Integer supportmoney;
  private String content;
  private Integer count;
  private Integer signalpurchase;
  private Integer purchase;
  private Integer freight;
  private String invoice;
  private Integer rtndate;


}
