package com.xq.crowd_funding.common.pojo;


public class TProject {

  private long id;
  private String name;
  private String remark;
  private long money;
  private long day;
  private String status;
  private String deploydate;
  private long supportmoney;
  private long supporter;
  private long completion;
  private long memberid;
  private String createdate;
  private long follower;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }


  public long getDay() {
    return day;
  }

  public void setDay(long day) {
    this.day = day;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getDeploydate() {
    return deploydate;
  }

  public void setDeploydate(String deploydate) {
    this.deploydate = deploydate;
  }


  public long getSupportmoney() {
    return supportmoney;
  }

  public void setSupportmoney(long supportmoney) {
    this.supportmoney = supportmoney;
  }


  public long getSupporter() {
    return supporter;
  }

  public void setSupporter(long supporter) {
    this.supporter = supporter;
  }


  public long getCompletion() {
    return completion;
  }

  public void setCompletion(long completion) {
    this.completion = completion;
  }


  public long getMemberid() {
    return memberid;
  }

  public void setMemberid(long memberid) {
    this.memberid = memberid;
  }


  public String getCreatedate() {
    return createdate;
  }

  public void setCreatedate(String createdate) {
    this.createdate = createdate;
  }


  public long getFollower() {
    return follower;
  }

  public void setFollower(long follower) {
    this.follower = follower;
  }

}
