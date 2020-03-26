package com.k8s.demo.logging;


/**
 * @ClassName:		Logger
 * @Description:	Main logger interface for logging things
 * @author：		    wangjl 
 */
public interface Logger {
	
	void trace(String msg, String... args);
    void trace(String msg, Throwable ex, String... args);
    boolean isTraceEnabled();
    
    void debug(String msg, String... args);
    void debug(String msg, Throwable ex, String... args);
    boolean isDebugEnabled();
    
    void info(String msg, String... args);
    void info(String msg, Throwable ex, String... args);
    boolean isInfoEnabled();

    void warn(boolean isMonitor, String msg, String... args);
    void warn(String msg, String... args);
    void warn(String msg, Throwable ex, String... args);

    void error(boolean isMonitor, String msg, String... args);
    void error(String msg, String... args);
    void error(String msg, Throwable ex, String... args);

}
