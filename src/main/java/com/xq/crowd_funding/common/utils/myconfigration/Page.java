package com.xq.crowd_funding.common.utils.myconfigration;

import lombok.Data;
import lombok.ToString;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * @Author Maozhihao
 **/

public class Page {
    private  Integer pageno;//当前页
    private  Integer pagesize;
    private  List datas;//当前页数据
    private  Integer totalsize;//总共有多少条记录
    private  Integer totalno;//总共有多少条页


}
