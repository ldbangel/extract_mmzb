package com.kejin.extract.domainservice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CustomerInfoService {
	public Map<String,List<Map<String,Object>>> customerDistribute(Date beginTime, Date endTime);
	
	public void GeneraterDistributeExcel(Date beginTime, Date endTime);
}
