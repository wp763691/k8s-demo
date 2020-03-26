package com.k8s.demo.logging;

import com.k8s.demo.util.StringUtils;

/**
 * @ClassName: CloudLogger
 * @author jiangliu.wang
 * @date 2017年6月5日
 *
 */
public class CloudLogger implements Logger {

	private org.slf4j.Logger log;
	
	public CloudLogger(org.slf4j.Logger log) {
        this.log = log;
    }
	
	@Override
    public void error(String msg, String... args) {
        error(true, msg, args);
    }
	
	@Override
    public void error(String msg, Throwable ex, String... args) {
        log.error(StringUtils.format(msg, args), ex);
    }

	@Override
    public void info(String msg, String... args) {
        log.info(StringUtils.format(msg, args));
    }

	@Override
    public void info(String msg, Throwable ex, String... args) {
        log.info(StringUtils.format(msg, args), ex);
    }

	@Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

	@Override
    public void warn(String msg, String... args) {
        warn(true, msg, args);
    }

	@Override
    public void warn(String msg, Throwable ex, String... args) {
        log.warn(StringUtils.format(msg, args), ex);
    }

	@Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

	@Override
    public void debug(String msg, String... args) {
        log.debug(StringUtils.format(msg, args));
    }

	@Override
    public void debug(String msg, Throwable ex, String... args) {
        log.debug(StringUtils.format(msg, args), ex);
    }

	@Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

	@Override
    public void trace(String msg, String... args) {
        log.trace(StringUtils.format(msg, args));
    }

	@Override
    public void trace(String msg, Throwable ex, String... args) {
        log.trace(StringUtils.format(msg, args), ex);
    }


	
	@Override
	public void warn(boolean isMonitor, String msg, String... args) {
        log.warn(StringUtils.format(msg, args));
	}
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param isMonitor
	    * @param msg
	    * @param args
	    * @see com.synwing.cloud.common.logging.Logger#error(boolean, java.lang.String, java.lang.String[])
	    */
	    
	@Override
	public void error(boolean isMonitor, String msg, String... args) {
        log.error(StringUtils.format(msg, args));
	}

}
