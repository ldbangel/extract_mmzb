package com.kejin.extract.domainservice.service;

import java.util.Date;

public interface BusinessReportInfoService {
	
	public String getFinancialManagerBusinessDayInfo(Date begin, Date end);
	
	public String getFinancialManagerBusinessMonthInfo(Date begin, Date end);
	
	public String getAllBusinessInfo(Date begin, Date end);
	
	public String getAccountBalanceInfo();
	
}
