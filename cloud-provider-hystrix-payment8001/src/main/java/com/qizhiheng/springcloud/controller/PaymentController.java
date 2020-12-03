package com.qizhiheng.springcloud.controller;

import com.qizhiheng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String  serverPort;


    @GetMapping("/payment/hystrix/ok/{id}")
    public  String payment_Ok(@PathVariable("id") Integer id){

        String result  = paymentService.payment_Ok(id);
        log.info("----------------"+result);
        return result;
    }


    @GetMapping("/payment/hystrix/timeout/{id}")
    public  String payment_Timeout(@PathVariable("id") Integer id){

        String result  = paymentService.payment_TimeOut(id);
        log.info("----------------"+result);
        return result;
    }

    //-----服务熔断

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        String result  = paymentService.paymentCircuitBreaker(id);
        log.info("result: "+result);
        return result;
    }
}
