package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Created by chenkaihua on 2019-04-18 15:55
 */
@Slf4j
@Component
public class PreRequestFilter extends ZuulFilter {

    // 定义filter类型
    @Override
    public String filterType() {
        // 表示请求进来的
        return FilterConstants.PRE_TYPE;
    }

    //定义filter执行循序
    @Override
    public int filterOrder() {
        return 0;
    }

    //该filter时候应该之心
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //最后执行的内容
    @Override
    public Object run() throws ZuulException {
        // 这里是 zuul 请求的上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());
        return null;
    }
}
