package com.xq.crowd_funding.beans.raisefunding.pojo;

import lombok.Data;

@Data
public class TReturnPO {

  private long id;
  private long projectid;
  private String type;
  private long supportmoney;
  private String content;
  private long count;
  private long signalpurchase;
  private long purchase;
  private long freight;
  private String invoice;
  private long rtndate;


}
