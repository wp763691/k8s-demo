package com.k8s.demo.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName: ExceptionUtil
 * @Description: 异常工具类
 * @author jiangliu.wang
 * @date 2017年6月28日
 *
 */

public class ExceptionUtil {
	
	/**
	 * @Title: getExceptionDetail
	 * @Description: 获取异常堆栈信息
	 * @param @param e
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String getExceptionDetail(Exception e) {
		if (e == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String msg = sw.toString();
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return msg;
	}

	public static String getExceptionDetail(Throwable e) {
		if (e == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String msg = sw.toString();
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return msg;
	}
}
