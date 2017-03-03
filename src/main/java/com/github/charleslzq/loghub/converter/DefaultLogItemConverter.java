package com.github.charleslzq.loghub.converter;

import com.aliyun.openservices.log.common.LogItem;
import com.google.gson.Gson;

/**
 * Created by Charles on 2/27/2017.
 */
public class DefaultLogItemConverter<T> implements LogItemConverter<T> {
	private Gson gson = new Gson();

	@Override
	public LogItem convert(T target) {
		LogItem logItem = new LogItem();
		logItem.PushBack("content", gson.toJson(target));
		return logItem;
	}
}
