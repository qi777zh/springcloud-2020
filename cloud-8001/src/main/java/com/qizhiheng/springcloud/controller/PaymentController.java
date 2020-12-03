package com.qizhiheng.springcloud.controller;

import com.qizhiheng.springcloud.entities.CommonResult;
import com.qizhiheng.springcloud.entities.Payment;
import com.qizhiheng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.creat(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功--"+serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败--"+serverPort, null);
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult getPaymentid(@PathVariable("id") Long id) {

        Payment result = paymentService.getPayment(id);
        log.info("*****查询结果：88888" + result);

        if (result != null) {
            return new CommonResult(200, "查询成功--"+serverPort, result);
        } else {
            return new CommonResult(444, id+"没有对应记录--"+serverPort, null);
        }
    }

    @GetMapping(value="/payment/lb")
    public String getPaymentlb() {

      return serverPort;
    }

    @GetMapping("payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String element : services){
            log.info("******element:"+element);
        }
       List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-SERVICE");
        for ( ServiceInstance instance : instances){
                log.info(instance.getInstanceId()+"----"+instance.getHost()+"----"+instance.getPort());
        }
        return  this.discoveryClient;
    }
}
