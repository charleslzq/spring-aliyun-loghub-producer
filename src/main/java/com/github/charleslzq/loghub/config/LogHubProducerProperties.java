package com.github.charleslzq.loghub.config;

import com.aliyun.openservices.log.producer.ProducerConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2/27/2017.
 */
@ConfigurationProperties(
        prefix = "spring.ali.log-hub.producer"
)
@Data
public class LogHubProducerProperties {
    private List<LogHubProjectConfig> projects = new ArrayList<>();
    private Map<String, LogHubTemplateConfig> templates = new HashMap<>();
    private int packageTimeOutMs = 3000;
    private int logPerPackage = 4096;
    private int bytesPerPackage = 5 * 1024 * 1024;
    private int maxMemSize = 1000 * 1024 * 1024;
    private int maxIOThread = 50;
    private int updateIntervalMs = 10 * 60 * 1000;
    private int retry = 3;

    public ProducerConfig generateProducerConfig() {
        ProducerConfig config = new ProducerConfig();
        config.packageTimeoutInMS = packageTimeOutMs;
        config.logsCountPerPackage = logPerPackage;
        config.logsBytesPerPackage = bytesPerPackage;
        config.memPoolSizeInByte = maxMemSize;
        config.maxIOThreadSizeInPool = maxIOThread;
        config.shardHashUpdateIntervalInMS = updateIntervalMs;
        config.retryTimes = retry;
        return config;
    }
}
