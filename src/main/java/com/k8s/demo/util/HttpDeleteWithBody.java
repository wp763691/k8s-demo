package com.k8s.demo.util;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import com.amazonaws.annotation.NotThreadSafe;

/**
 * @ClassName: HttpDeleteWithBody
 * @Description: 带body的delete方法
 * @author jiangliu.wang
 * @date 2017年10月19日
 *
 */
@NotThreadSafe
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {

	public static final String METHOD_NAME = "DELETE";

	@Override
	public String getMethod() {
		return METHOD_NAME;
	}

	public HttpDeleteWithBody(final String uri) {
		super();
		setURI(URI.create(uri));
	}

	public HttpDeleteWithBody(final URI uri) {
		super();
		setURI(uri);
	}

	public HttpDeleteWithBody() {
		super();
	}
}
