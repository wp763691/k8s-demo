
/**  
 * @Title: ServiceExceptionInfo.java
 * @Package com.synwing.cloud.common.msg
 * @Description: 业务异常信息
 * @author jiangliu.wang
 * @date 2017年6月7日
 * @version V1.0  
 */

package com.k8s.demo.util;

/**
 * @ClassName: ServiceExceptionInfo
 * @Description: 提供给调用者返回错误信息和错误码
 * @author jiangliu.wang
 * @date 2017年6月7日
 *
 */

public interface ServiceExceptionInfo {

	String getMsg();

	void setMsg(String msg);

	int getCode();

	void setCode(int code);

}
