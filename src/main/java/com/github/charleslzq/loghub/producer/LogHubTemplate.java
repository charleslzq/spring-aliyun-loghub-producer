package com.github.charleslzq.loghub.producer;

import com.aliyun.openservices.log.producer.ILogCallback;
import com.aliyun.openservices.log.producer.LogProducer;
import com.github.charleslzq.loghub.converter.LogItemConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Charles on 2/27/2017.
 */
public class LogHubTemplate<T> {
    private final LogProducer logProducer;
    private final String project;
    private final String store;
    private final LogItemConverter<T> converter;

    LogHubTemplate(LogProducer logProducer, String project, String store, LogItemConverter<T> converter) {
        this.logProducer = logProducer;
        this.project = project;
        this.store = store;
        this.converter = converter;
    }

    public void send(String topic, String source, List<T> items, ILogCallback callback) {
        logProducer.send(
                project,
                store,
                topic,
                source,
                items.stream().map(converter::convert).collect(Collectors.toList()),
                callback
        );
    }

    public void send(String topic, String source, List<T> items) {
        logProducer.send(
                project,
                store,
                topic,
                source,
                items.stream().map(converter::convert).collect(Collectors.toList())
        );
    }

    public void send(String source, List<T> items, ILogCallback callback) {
        send("", source, items, callback);
    }

    public void send(String source, List<T> items) {
        send("", source, items);
    }
}
