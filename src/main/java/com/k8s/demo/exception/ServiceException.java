
/**  
 * @Title: ServiceException.java
 * @Package com.synwing.cloud.common.exception
 * @Description: 层的异常信息
 * @author wp
 * @date 2017年6月5日
 * @version V1.0  
 */

package com.k8s.demo.exception;

import com.k8s.demo.util.ServiceExceptionInfo;

public class ServiceException extends Exception {
	

	int errorCode = -1;

	String message;

	Object exception;
	

	/**
	 *
	 */
	private static final long serialVersionUID = 3726430705530309153L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		this(message, null);
	}

	public ServiceException(String message, Exception exception) {
		super(message, exception);
		this.message = message;
		this.exception = exception;
	}

	public ServiceException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public ServiceException(ServiceExceptionInfo exceptionInfo) {
		super();
		this.errorCode = exceptionInfo.getCode();
		this.message = exceptionInfo.getMsg();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getException() {
		return exception;
	}

	public void setException(Object exception) {
		this.exception = exception;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}