package com.qizhiheng.springcloud.service.impl;

import com.qizhiheng.springcloud.dao.PaymentDao;
import com.qizhiheng.springcloud.entities.Payment;
import com.qizhiheng.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int creat(Payment payment) {
        return paymentDao.creat(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDao.getPayment(id);
    }
}
