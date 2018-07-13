package com.kejin.extract.common.utils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
	private static final Logger logger = Logger.getLogger(HttpUtil.class);
	
	public static String get(String url){
		Request request = new Request.Builder()
		    .url(url)
		    .header("corpid", "ding683a0a936a4cb9cc")
		    .header("corpsecret", "h9IlK4JEtdpd9v0PiaZY57zfgodZXPHCVWAxGiP_VKpazbv0yycxDnDW4-hHsjRB")
		    .build();
		return exec(request);
	}
	
	private static String exec(Request request) {
		try {
			Response response = new OkHttpClient().newCall(request).execute();
			if (!response.isSuccessful())
				throw new RuntimeException("Unexpected code " + response);
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static String getResponsebyGet(String url, List<NameValuePair> params) {
		logger.info("开始执行HttpClient get 请求，请求URL为： "+url);
		String result = "";
		HttpClient httpClient = new DefaultHttpClient();
		// 连接时间
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		// 数据传输时间
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				2000);
		// Get请求
		HttpGet httpget = new HttpGet(url);
		try {
			// 设置参数
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params,
					"UTF-8"));
			httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(httpget);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("执行HttpClient get 请求报错，statusCode为 ： "+statusCode);
			}
			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();
			String body = EntityUtils.toString(entity, "utf-8");
			result = body;
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		//logger.info("结束执行HttpClient get 请求，返回结果为： "+result);
		return result;
	}
	
	public static void main(String[] args) {
		/*String str = get("https://oapi.dingtalk.com/gettoken");
		System.out.println(str);*/
		
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corpid","ding683a0a936a4cb9cc"));
		params.add(new BasicNameValuePair("corpsecret","h9IlK4JEtdpd9v0PiaZY57zfgodZXPHCVWAxGiP_VKpazbv0yycxDnDW4-hHsjRB"));
		String str = getResponsebyGet("https://oapi.dingtalk.com/gettoken", params);
		System.out.println(str);
	}
}
