package com.xq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CrowdFundingApplication {

    public static void main(String[] args)
    {

        SpringApplication.run(CrowdFundingApplication.class, args);
    }

}
