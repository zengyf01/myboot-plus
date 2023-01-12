package com.myboot.plus.redisson.config;

import lombok.Data;
import org.redisson.config.SentinelServersConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author zyf
 * @date 2023/1/11
 * @desc 哨兵模式配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redisson.sentinel")
public class SentinelModelConfig extends SentinelServersConfig implements ObtainModelInterface{

    @Override
    public boolean isOneselfModel() {
        return !CollectionUtils.isEmpty(this.getSentinelAddresses());
    }
}
