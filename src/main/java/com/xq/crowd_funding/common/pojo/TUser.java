package com.xq.crowd_funding.common.pojo;


public class TUser {

  private long id;
  private String loginacct;
  private String userpswd;
  private String username;
  private String email;
  private String createtime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLoginacct() {
    return loginacct;
  }

  public void setLoginacct(String loginacct) {
    this.loginacct = loginacct;
  }


  public String getUserpswd() {
    return userpswd;
  }

  public void setUserpswd(String userpswd) {
    this.userpswd = userpswd;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

}
