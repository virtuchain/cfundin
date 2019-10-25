package com.xq.crowd_funding.webcontorller;/*
    @auther yangjie
*/

import com.xq.crowd_funding.servicepackage.IRaiseFundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 发起众筹
 */
@RequestMapping("raisefunding")
@ResponseBody
public class RaiseFundingController {

    @Autowired
    IRaiseFundingService raiseFundingImp;

}
