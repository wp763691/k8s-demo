package com.k8s.demo.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: HttpRequestUtil
 * @Description: http通信工具类
 * @author jiangliu.wang
 * @date Oct 10, 2017
 *
 */

public class HttpRequestUtil {
	
	public final static String POST = "POST";
	public final static String GET = "GET";
	public final static String PUT = "PUT";
	public final static String DELETE = "DELETE";
	public final static String PATCH = "PATCH";

	public static final String TRACK_NO = "TrackNo";
	public final static String DEFAULT_CHARSET_NAME = "UTF-8";
	private final static String DEFAULT_CONTENT_TYPE = "application/json";
	
	/**
	 * 默认超时时间5分钟
	 */
	private final static int DEFAULT_TIME_OUT = 300000; 

	private final static int SUCCESS_CODE = 200;


	public static String sendData(String method, String url) throws Exception{
		return sendData(method, url, null, null);
	}
	
	public static String sendData(String method, String url, String data) throws Exception{
		return sendData(method, url, data, null);
	}
	
	public static String sendData(String method, String url, String data, Map<String, String> headers) throws Exception{
		switch (method) {
		case POST :
			return httpPost(url, data, headers);
		case GET :
			return httpGet(url, headers);
		case PUT:
			return httpPut(url, data, headers);
		case DELETE:
			return httpDelete(url, data, headers);
		case PATCH:
			return httpPatch(url, data, headers);
		default:
			throw new Exception("Undefined method:" + method);
		}
	}

	public static JSONObject sendJsonData(String method, String url) throws Exception{
		return sendJsonData(method, url, null, null);
	}
	
	public static JSONObject sendJsonData(String method, String url, JSONObject data) throws Exception{
		return sendJsonData(method, url, data, null);
	}

	public static JSONObject sendJsonData(String method, String url, JSONObject data, Map<String, String> headers) throws Exception{
		String resData = null;
		if(data != null){
			resData = sendData(method, url, data.toString(), headers);
		}else{
			resData = sendData(method, url, null, headers);
		}
		if(resData != null){
			return JSONObject.parseObject(resData);
		}
		return null;
	}
	
	public static String httpPost(String url, String data, Map<String, String> headers) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		setContent(httpPost, data, headers);
		String resData = execute(httpPost);
		return resData;
	}
	
	public static String httpGet(String url, Map<String, String> headers) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		setContent(httpGet, headers);
		String resData = execute(httpGet);
		return resData;
	}
	
	public static HttpEntity httpGet(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		setContent(httpGet, null);
		HttpEntity httpEntity = executeAndReturnInputStream(httpGet);
		return httpEntity;
	}
	

	public static String httpPut(String url, String data, Map<String, String> headers) throws Exception {
		HttpPut httpPut = new HttpPut(url);
		setContent(httpPut, data, headers);
		String resData = execute(httpPut);
		return resData;
	}

	public static String httpPatch(String url, String data, Map<String, String> headers) throws Exception {
		HttpPatch httpPatch = new HttpPatch(url);
		setContent(httpPatch, data, headers);
		String resData = execute(httpPatch);
		return resData;
	}
	
	public static String httpDelete(String url, String data, Map<String, String> headers) throws Exception {
		String resData = null;
		if(data != null){
			HttpEntityEnclosingRequestBase httpDelete = new HttpDeleteWithBody(url);
			setContent(httpDelete, data, headers);
			resData = execute(httpDelete);
		} else{
			HttpDelete httpDelete = new HttpDelete(url);
			setContent(httpDelete, headers);
			resData = execute(httpDelete);
		}
		return resData;
	}
	
	private static void setContent(HttpRequestBase httpEntity, Map<String, String> headers){
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(DEFAULT_TIME_OUT)
				.setConnectionRequestTimeout(DEFAULT_TIME_OUT)
				.setSocketTimeout(DEFAULT_TIME_OUT)
				.build();
		String trackNo = Thread.currentThread().getName();
		if(headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(TRACK_NO, trackNo);
		addHeaders(httpEntity, headers);
		httpEntity.setConfig(requestConfig);
	}
	
	private static void setContent(HttpEntityEnclosingRequestBase httpEntity, String data, Map<String, String> headers){
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(DEFAULT_TIME_OUT)
				.setConnectionRequestTimeout(DEFAULT_TIME_OUT)
				.setSocketTimeout(DEFAULT_TIME_OUT)
				.build();
		String trackNo = Thread.currentThread().getName();
		if(headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(TRACK_NO, trackNo);
		addHeaders(httpEntity, headers);
		httpEntity.setConfig(requestConfig);
		if(data != null){
			StringEntity strEntity = new StringEntity(data, DEFAULT_CHARSET_NAME);
			strEntity.setContentEncoding(DEFAULT_CHARSET_NAME);
			strEntity.setContentType(DEFAULT_CONTENT_TYPE);
			httpEntity.setEntity(strEntity);
		}
	}
	
	private static void addHeaders(HttpRequestBase httpEntity, Map<String, String> headers){
		if(headers != null){
			headers.forEach((k, v) -> httpEntity.addHeader(k, v));
		}
	}
	
	private static HttpEntity executeAndReturnInputStream(HttpRequestBase httpEntity) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpclient.execute(httpEntity);
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != SUCCESS_CODE) {
				throw new Exception("Server response error:" + status.getStatusCode());
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				throw new Exception("The server response is null");
			}
			return entity;
		} catch (IOException e) {
			throw new Exception("Send http request error");
		}
	}
	
	private static String execute(HttpRequestBase httpEntity) throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try (CloseableHttpResponse response = httpclient.execute(httpEntity)) {
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != SUCCESS_CODE) {
				throw new Exception("Server response error:" + status.getStatusCode());
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				throw new Exception("The server response is null");
			}
			String resData = EntityUtils.toString(entity, DEFAULT_CHARSET_NAME);
			EntityUtils.consume(entity);
			return resData;
		} catch (IOException e) {
			throw new Exception("Send http request error");
		}
	}
}
