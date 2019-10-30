package com.xq.crowd_funding.raisefunding.beans.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 无参数构造
@NoArgsConstructor
// 全参数构造
@AllArgsConstructor
public class TProjectPO {

  private Integer id;
  private String name;
  private String remark;
  private Integer money;
  private Integer day;
  private String status;
  private String deploydate;
  private Integer supportmoney;
  private Integer supporter;
  private Integer completion;
  private Integer memberid;
  private String createdate;
  private Integer follower;
  private  String headerPicturePath;
}
