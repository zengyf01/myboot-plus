package com.myboot.plus.redisson.config;

import lombok.Data;
import org.redisson.config.MasterSlaveServersConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author zyf
 * @date 2023/1/11
 * @desc 主从模式配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redisson.master-slave")
public class MasterSlaveModelConfig extends MasterSlaveServersConfig implements ObtainModelInterface{

    @Override
    public boolean isOneselfModel() {
        return StringUtils.hasText(this.getMasterAddress()) && !CollectionUtils.isEmpty(this.getSlaveAddresses()) ;
    }
}
