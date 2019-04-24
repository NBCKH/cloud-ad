package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.client.SponsorClient;
import com.imooc.ad.vo.AdPlan;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private SponsorClient sponsorClient;


    //使用feign调用的,给服务端结构定义一个接口，FeignClient 声明是调用哪个服务
    @IgnoreResponseAdvice  //忽略返回包装
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlans -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

    /**
     * 通过ribbon调用微服务通过封装的http
     * @param request
     * @return
     */
    @SuppressWarnings("all")
    @IgnoreResponseAdvice  //忽略返回包装
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPlan>> getAdPlansByRebbon(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlansByRibbon -> {}", JSON.toJSONString(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan", request, CommonResponse.class).getBody();
    }
}
