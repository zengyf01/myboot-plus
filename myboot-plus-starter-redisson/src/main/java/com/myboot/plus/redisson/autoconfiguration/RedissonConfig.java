package com.myboot.plus.redisson.autoconfiguration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    @Value(value = "${spring.redis.port}")
    private int port;
    @Value(value = "${spring.redis.database:0}")
    private int database;
    @Value(value = "${spring.redis.password:}")
    private String password;


    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        if (!host.contains(",")) { //Redis单节点
            SingleServerConfig singleServerConfig = config.useSingleServer();
            //可以用"rediss://"来启用SSL连接
            String address = "redis://" + host + ":" + port;
            singleServerConfig.setAddress(address);
            //设置 数据库编号
            singleServerConfig.setDatabase(database);
            singleServerConfig.setPassword(password);
            //连接池大小:默认值：64
            // singleServerConfig.setConnectionPoolSize()
        } else { //Redis多节点
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            List<String> nodeAddresses = new ArrayList<>();
            String[] hosts = host.split(",");
            for (int i = 0 ; i < hosts.length; i ++) {
                nodeAddresses.add("redis://"+hosts[i]);
            }
            clusterServersConfig.setNodeAddresses(nodeAddresses);
        }
        return Redisson.create(config);
    }




}
