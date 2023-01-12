package com.myboot.plus.redisson.config;

import lombok.Data;
import org.redisson.config.ClusterServersConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author zyf
 * @date 2023/1/11
 * @desc 集群模式配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redisson.cluster")
public class ClusterModelConfig extends ClusterServersConfig implements ObtainModelInterface{

    @Override
    public boolean isOneselfModel() {
        return !CollectionUtils.isEmpty(super.getNodeAddresses());
    }


}
