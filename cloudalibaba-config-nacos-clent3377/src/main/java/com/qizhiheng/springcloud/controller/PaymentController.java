package com.qizhiheng.springcloud.controller;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持nacos的动态刷新功能
public class PaymentController {


    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public  String getConfigInfo(){

        return configInfo;
    }

}
