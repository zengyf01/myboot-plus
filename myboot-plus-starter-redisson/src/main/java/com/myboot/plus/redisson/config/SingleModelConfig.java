package com.myboot.plus.redisson.config;

import lombok.Data;
import org.redisson.config.SslProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URL;

/**
 * @author zyf
 * @date 2023/1/11
 * @desc 单节点模式配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redisson.single")
public class SingleModelConfig  implements ObtainModelInterface{
    private String address;
    private int subscriptionConnectionMinimumIdleSize = 1;
    private int subscriptionConnectionPoolSize = 50;
    private int connectionMinimumIdleSize = 24;
    private int connectionPoolSize = 64;
    private int database = 0;
    private long dnsMonitoringInterval = 5000L;
    private int idleConnectionTimeout = 10000;
    private int connectTimeout = 10000;
    private int timeout = 3000;
    private int retryAttempts = 3;
    private int retryInterval = 1500;
    private String password;
    private String username;
    private int subscriptionsPerConnection = 5;
    private String clientName;
    private boolean sslEnableEndpointIdentification = true;
    private SslProvider sslProvider;
    private URL sslTruststore;
    private String sslTruststorePassword;
    private URL sslKeystore;
    private String sslKeystorePassword;
    private String[] sslProtocols;
    private int pingConnectionInterval;
    private boolean keepAlive;
    private boolean tcpNoDelay;

    @Override
    public boolean isOneselfModel() {
        return StringUtils.hasText(this.address);
    }
}
