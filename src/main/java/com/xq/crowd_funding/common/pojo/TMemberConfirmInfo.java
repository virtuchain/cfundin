package com.xq.crowd_funding.common.pojo;


public class TMemberConfirmInfo {

  private long id;
  private long memberid;
  private String paynum;
  private String cardnum;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMemberid() {
    return memberid;
  }

  public void setMemberid(long memberid) {
    this.memberid = memberid;
  }


  public String getPaynum() {
    return paynum;
  }

  public void setPaynum(String paynum) {
    this.paynum = paynum;
  }


  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

}
