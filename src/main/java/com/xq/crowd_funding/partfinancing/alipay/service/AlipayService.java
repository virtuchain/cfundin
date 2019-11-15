package com.xq.crowd_funding.partfinancing.alipay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AlipayService {
    void  aliPay(HttpServletResponse response, HttpServletRequest request) throws IOException;
}
