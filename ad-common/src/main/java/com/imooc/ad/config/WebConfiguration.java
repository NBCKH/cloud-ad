package com.imooc.ad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by chenkaihua on 2019-04-19 14:06
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //转换器 可以将java 对象转换成json格式
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 清空转换器容器
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
