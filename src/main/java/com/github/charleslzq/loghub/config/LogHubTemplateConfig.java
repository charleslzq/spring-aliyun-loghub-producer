package com.github.charleslzq.loghub.config;

import com.github.charleslzq.loghub.converter.DefaultLogItemConverter;
import com.github.charleslzq.loghub.converter.LogItemConverter;
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
    private Class<? extends LogItemConverter> converter = DefaultLogItemConverter.class;
}
