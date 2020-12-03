package com.qizhiheng.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public  String getPayment(@PathVariable("id") Integer id){

        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);

    }

}
