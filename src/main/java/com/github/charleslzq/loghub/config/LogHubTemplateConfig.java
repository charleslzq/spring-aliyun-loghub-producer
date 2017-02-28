package com.github.charleslzq.loghub.config;

import com.github.charleslzq.loghub.converter.DefaultLogItemConverter;
import lombok.Data;

/**
 * Created by Charles on 2/28/2017.
 */
@Data
public class LogHubTemplateConfig {
    private String project;
    private String store;
    private SourceType source = SourceType.HOST_IP;
    private String topic = "";
    private String converter = DefaultLogItemConverter.class.getName();
}
