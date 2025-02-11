package com.myboot.plus.config;

import com.myboot.plus.messageconvoter.Entity2ResponseConverter;
import com.myboot.plus.messageconvoter.String2ResponseConvoter;
import com.myboot.plus.returnhandler.ResponseEntityReturnValueHandler;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2025/2/7 09:29
 * @Description: 配置请求响应消息转换器
 */
public class MvcConfigurer implements WebMvcConfigurer {

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new String2ResponseConvoter());
        converters.add(1, new Entity2ResponseConverter());
    }

    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(0,new ResponseEntityReturnValueHandler());
    }
}
