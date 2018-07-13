package com.kejin.extract.domainservice.growingio;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.common.utils.HttpsRequestUtil;
import com.kejin.extract.domainservice.growingio.ChartBean.Meta;
import com.kejin.extract.domainservice.service.BusinessReportInfoService;
import com.kejin.extract.domainservice.util.MailService;

public class GrowingIODataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String secret = "162d3dffc9514040b71870cbb27f6556";
	private static String project = "q9ABZbRW";
	private static String ai = "a8a8791119e0a6e3";
	private static String X_Client_Id = "f1bb79aa000d4b638c7cda3f80aa1210";
	private static String CHART_LOGIN = "xRx0qvjR";
	private static String CHART_UV = "39l6GD3P";
//	private static String CHART_UV = "xogxGW49";

	private static int DAY_MS = 1000*60*60*24;
	
	@Resource(name="mailService")
	private MailService mailService;
	
	@Resource(name="businessReportInfoService")
   	private BusinessReportInfoService businessReportInfoService;
	
	public static String authToken() {
		try{
			long tm = new Date().getTime();
			String message = "POST\n/auth/token\nproject=" + project + "&ai=" + ai + "&tm=" + tm;
			Mac hmac = Mac.getInstance("HmacSHA256");
			hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
			byte[] signature = hmac.doFinal(message.getBytes("UTF-8"));
			String  authToken = new String(Hex.encode(signature));
			
			
			Map<String,String> params = new HashMap<String,String>();
			params.put("ai", ai);
			params.put("project", project);
			params.put("tm", tm+"");
			params.put("auth", authToken);
			
			Map<String,String> headers = new HashMap<String,String>();
			headers.put("X-Client-Id", X_Client_Id);
					
	        String s= HttpsRequestUtil.sendHtpps("https://www.growingio.com/auth/token","POST",headers,params);  
	        AuthBean authBean = JSON.parseObject(s, AuthBean.class);
			return authBean.getCode();
		}catch(Exception e){
			return "";
		}
	}


	public static ChartBean getChart(String authCode,String chartID,long day){
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("X-Client-Id", X_Client_Id);
		headers.put("Authorization", authCode);
		
		
		Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        long endTime = cal.getTimeInMillis(); 
		long startTime = endTime-day*DAY_MS;
		long interval = day*DAY_MS;
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("startTime", startTime + "");
		params.put("endTime", endTime +"");
		params.put("interval", interval+"");

		String s2= HttpsRequestUtil.sendHtpps("https://www.growingio.com/projects/"+project+"/charts/"+chartID+".json","GET",headers,params);  
        ChartBean cb = JSON.parseObject(s2, ChartBean.class);
        return cb;
	}
	
	public static void main(String[] args){  

		ChartBean chartBean = getChart(authToken(),CHART_UV,1);
		System.out.println(JSON.toJSON(chartBean));
		
		StringBuffer sb = new StringBuffer(); 
		for(Meta mata:chartBean.getMeta()){
			sb.append(mata.getName());
			sb.append("\t");
		}
		System.out.println(sb.toString());
		
		for(List<String> data:chartBean.getData()){
			StringBuffer sb2 = new StringBuffer(); 
			sb2.append(data.get(0));
			sb2.append("\t");
			sb2.append(data.get(1));
			sb2.append("\t");
			sb2.append(data.get(2));
			sb2.append("\t");
//			sb2.append(data.get(3));
			System.out.println(sb2.toString());
		}
    }  
	
	
	
	
}
