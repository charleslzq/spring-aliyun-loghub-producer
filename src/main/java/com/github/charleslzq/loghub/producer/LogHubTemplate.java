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
	private final String source;
	private final String defaultTopic;
	private final LogItemConverter<T> converter;

	LogHubTemplate(LogProducer logProducer,
	               String project,
	               String store,
	               String source,
	               String defaultTopic,
	               LogItemConverter<T> converter) {
		this.logProducer = logProducer;
		this.project = project;
		this.store = store;
		this.source = source;
		this.converter = converter;
		this.defaultTopic = defaultTopic;
	}

	public void send(String topic, List<T> items, ILogCallback callback) {
		logProducer.send(
				project,
				store,
				topic,
				source,
				items.stream().map(converter::convert).collect(Collectors.toList()),
				callback
		);
	}

	public void send(String topic, List<T> items) {
		logProducer.send(
				project,
				store,
				topic,
				source,
				items.stream().map(converter::convert).collect(Collectors.toList())
		);
	}

	public void send(List<T> items, ILogCallback callback) {
		send(defaultTopic, items, callback);
	}

	public void send(List<T> items) {
		send(defaultTopic, items);
	}
}
