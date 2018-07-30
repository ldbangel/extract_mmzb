package com.kejin.extract.domainservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Map<String, Object> getWechatPushInfoNums() {
		Map<String,Integer> map1 = wechatPushInfoDao.selectWechatPushEachInfoNums1();
		List<Map<String,Object>> listInfo = wechatPushInfoDao.selectWechatPushEachInfoNums2();
		Map<String,Object> result = new HashMap<String, Object>();
		
		result.putAll(map1);
		
		for(Map<String,Object> map : listInfo){
			result.put(map.get("title").toString(), ((Number)map.get("nums")).intValue());
		}
		
		//链接统计
		List<Map<String,Object>> urlLists = accessLogDao.getLogUrlStatistics();
		for(Map<String,Object> map : urlLists){
			result.put(map.get("request_url").toString(), ((Number)map.get("nums")).intValue());
		}
		return result;
	}
}
