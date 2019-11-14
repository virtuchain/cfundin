package com.xq.crowd_funding.common.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class TPermission {

  private Integer id;
  private Integer pid;
  private String name;
  private String icon;
  private String url;
  private boolean open=true;
  private boolean checked;
  private Integer level;
  private List<TPermission> children;



}
