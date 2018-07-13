package com.kejin.extract.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class HttpsRequestUtil {
	
	private final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
	
	private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendHtpps(String url,String method,Map<String,String> headers,Map<String,String> params) {
        String result = "";
        BufferedReader in = null;
        OutputStreamWriter out = null;
        HttpURLConnection conn;
        try {
            trustAllHosts();
            StringBuffer paramsSb = new StringBuffer();
            if(params != null){
            	for(Map.Entry<String,String> entry : params.entrySet()){
            		paramsSb.append(entry.getKey());
            		paramsSb.append("=");
            		paramsSb.append(entry.getValue());
            		paramsSb.append("&");
            	}
            }
            URL realUrl = null;
            if("GET".equals(method)){
            	realUrl = new URL(url+"?"+paramsSb.toString());
            }else{
            	realUrl = new URL(url);
            }
            //通过请求地址判断请求类型(http或者是https)
            if (realUrl.getProtocol().toLowerCase().equals("https")) {
                HttpsURLConnection https = (HttpsURLConnection) realUrl.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                conn = https;
            } else {
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            conn.setRequestProperty("Content-Type", "text/plain;charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if(headers != null){
            	for(Map.Entry<String,String> entry : headers.entrySet()){
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
            	}
            }
            if("POST".equals(method)){
                out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                // 发送请求params参数
                out.write(paramsSb.toString());
                out.flush();
            }
            
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
            try {
            	if (out != null) {
            		out.close();
            		out = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
	
	
    private static class DefaultTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
