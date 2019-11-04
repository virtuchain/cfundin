package com.xq.crowd_funding.common.utils;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author Maozhihao
 **/
@ToString
public class Page {
    private Integer totalno;//总共有多少页
    private Integer pageno; //当前页
    private List datas;//当前页数据
    private Integer totalsize;//总记录数
    private Integer pagesize;//最大条数

    public Page(Integer pageno,Integer pagesize){
        if(pageno<=0){
            this.pageno=1;
        }else {
            this.pageno=pageno;
        }
        if(pagesize<=0){
            this.pagesize=10;
        }else {
            this.pagesize=pagesize;
        }
    }



    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public List getDatas() {
        return datas;
    }

    public void setDatas(List datas) {
        this.datas = datas;
    }
    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }
    public Integer getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
        this.totalno=(totalsize  %  pagesize) == 0 ? (totalsize / pagesize) : (totalsize / pagesize + 1);
    }
    public Integer getTotalno() {
        return totalno;
    }

    private void setTotalno(Integer totalno) {
        this.totalno = totalno;
    }
    public  Integer getStartIndex(){
        return  (this.pageno-1)*pagesize;//取开始索引
    }
}
