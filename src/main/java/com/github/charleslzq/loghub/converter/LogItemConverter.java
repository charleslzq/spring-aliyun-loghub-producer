package com.github.charleslzq.loghub.converter;

import com.aliyun.openservices.log.common.LogItem;

/**
 * Created by Charles on 2/27/2017.
 */
public interface LogItemConverter<T> {
    LogItem convert(T target);
}
