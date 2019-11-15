package com.xq.crowd_funding.partfinancing.pojo;

import com.xq.crowd_funding.common.pojo.TMemberAddress;

public class TMember {

  private long id;
  private String loginacct;
  private String userpswd;
  private String username;
  private String email;
  private String authstatus;
  private String usertype;
  private String realname;
  private String cardnum;
  private String accttype;
  private String phone;
  //会员地址表
  private TMemberAddress tMemberAddress;

  public TMemberAddress gettMemberAddress() {
    return tMemberAddress;
  }

  public void settMemberAddress(TMemberAddress tMemberAddress) {
    this.tMemberAddress = tMemberAddress;
  }


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


  public String getAuthstatus() {
    return authstatus;
  }

  public void setAuthstatus(String authstatus) {
    this.authstatus = authstatus;
  }


  public String getUsertype() {
    return usertype;
  }

  public void setUsertype(String usertype) {
    this.usertype = usertype;
  }


  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }


  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }


  public String getAccttype() {
    return accttype;
  }

  public void setAccttype(String accttype) {
    this.accttype = accttype;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
