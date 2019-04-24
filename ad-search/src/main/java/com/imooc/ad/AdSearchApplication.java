package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 消费端和服务端都需要注册到服务端，保持服务的注册/续约，还有获取服务注册信息
 * 消费端调用服务端可以使用 ribbon/feign 远程调用
 */
@EnableFeignClients     //使用 feign访问其他的微服务
@EnableEurekaClient     //客户端
@EnableHystrix          //使用断路器
@EnableCircuitBreaker   //使用断路器
@EnableDiscoveryClient  //开启微服务发现能力，ribbon访问微服务
@EnableHystrixDashboard //启动监控
@SpringBootApplication
public class AdSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdSearchApplication.class, args);
    }

    //使用ribbon调用服务端，直接使用 RestTemplate去调用就行了
    @Bean
    @LoadBalanced  //负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
