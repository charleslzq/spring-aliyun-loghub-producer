package com.github.charleslzq.loghub.config;

import com.aliyun.openservices.log.producer.ProjectConfig;
import lombok.Data;

/**
 * Created by Charles on 2/27/2017.
 */
@Data
public class LogHubProjectConfig {
	/**
	 * the project name
	 */
	private String project;
	/**
	 * the endpoint to access alibaba loghub service
	 */
	private String endpoint;
	/**
	 * the accessId of which holds the appropriate authority
	 */
	private String accessId;
	/**
	 * the accessKey of which holds the appropriate authority
	 */
	private String accessKey;


	public ProjectConfig generateProjectConfig() {
		return new ProjectConfig(project, endpoint, accessId, accessKey);
	}
}
