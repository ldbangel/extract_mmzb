package com.kejin.extract.domainservice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ActionDetailsInfoService {
	public List<Map<String,Object>> getCashDetails(Date beginTime, Date endTime);
	
	public List<Map<String,Object>> getLargeCashDetails(Date beginTime, Date endTime);
	
	public List<Map<String,Object>> getInvestDetails(Date beginTime, Date endTime);
	
	public List<Map<String,Object>> getChargeDetails(Date beginTime, Date endTime);
	
	public List<Map<String,Object>> getRecoveryDetails(Date beginTime, Date endTime);
	
	public void exportCashDetailsExcel(Date beginTime, Date endTime);
}
