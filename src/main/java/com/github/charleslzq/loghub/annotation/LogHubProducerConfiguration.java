package com.github.charleslzq.loghub.annotation;

import com.aliyun.openservices.log.producer.LogProducer;
import com.github.charleslzq.loghub.config.LogHubProducerProperties;
import com.github.charleslzq.loghub.config.LogHubTemplateConfig;
import com.github.charleslzq.loghub.config.SourceType;
import com.github.charleslzq.loghub.converter.LogItemConverter;
import com.github.charleslzq.loghub.producer.LogHubProducerFactory;
import com.github.charleslzq.loghub.producer.LogHubTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Charles on 2/27/2017.
 */
@Configuration
@EnableConfigurationProperties(LogHubProducerProperties.class)
public class LogHubProducerConfiguration {

    @Autowired
    private LogHubProducerProperties logHubProducerProperties;
    private String hostIp = "127.0.0.1";
    private String hostName = "localhost";

    @Bean
    public LogHubProducerFactory logHubProducerFactory(DefaultListableBeanFactory defaultListableBeanFactory) {
        try {
            hostIp = InetAddress.getLocalHost().getHostAddress();
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        LogHubProducerFactory factory = new LogHubProducerFactory(logHubProducerProperties);
        logHubProducerProperties.getTemplates().entrySet().forEach(entry -> {
            String templateName = entry.getKey();
            LogHubTemplateConfig config = entry.getValue();
            String source = config.getSource() == SourceType.HOST_IP ? hostIp : hostName;
            try {
                LogItemConverter converter = (LogItemConverter) Class.forName(config.getConverter()).newInstance();
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(LogHubTemplate.class);
                builder.addConstructorArgValue(factory.getProducer())
                        .addConstructorArgValue(config.getProject())
                        .addConstructorArgValue(config.getStore())
                        .addConstructorArgValue(source)
                        .addConstructorArgValue(config.getTopic())
                        .addConstructorArgValue(converter);
                defaultListableBeanFactory.registerBeanDefinition(templateName, builder.getBeanDefinition());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return factory;
    }

    @Bean
    public LogProducer logProducer(LogHubProducerFactory factory) {
        return factory.getProducer();
    }
}
