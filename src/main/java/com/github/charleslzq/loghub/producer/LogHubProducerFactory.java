package com.github.charleslzq.loghub.producer;

import com.aliyun.openservices.log.producer.LogProducer;
import com.github.charleslzq.loghub.config.LogHubProducerProperties;
import com.github.charleslzq.loghub.converter.LogItemConverter;

/**
 * Created by Charles on 2/27/2017.
 */
public class LogHubProducerFactory {
    private final LogHubProducerProperties logHubProducerProperties;
    private LogProducer producer;

    public LogHubProducerFactory(LogHubProducerProperties logHubProducerProperties) {
        this.logHubProducerProperties = logHubProducerProperties;
    }

    public LogProducer getProducer() {
        if (producer == null) {
            synchronized (this) {
                if (producer == null) {
                    producer = new LogProducer(logHubProducerProperties.generateProducerConfig());
                    logHubProducerProperties.getProjects().forEach(
                            logHubProjectConfig -> producer.setProjectConfig(logHubProjectConfig.generateProjectConfig())
                    );
                }
            }
        }
        return producer;
    }

    public <T> LogHubTemplate<T> createTemplate(String project, String store, LogItemConverter<T> converter) {
        return new LogHubTemplate<>(this.getProducer(), project, store, converter);
    }
}
