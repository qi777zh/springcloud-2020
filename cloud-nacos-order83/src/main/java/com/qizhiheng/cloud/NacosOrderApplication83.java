package com.qizhiheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderApplication83 {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderApplication83.class,args);
    }
}
