package com.xq.crowd_funding.partfinancing.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
ClassName: TMemberProjectFollow
          关注表实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:21
@Time: 2019/11/4
@Version: 1.0
*/
public class TMemberProjectFollow {

  private Long id;
  private Long projectid;
  private Long memberid;

}
