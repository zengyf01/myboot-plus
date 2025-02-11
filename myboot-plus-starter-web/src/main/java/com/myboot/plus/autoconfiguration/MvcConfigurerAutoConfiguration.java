package com.myboot.plus.autoconfiguration;

import com.myboot.plus.config.MvcConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zyf
 * @Date: 2025/2/10 16:20
 * @Description: TODO
 */
@Configuration
public class MvcConfigurerAutoConfiguration {

//    @Bean
//    @ConditionalOnMissingBean(MvcConfigurer.class)
//    public MvcConfigurer mvcConfigurer(){
//        return new MvcConfigurer();
//    }
}
