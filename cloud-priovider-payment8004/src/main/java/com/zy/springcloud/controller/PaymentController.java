package com.zy.springcloud.controller;

import com.sun.javafx.tools.packager.Log;
import com.zy.springcloud.entities.CommonResult;
import com.zy.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
//    @Resource
//    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
