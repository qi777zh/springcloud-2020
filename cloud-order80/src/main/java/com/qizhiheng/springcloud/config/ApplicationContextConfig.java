package com.qizhiheng.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//相当于：applicationcontext. xml <bean id=""class="">
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//用于RestTemplate负载均衡
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
