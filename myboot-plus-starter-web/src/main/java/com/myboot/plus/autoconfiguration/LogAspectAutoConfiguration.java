package com.myboot.plus.autoconfiguration;

import com.myboot.plus.aspect.HttpRequesetLogAspect;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @auth zyf
 * @date 2021/11/26
 * @desc 日志切面自动配置
 */
@Configurable
public class LogAspectAutoConfiguration {

    @Bean
    public HttpRequesetLogAspect httpRequesetLogAspect(){
        return new HttpRequesetLogAspect();
    }
}
