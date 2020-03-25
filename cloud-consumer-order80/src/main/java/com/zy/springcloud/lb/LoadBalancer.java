package com.zy.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //第一步：收集eureka上面存在的服务实例
    ServiceInstance instances(List<ServiceInstance> serviceInstance);
}
