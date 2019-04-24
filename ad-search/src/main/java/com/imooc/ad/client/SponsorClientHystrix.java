package com.imooc.ad.client;

import com.imooc.ad.vo.AdPlan;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SponsorClientHystrix implements SponsorClient {

    //短路器的使用，实现客户端接口
    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse().setCode(-1).setMessage("eureka-client-ad-sponsor error");
    }
}
