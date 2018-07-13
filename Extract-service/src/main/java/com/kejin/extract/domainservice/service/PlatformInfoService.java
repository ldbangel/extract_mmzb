package com.kejin.extract.domainservice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PlatformInfoService {
	//获取platform数据(分ios、Android、H5、PC)
	public Map<String,Object> getPlatformInfo(Date beginTime, Date endTime);
	
	//获取growingio前一天数据
	public List<Map<String,Object>> getGrowingioDayInfo();
	
	//获取growingio前30天数据
	public List<Map<String,Object>> getGrowingioMonthInfo();
}
