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
	/**
	 * predefined projects. producer can send message to this projects.
	 */
	private List<LogHubProjectConfig> projects = new ArrayList<>();
	/**
	 * predefined templates. The key of the map will be used as bean name of LogHubTemplate
	 */
	private Map<String, LogHubTemplateConfig> templates = new HashMap<>();
	private int packageTimeOutMs = 3000;
	/**
	 * max count of logs in a package
	 */
	private int logPerPackage = 4096;
	/**
	 * max size of a package in bytes
	 */
	private int bytesPerPackage = 5 * 1024 * 1024;
	/**
	 * max memory a producer will use
	 */
	private int maxMemSize = 1000 * 1024 * 1024;
	/**
	 * max io threads allowed
	 */
	private int maxIOThread = 50;
	/**
	 * update interval
	 */
	private int updateIntervalMs = 10 * 60 * 1000;
	/**
	 * the retry times
	 */
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
