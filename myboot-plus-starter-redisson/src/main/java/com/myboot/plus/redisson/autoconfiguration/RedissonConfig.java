package com.myboot.plus.redisson.autoconfiguration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyf
 * @date 2022/12/20
 * @desc RedissonconfigBean
 */
@Configuration
public class RedissonConfig {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port:6379}")
    private int port;
    @Value(value = "${spring.redis.database:0}")
    private int database;
    @Value(value = "${spring.redis.password:}")
    private String password;
    @Value(value = "${spring.redisson.lockWatchdogTimeout:}")
    private Long lockWatchdogTimeout;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();

        if (null != lockWatchdogTimeout) {
            //指定看门狗续期时间
            config.setLockWatchdogTimeout(lockWatchdogTimeout);
        }

        if (!host.contains(",")) { //Redis单节点
            SingleServerConfig singleServerConfig = config.useSingleServer();
            //可以用"rediss://"来启用SSL连接
            String address = "redis://" + host + ":" + port;
            singleServerConfig.setAddress(address);
            //设置 数据库编号
            singleServerConfig.setDatabase(database);
            if (!StringUtils.isEmpty(password)) {
                singleServerConfig.setPassword(password);
            }
            singleServerConfig.setPingConnectionInterval(50000);
            //连接池大小:默认值：64
            // singleServerConfig.setConnectionPoolSize()
        } else { //Redis集群节点
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            List<String> nodeAddresses = new ArrayList<>();
            String[] hosts = host.split(",");
            for (int i = 0 ; i < hosts.length; i ++) {
                nodeAddresses.add("redis://"+hosts[i]);
            }
            clusterServersConfig.setNodeAddresses(nodeAddresses);
            if (!StringUtils.isEmpty(password)) {
                clusterServersConfig.setPassword(password);
            }

        }
        return Redisson.create(config);
    }




}
