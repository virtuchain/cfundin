package com.xq.crowd_funding.partfinancing.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ToString
/**
ClassName: TProject
          项目实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:22
@Time: 2019/11/4
@Version: 1.0
*/
public class TProject {

  private Long id;
  private String name;
  private String remark;
  private Long money;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Long day;
  private String status;
  private String deploydate;
  private Long supportmoney;
  private Long supporter;
  private Long completion;
  private Long memberid;
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
  private String createdate;
  private Long follower;
  private String headerPicturePath;

}
