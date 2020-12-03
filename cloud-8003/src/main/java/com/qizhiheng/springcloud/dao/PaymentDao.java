package com.qizhiheng.springcloud.dao;


import com.qizhiheng.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

   public  int creat(Payment payment);

     public  Payment getPayment(@Param("id") Long id);
}
