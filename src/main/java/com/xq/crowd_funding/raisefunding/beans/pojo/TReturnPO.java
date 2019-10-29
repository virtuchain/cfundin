package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.Data;

@Data
public class TReturnPO {

  private Long id;
  private Long projectid;
  private String type;
  private Long supportmoney;
  private String content;
  private Long count;
  private Long signalpurchase;
  private Long purchase;
  private Long freight;
  private String invoice;
  private Long rtndate;


}
