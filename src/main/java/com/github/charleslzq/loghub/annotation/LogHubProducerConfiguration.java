package com.github.charleslzq.loghub.annotation;

import com.github.charleslzq.loghub.config.LogHubProducerProperties;
import com.github.charleslzq.loghub.producer.LogHubProducerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Charles on 2/27/2017.
 */
@Configuration
@EnableConfigurationProperties(LogHubProducerProperties.class)
public class LogHubProducerConfiguration {

    @Autowired
    private LogHubProducerProperties logHubProducerProperties;

    @Bean
    public LogHubProducerFactory logHubProducerFactory() {
        return new LogHubProducerFactory(logHubProducerProperties);
    }
}
