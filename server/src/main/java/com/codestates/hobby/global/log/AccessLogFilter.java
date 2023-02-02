package com.codestates.hobby.global.log;

import org.apache.logging.log4j.ThreadContext;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class AccessLogFilter extends Filter<ILoggingEvent> {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (!event.getLevel().equals(Level.INFO))
			return FilterReply.DENY;

		if (ThreadContext.get("type").equals("access")) {
			return FilterReply.ACCEPT;
		} else {
			return FilterReply.NEUTRAL;
		}
	}
}
