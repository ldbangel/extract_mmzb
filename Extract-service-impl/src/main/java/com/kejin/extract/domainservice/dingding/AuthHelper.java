package com.kejin.extract.domainservice.dingding;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.kejin.extract.common.utils.HttpUtil;
import com.kejin.extract.common.utils.JsonUtil;

public class AuthHelper {
	public static String getAccessToken(){
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("corpid","ding683a0a936a4cb9cc"));
		params.add(new BasicNameValuePair("corpsecret","h9IlK4JEtdpd9v0PiaZY57zfgodZXPHCVWAxGiP_VKpazbv0yycxDnDW4-hHsjRB"));
		String response = HttpUtil.getResponsebyGet("https://oapi.dingtalk.com/gettoken", params);
		String accessToken = JsonUtil.getVauleFromJson("access_token", response);
		System.out.println(accessToken);
		return accessToken;
	}
	
	public static void main(String[] args) {
		getAccessToken();
	}
}
