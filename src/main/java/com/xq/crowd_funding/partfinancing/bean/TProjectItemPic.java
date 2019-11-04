package com.xq.crowd_funding.partfinancing.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
ClassName: TProjectItemPic
          项目详情图片实体类
@Description: TODO
@Author: GuoXinZhang
@Date: 9:22
@Time: 2019/11/4
@Version: 1.0
*/
public class TProjectItemPic {

  private Long id;
  private Long projectid;
  private String itemPicPath;

}
