package com.zy.springcloud.dao;

import com.zy.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPmById(@Param("id") Integer id);
}
