package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DLatestActionModel;

public interface LatestActionInfoService {

	public abstract List<DLatestActionModel> readLatestInvestInfo(Date beginTime,Date endTime,int continueRead);
	
	public abstract List<DLatestActionModel> readLatestRecoveryInfo(Date beginTime,Date endTime,int continueRead);
	
	public abstract List<DLatestActionModel> readLatestChargeInfo(Date beginTime,Date endTime,int continueRead);
	
	public abstract List<DLatestActionModel> readLatestCashInfo(Date beginTime,Date endTime,int continueRead);
	
}