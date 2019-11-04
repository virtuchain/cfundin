package com.xq.crowd_funding.partfinancing.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
ClassName: TReturn
          回报实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:23
@Time: 2019/11/4
@Version: 1.0
*/
public class TReturn {

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
