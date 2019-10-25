package com.xq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CrowdFundingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrowdFundingApplication.class, args);
    }

}
