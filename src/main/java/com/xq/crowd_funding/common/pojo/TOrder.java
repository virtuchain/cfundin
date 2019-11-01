package com.xq.crowd_funding.common.pojo;


public class TOrder {

  private long id;
  private long memberid;
  private long projectid;
  private long returnid;
  private String ordernum;
  private String createdate;
  private long money;
  private long rtncount;
  private String status;
  private String address;
  private String invoice;
  private String invoictitle;
  private String remark;


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


  public long getProjectid() {
    return projectid;
  }

  public void setProjectid(long projectid) {
    this.projectid = projectid;
  }


  public long getReturnid() {
    return returnid;
  }

  public void setReturnid(long returnid) {
    this.returnid = returnid;
  }


  public String getOrdernum() {
    return ordernum;
  }

  public void setOrdernum(String ordernum) {
    this.ordernum = ordernum;
  }


  public String getCreatedate() {
    return createdate;
  }

  public void setCreatedate(String createdate) {
    this.createdate = createdate;
  }


  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }


  public long getRtncount() {
    return rtncount;
  }

  public void setRtncount(long rtncount) {
    this.rtncount = rtncount;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getInvoice() {
    return invoice;
  }

  public void setInvoice(String invoice) {
    this.invoice = invoice;
  }


  public String getInvoictitle() {
    return invoictitle;
  }

  public void setInvoictitle(String invoictitle) {
    this.invoictitle = invoictitle;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
