package com.zy.springcloud.controller;

import com.zy.springcloud.service.PaymentFeignService;
import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPmById(@PathVariable("id") Integer id){
        return paymentFeignService.getPmById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        String feignTimeout = paymentFeignService.paymentFeignTimeout();
        return feignTimeout;
    }
}
