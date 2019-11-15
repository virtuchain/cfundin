package com.xq.crowd_funding.partfinancing.alipay.service.imp;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xq.crowd_funding.common.pojo.TOrder;
import com.xq.crowd_funding.partfinancing.alipay.AlipayConfig;
import com.xq.crowd_funding.partfinancing.alipay.service.AlipayService;
import com.xq.crowd_funding.partfinancing.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    IOrderService iOrderService;

    @Override
    public void aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
//        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        HttpSession session = request.getSession();
        TOrder tOrder = (TOrder) session.getAttribute("tOrder");
        Integer order_number = new Long(tOrder.getOrdernum()).intValue();
        //付款金额，从前台获取，必填
        Integer total_amount = new Long(tOrder.getMoney()).intValue();
        //订单名称，必填
        String subject = new String("买吧，不缺钱");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = "";
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
            System.out.println(result);

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //输出
        out.println(result);
//        int i = iOrderService.updateStatus(new Long(tOrder.getOrdernum()).intValue());
        log.info("返回结果={}",result);

    }
}
