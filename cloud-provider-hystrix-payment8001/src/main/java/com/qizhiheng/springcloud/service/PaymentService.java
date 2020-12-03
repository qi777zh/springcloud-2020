package com.qizhiheng.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "payment_TimeOutHandler")
public class PaymentService {

    /**
     * 正常访问一定成功
     * @param id
     * @return
     */
    public  String payment_Ok(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" payment_ok,id: "+id;
    }

    /**
     * 正常访问延时反应
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public  String payment_TimeOut(Integer id){

        int timeOut = 3;
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" payment_TimeOut超时"+timeOut+",id: "+id;
    }

    /**
     * 备用方法
     * @param id
     * @return
     */
    public  String payment_TimeOutHandler(Integer id){

        return "线程池： "+Thread.currentThread().getName()+" 系统繁忙，请稍后再试：payment_TimeOutHandler,id: "+id;
    }


    //---------服务熔断
    @HystrixCommand(fallbackMethod ="payment_TimeOutHandler",commandProperties= {
        @HystrixProperty(name ="circuitBreaker.enabled",value="true"), //是否开后新路器
        @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
        @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期(短路多久以后开始尝试是否恢复，默认5s)
        @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage",value="60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){

        if (id < 0){
                throw new RuntimeException("******id 不能负数");
        }

        String  serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() +"调用成功，流水号：" + serialNumber;
    }
}
