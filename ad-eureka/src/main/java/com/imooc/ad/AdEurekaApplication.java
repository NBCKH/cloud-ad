package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //开启eureka server 服务
public class AdEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdEurekaApplication.class, args);
	}

}
