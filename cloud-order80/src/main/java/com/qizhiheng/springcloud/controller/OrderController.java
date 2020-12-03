package com.qizhiheng.springcloud.controller;

import com.qizhiheng.springcloud.entities.CommonResult;
import com.qizhiheng.springcloud.entities.Payment;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    public static final String PAYWENT_URL = "http://CLOUD-SERVICE";

    @GetMapping("consumer/payment/creat")
    public CommonResult<Payment> creat(Payment payment) {

        return restTemplate.postForObject(PAYWENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYWENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getEntityPayment/{id}")
    public CommonResult<Payment> getEntityPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYWENT_URL+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return  entity.getBody();
        } else {
            return new CommonResult<Payment>(444,"操作失败");
        }
    }
}
