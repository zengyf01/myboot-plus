package com.myboot.plus.response;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2025/2/7 09:29
 * @Description: 配置请求响应消息转换器
 */
@Configuration
public class SetWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
        converters.add(0,new StringConvoter());
        converters.add(1,new EntityResponseConverter());
    }
}
