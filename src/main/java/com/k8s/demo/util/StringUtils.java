package com.k8s.demo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.k8s.demo.exception.ServiceException;

/**
 * @ClassName: StringUtils
 * @Description: 通过参数的形式格式化日志
 * @author jiangliu.wang
 * @date 2017年6月5日
 *
 */
public class StringUtils {

	public final static String DEFAULT_CHARSET_NAME = "UTF-8";
	
	private final static char ARGUMENT_SYMBOL = '#';
	
    /**
     * Formats messages using parameters. For example, the call:
     * 
     * <pre>
     * format("foo #0", "bob");
     * </pre>
     * 
     * will return:
     * <pre>
     * foo bob
     * </pre>
     * 
     * @param msg The message
     * @param args A list of arguments.  A maximum of 10 are supported.
     * @return The formatted string
     */
    public static String format(String msg, String... args) {
        if (msg != null && msg.length() > 0 && msg.indexOf(ARGUMENT_SYMBOL) > -1) {
            StringBuilder sb = new StringBuilder();
            boolean isArg = false;
            for (int x = 0; x < msg.length(); x++) {
                char c = msg.charAt(x);
                if (isArg) {
                    isArg = false;
                    if (Character.isDigit(c)) {
                        int val = Character.getNumericValue(c);
                        if (val >= 0 && val < args.length) {
                            sb.append(args[val]);
                            continue;
                        }
                    }
                    sb.append(ARGUMENT_SYMBOL);
                }
                if (c == ARGUMENT_SYMBOL) {
                    isArg = true;
                    continue;
                }
                sb.append(c);
            }
            
            if (isArg) {
                sb.append(ARGUMENT_SYMBOL);
            }
            return sb.toString();
        }
        return msg;
    }
    
    public static String formatURLEncode(String msg, String... args) throws ServiceException {
        if (msg != null && msg.length() > 0 && msg.indexOf(ARGUMENT_SYMBOL) > -1) {
            StringBuilder sb = new StringBuilder();
            boolean isArg = false;
            for (int x = 0; x < msg.length(); x++) {
                char c = msg.charAt(x);
                if (isArg) {
                    isArg = false;
                    if (Character.isDigit(c)) {
                        int val = Character.getNumericValue(c);
                        if (val >= 0 && val < args.length) {
                            try {
								sb.append(urlEncodeURL(args[val]));
							} catch (UnsupportedEncodingException e) {
								throw new ServiceException("Encode error");
							}
                            continue;
                        }
                    }
                    sb.append(ARGUMENT_SYMBOL);
                }
                if (c == ARGUMENT_SYMBOL) {
                    isArg = true;
                    continue;
                }
                sb.append(c);
            }
            
            if (isArg) {
                sb.append(ARGUMENT_SYMBOL);
            }
            return sb.toString();
        }
        return msg;
    }

    
	public static String urlEncodeURL(String url) throws UnsupportedEncodingException {
		String result = URLEncoder.encode(url, DEFAULT_CHARSET_NAME);
		result = result.replaceAll("\\+", "%20");// +实际上是 空格 url
		return result;
	}

    public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
    
}
