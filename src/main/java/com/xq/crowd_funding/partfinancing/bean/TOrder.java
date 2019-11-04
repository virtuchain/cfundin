package com.xq.crowd_funding.partfinancing.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ToString
/**
ClassName: TOrder
         订单表实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:21
@Time: 2019/11/4
@Version: 1.0
*/
public class TOrder {

  private Long id;
  private Long memberid;
  private Long projectid;
  private Long returnid;
  private String ordernum;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String createdate;
  private Long money;
  private Long rtncount;
  private String status;
  private String address;
  private String invoice;
  private String invoictitle;
  private String remark;

}
