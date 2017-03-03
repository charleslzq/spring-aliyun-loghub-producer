package com.github.charleslzq.loghub.config;

import com.github.charleslzq.loghub.converter.DefaultLogItemConverter;
import com.github.charleslzq.loghub.converter.LogItemConverter;
import lombok.Data;

/**
 * Created by Charles on 2/28/2017.
 */
@Data
public class LogHubTemplateConfig {
	/**
	 * the project to send message to
	 */
	private String project;
	/**
	 * the logstore to send message to
	 */
	private String store;
	/**
	 * specify ip or host name as source field of loghub message
	 */
	private SourceType source = SourceType.HOST_IP;
	/**
	 * the topic to send message to
	 */
	private String topic = "";
	/**
	 * the converter which can transform raw object to ali LogItem
	 */
	private Class<? extends LogItemConverter> converter = DefaultLogItemConverter.class;
}
