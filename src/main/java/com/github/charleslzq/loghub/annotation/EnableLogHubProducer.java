package com.github.charleslzq.loghub.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by Charles on 2/27/2017.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogHubProducerConfiguration.class)
public @interface EnableLogHubProducer {
}
