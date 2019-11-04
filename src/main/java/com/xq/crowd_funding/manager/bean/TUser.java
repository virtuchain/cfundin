package com.xq.crowd_funding.manager.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author Maozhihao
 */
@ToString
@Data
public class TUser {
  private Integer id;//主键
  private String loginacct;//登录账号
  private String userpswd;//登录密码
  private String username;//登录名称
  private String email;//电子邮箱
  private String createtime;//创建时间
}
