package com.kejin.extract.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	/**
	 * 解析JSON 对象为对应的Bean
	 * Liu Dongbo
	 */
	public static Object getObjFromJson(Object object, String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Object Beanobject = jsonObject.getObject(json, object.getClass());
		return Beanobject;
	}
	
	/**
	 * 解析获取json里面对应key的value
	 * Liu Dongbo
	 */
	public static String getVauleFromJson(String key, String json) {
		String value="";
		JSONObject jsonObject = JSONObject.parseObject(json);
		if(jsonObject.containsKey(key)){
			value=jsonObject.getString(key);
		}		 
		return value;
	}
	
	/**
	 * 解析json获取map
	 * @param json
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> jsonToMap(String json){
		return JSONObject.parseObject(json, Map.class);
	}
	
	public static HashMap<String, String> toMap(String jsonStr) {
		ObjectMapper om = new ObjectMapper();
		
	    if ((jsonStr == null) || (jsonStr.trim().length() == 0))
	    	return null;
	    try{
	    	return (HashMap)om.readValue(jsonStr, HashMap.class);
	    } catch (Exception e) {
	    	
	    }return null;
	  }
	
	
	public static void main(String[] args) {
		String json = "{\"plantform\":\"ios\"}";
		Map<String,String> map = jsonToMap(json);
		String str = map.get("plantform");
		System.out.println(str);
	}
	
}
