package com.qizhiheng.springcloud.service;

import com.qizhiheng.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    public  int creat(Payment payment);

    public Payment getPayment(@Param("id") Long id);
}
