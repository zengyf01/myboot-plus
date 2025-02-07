package com.myboot.plus.autoconfiguration;

import com.myboot.plus.response.EntityResponseConverter;
import com.myboot.plus.response.StringConvoter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @Author: zyf
 * @Date: 2025/2/7 14:25
 * @Description: 请求响应统一格式自动配置
 */
@Configurable
public class HttpMessageConverterConfiguration {

    /**
     * 当配置文件中myboot.entity2response.enabled指定为false时则关闭该转换，matchIfMissing=true表示模式开启该转换
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "myboot.entity2response.enabled", havingValue = "true", matchIfMissing = true)
    public EntityResponseConverter entityResponseConverter() {
        return new EntityResponseConverter();
    }

    /**
     * 当配置文件中myboot.string2response.enabled指定为false时则关闭该转换，matchIfMissing=true表示模式开启该转换
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "myboot.string2response.enabled", havingValue = "true", matchIfMissing = true)
    public StringConvoter stringConvoter() {
        return new StringConvoter();
    }
}
