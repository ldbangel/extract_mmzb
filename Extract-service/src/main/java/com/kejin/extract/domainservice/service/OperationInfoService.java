package com.kejin.extract.domainservice.service;

import java.util.Date;

public interface OperationInfoService {

	public abstract String getInfoOfDay(Date begin, Date end);

	public abstract String getInfoOfWeek(Date originTime);

	public abstract String getInfoOfMonth(Date originTime);

	public abstract String getInfoOfYear(Date originTime);

	public abstract String getInfoOfAll(Date originTime);

	public abstract String getInfoOfDayDetail(Date begin, Date end);
	
	public abstract String getInfoOfWeekDetail(Date begin, Date end);

	public abstract String getInfoOfMonthDetail(Date originTime);

}