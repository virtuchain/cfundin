package com.xq.crowd_funding.partfinancing.controller;

import com.xq.crowd_funding.common.ResultEntity;
import com.xq.crowd_funding.common.pojo.TMember;
import com.xq.crowd_funding.common.pojo.TOrder;
import com.xq.crowd_funding.partfinancing.pojo.TReturn;
import com.xq.crowd_funding.partfinancing.service.IOrderService;
import com.xq.crowd_funding.partfinancing.service.ITReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
ClassName: OrderController
 订单的Controller
 addOrder:添加进订单
            参数projectid项目Id,rsupportmoney回报单价,sl支持数量,shdz收货地址,phone手机号码,username收货人姓名
@Description: TODO
@Author: GuoXinZhang
@Date: 11:29
@Time: 2019/11/4
@Version: 1.0
*/

@RestController
public class OrderController {

    @Autowired
    IOrderService iOrderService;
    @Autowired
    ITReturnService itReturnService;

    @PostMapping("/addOrder/{projectid}/{rsupportmoney}/{sl}/{shdz}/{phone}/{name}")
    public ResultEntity addOrder(@PathVariable("projectid") Integer projectid,@PathVariable("rsupportmoney")Integer rsupportmoney,@PathVariable("sl")Integer sl,@PathVariable("shdz")String shdz,@PathVariable("phone")String phone,@PathVariable("name")String name, HttpServletRequest request){
        TOrder tOrder = new TOrder();
        //获取用户信息
        HttpSession session = request.getSession();
//        TMember tMember = (TMember)session.getAttribute("");
//        tOrder.setMemberid(new Long(tMember.getId()).intValue());
        //将用户Id添加进订单表中
        tOrder.setMemberid(1);
        //将项目Id添加进订单表中
        tOrder.setProjectid(projectid);

        //通过支持金额和项目Id来获取回报Id
        TReturn tReturn = itReturnService.queryReturnById(projectid, rsupportmoney);
        System.out.println(new Long(tReturn.getId()).intValue());
        //将回报Id添加进订单表中
        tOrder.setReturnid(new Long(tReturn.getId()).intValue());

        //String.valueOf(new Date().getTime())订单号
        String ss = String.valueOf(new Date().getTime());
        tOrder.setOrdernum(ss);

        //创建时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //对时间进行格式化
        String date = format.format(new Date());
        //将时间添加进订单表中
        tOrder.setCreatedate(date);

        //支持金额
        int i1 = rsupportmoney * sl;
        tOrder.setMoney(i1);

        //回报数量
        tOrder.setRtncount(sl);

        //交易状态
        tOrder.setStatus("1");

        //收货地址
        tOrder.setAddress(shdz);

        //发票
        tOrder.setInvoice("0");

        //发票抬头
        tOrder.setInvoictitle("0");

        //备注
        tOrder.setRemark("0");

        session.setAttribute("tOrder",tOrder);

        int i = iOrderService.addOrder(tOrder);
        if (i>0){
            return ResultEntity.successWithData("订单添加成功");
        }
        return ResultEntity.failed("订单添加失败");
    }

    @PostMapping("/savaTOrder")
    public ResultEntity<String> savaTOrder(TOrder tOrder,Integer memberid){
        int i = iOrderService.savaTOrder(tOrder,memberid);
        if (i>0)
            return ResultEntity.successWithData("修改成功");
        return ResultEntity.failed("地址错误");
    }

}
