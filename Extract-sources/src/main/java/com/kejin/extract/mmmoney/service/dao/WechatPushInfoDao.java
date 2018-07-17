package com.kejin.extract.mmmoney.service.dao;

import java.util.List;
import java.util.Map;

public interface WechatPushInfoDao {
	public Map<String,Integer> selectWechatPushEachInfoNums1();
	
	public List<Map<String,Object>> selectWechatPushEachInfoNums2();
}
