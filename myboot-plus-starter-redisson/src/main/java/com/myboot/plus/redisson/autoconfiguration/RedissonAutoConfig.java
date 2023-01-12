package com.myboot.plus.redisson.autoconfiguration;

import com.myboot.plus.redisson.config.ClusterModelConfig;
import com.myboot.plus.redisson.config.MasterSlaveModelConfig;
import com.myboot.plus.redisson.config.SentinelModelConfig;
import com.myboot.plus.redisson.config.SingleModelConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * @author zyf
 * @date 2022/12/20
 * @desc 创建Redisson客户端
 */
@Configuration
@Import(value = {ClusterModelConfig.class, MasterSlaveModelConfig.class, SentinelModelConfig.class, SingleModelConfig.class})
public class RedissonAutoConfig {

    @Autowired
    private ClusterModelConfig clusterModelConfig;

    @Autowired
    private MasterSlaveModelConfig masterSlaveModelConfig;

    @Autowired
    private SentinelModelConfig sentinelModelConfig;


    @Autowired
    private SingleModelConfig singleModelConfig;

    @Primary
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    RedissonClient redisson() {
        Config config = new Config();
        /**集群模式*/
        if (clusterModelConfig.isOneselfModel()) {
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            BeanUtils.copyProperties(clusterModelConfig, clusterServersConfig);
        }
        /**主从模式*/
        if (masterSlaveModelConfig.isOneselfModel()) {
            MasterSlaveServersConfig masterSlaveServersConfig = config.useMasterSlaveServers();
            BeanUtils.copyProperties(masterSlaveModelConfig, masterSlaveServersConfig);
        }
        /**哨兵模式*/
        if (sentinelModelConfig.isOneselfModel()) {
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            BeanUtils.copyProperties(sentinelModelConfig, sentinelServersConfig);
        }
        /**单节点模式*/
        if (singleModelConfig.isOneselfModel()) {
            SingleServerConfig singleServerConfig = config.useSingleServer();
            BeanUtils.copyProperties(singleModelConfig, singleServerConfig);
            if (!singleServerConfig.getAddress().contains("redis://") || !singleServerConfig.getAddress().contains("redisson://")) {
                singleServerConfig.setAddress("redis://" + singleServerConfig.getAddress());
            }
        }

        return Redisson.create(config);
    }




}
