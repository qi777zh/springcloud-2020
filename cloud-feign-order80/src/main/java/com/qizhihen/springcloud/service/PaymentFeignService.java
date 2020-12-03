package com.qizhihen.springcloud.service;

import com.qizhihen.springcloud.controller.OrderFeignController;
import com.qizhiheng.springcloud.entities.CommonResult;
import com.qizhiheng.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-SERVICE",fallback = OrderFeignController.class)
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
     CommonResult<Payment> getPayment(@PathVariable("id") Long id);
}
