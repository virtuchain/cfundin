package com.xq.crowd_funding.common.pojo;


public class TMessage {

  private long id;
  private long memberid;
  private String content;
  private String senddate;


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


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getSenddate() {
    return senddate;
  }

  public void setSenddate(String senddate) {
    this.senddate = senddate;
  }

}
