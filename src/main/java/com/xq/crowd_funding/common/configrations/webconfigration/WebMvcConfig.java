package com.xq.crowd_funding.common.configrations.webconfigration;/*
    @auther yangjie
*/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {

        public  void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins("*") // 允许所有域
                    .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                    .maxAge(3600)
                    .allowCredentials(true); // 允许用户发送，处理cookie
        }
}
