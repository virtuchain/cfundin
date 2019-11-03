package com.xq.crowd_funding.common.pojo;


public class TAdvertisement {

  private long id;
  private String name;
  private String iconpath;
  private String status;
  private String url;
  private long userid;


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


  public String getIconpath() {
    return iconpath;
  }

  public void setIconpath(String iconpath) {
    this.iconpath = iconpath;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public long getUserid() {
    return userid;
  }

  public void setUserid(long userid) {
    this.userid = userid;
  }

}
