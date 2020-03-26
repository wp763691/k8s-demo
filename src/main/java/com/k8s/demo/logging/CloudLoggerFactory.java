package com.k8s.demo.logging;

import org.slf4j.LoggerFactory;

/**
 * @ClassName: CloudLoggerFactory
 * @Description: 云端日志工厂
 * @author jiangliu.wang
 * @date 2017年6月5日
 *
 */
public class CloudLoggerFactory{

	public static Logger getLoggerImpl(Class<?> cls) {
		return new CloudLogger(LoggerFactory.getLogger(cls));
	}

	public static Logger getLoggerImpl(String name) {
		return new CloudLogger(LoggerFactory.getLogger(name));
	}
	
}
