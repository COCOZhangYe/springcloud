package com.zy.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zy.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_OK,id: " + id + "\t" + "哈哈哈";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeoutNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeoutNum);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id + "\t" + "耗时"+timeoutNum+"秒钟";

    }
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " 系统繁忙或者运行出错，请稍后再试,id: " + id + "\t" + "sorry~";
    }

    //=========服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        //相当于UUID.randomUUID().toString();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
