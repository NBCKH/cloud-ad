package com.imooc.ad.client;

import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * feign 调用呢比较便捷，申明式的webservice客户端，创建一个接口，一个注解
 */
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)       //指明调用哪个微服务，fallback服务发生错误，实现服务降级 一旦出错调用SponsorClientHystrix
public interface SponsorClient {

    //调用的微服务url
    @RequestMapping(value = "/ad-sponsor/get/adPlan",  method = RequestMethod.POST)
    CommonResponse getAdPlans(@RequestBody AdPlanGetRequest request);
}
