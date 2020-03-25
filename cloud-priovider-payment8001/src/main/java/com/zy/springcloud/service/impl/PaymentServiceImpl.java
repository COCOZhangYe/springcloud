package com.zy.springcloud.service.impl;

import com.zy.springcloud.dao.PaymentDao;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        int num = paymentDao.create(payment);
        return num;
    }

    public Payment getPmById(Integer id){
        Payment pmById = paymentDao.getPmById(id);
        return pmById;
    }
}
