package com.xq.crowd_funding.common.pojo;


public class TMemberCert {

  private long id;
  private long memberid;
  private long certid;
  private String iconpath;


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


  public long getCertid() {
    return certid;
  }

  public void setCertid(long certid) {
    this.certid = certid;
  }


  public String getIconpath() {
    return iconpath;
  }

  public void setIconpath(String iconpath) {
    this.iconpath = iconpath;
  }

}
