package com.xq.crowd_funding.common.pojo;


public class TMemberLaunchInfo {

  private long id;
  private long memberid;
  private String descriptionSimple;
  private String descriptionDetail;
  private String phoneNum;
  private String serviceNum;


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


  public String getDescriptionSimple() {
    return descriptionSimple;
  }

  public void setDescriptionSimple(String descriptionSimple) {
    this.descriptionSimple = descriptionSimple;
  }


  public String getDescriptionDetail() {
    return descriptionDetail;
  }

  public void setDescriptionDetail(String descriptionDetail) {
    this.descriptionDetail = descriptionDetail;
  }


  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }


  public String getServiceNum() {
    return serviceNum;
  }

  public void setServiceNum(String serviceNum) {
    this.serviceNum = serviceNum;
  }

}
