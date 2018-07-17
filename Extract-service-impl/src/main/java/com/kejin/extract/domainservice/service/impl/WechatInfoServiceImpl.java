package com.kejin.extract.domainservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.WechatInfoService;
import com.kejin.extract.logdata.service.dao.AccessLogDao;
import com.kejin.extract.mmmoney.service.dao.WechatPushInfoDao;

@Service("wechatInfoService")
public class WechatInfoServiceImpl implements WechatInfoService {
	@Autowired
	private WechatPushInfoDao wechatPushInfoDao;
	@Autowired
	private AccessLogDao accessLogDao;

	@Override
	public List<Map<String, Object>> getWechatPushInfoNums() {
		Map<String,Integer> map1 = wechatPushInfoDao.selectWechatPushEachInfoNums1();
		List<Map<String,Object>> listInfo = wechatPushInfoDao.selectWechatPushEachInfoNums2();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		for(String str : map1.keySet()){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title", str);
			map.put("nums", ((Number)map1.get(str)).intValue());
			resultList.add(map);
		}
		for(Map<String,Object> map : listInfo){
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("title", map.get("title").toString());
			m.put("nums", ((Number)map.get("nums")).intValue());
			resultList.add(m);
		}
		
		//链接统计
		List<Map<String,Object>> urlLists = accessLogDao.getLogUrlStatistics();
		for(Map<String,Object> map : urlLists){
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("title", map.get("request_url").toString());
			m.put("nums", ((Number)map.get("nums")).intValue());
			resultList.add(m);
		}
		System.out.println(JSON.toJSONString(resultList));
		return resultList;
	}
}
