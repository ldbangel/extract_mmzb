package com.kejin.extract.domainservice.service;

import java.util.Date;

public interface DayDetailInfoService {
	
	public abstract String getDayCashInfoDetail(Date begin,Date end,int page);

	public abstract String getDayCashInfoSummary(Date begin,Date end);	

	public abstract String getDayChargeInfoDetail(Date begin,Date end,int page);

	public abstract String getDayChargeInfoSummay(Date begin,Date end);

	public abstract String getDayInvestInfoDetail(Date begin,Date end,int page);

	public abstract String getDayInvsetInfoSummay(Date begin,Date end);

}