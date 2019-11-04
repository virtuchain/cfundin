package com.xq.crowd_funding.common.pojo;


public class TReturn {

  private long id;
  private long projectid;
  private String type;
  private long supportmoney;
  private String content;
  private long count;
  private long signalpurchase;
  private long purchase;
  private long freight;
  private String invoice;
  private long rtndate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getProjectid() {
    return projectid;
  }

  public void setProjectid(long projectid) {
    this.projectid = projectid;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public long getSupportmoney() {
    return supportmoney;
  }

  public void setSupportmoney(long supportmoney) {
    this.supportmoney = supportmoney;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }


  public long getSignalpurchase() {
    return signalpurchase;
  }

  public void setSignalpurchase(long signalpurchase) {
    this.signalpurchase = signalpurchase;
  }


  public long getPurchase() {
    return purchase;
  }

  public void setPurchase(long purchase) {
    this.purchase = purchase;
  }


  public long getFreight() {
    return freight;
  }

  public void setFreight(long freight) {
    this.freight = freight;
  }


  public String getInvoice() {
    return invoice;
  }

  public void setInvoice(String invoice) {
    this.invoice = invoice;
  }


  public long getRtndate() {
    return rtndate;
  }

  public void setRtndate(long rtndate) {
    this.rtndate = rtndate;
  }

}
