package com.xq.crowd_funding.partfinancing.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xq.crowd_funding.common.pojo.TOrder;
import com.xq.crowd_funding.partfinancing.alipay.AlipayConfig;
import com.xq.crowd_funding.partfinancing.alipay.service.AlipayService;
import com.xq.crowd_funding.partfinancing.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @RequestMapping("/pay")
    public void payMent(HttpServletResponse response, HttpServletRequest request) throws IOException {
        try {
            alipayService.aliPay(response,request);
//            response.sendRedirect("/view/project.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}