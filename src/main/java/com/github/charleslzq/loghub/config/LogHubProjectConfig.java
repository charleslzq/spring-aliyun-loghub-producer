package com.github.charleslzq.loghub.config;

import com.aliyun.openservices.log.producer.ProjectConfig;
import lombok.Data;

/**
 * Created by Charles on 2/27/2017.
 */
@Data
public class LogHubProjectConfig {
    private String project;
    private String endpoint;
    private String accessId;
    private String accessKey;

    public ProjectConfig generateProjectConfig() {
        return new ProjectConfig(project, endpoint, accessId, accessKey);
    }
}
