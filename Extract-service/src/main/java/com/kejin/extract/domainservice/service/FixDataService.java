package com.kejin.extract.domainservice.service;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DReInvestAndNewModel;

public interface FixDataService {
	public String actionAssistOutputFail(Date fixBeginTime);
	
	public int fixReinvestAndNewBidFail(Date beginTime, Date outTime);
	
	public int fixRegularInvest(Date beginTime, Date endTime);
	
	public int fixRegularRecovery(Date beginTime, Date endTime);
	
	public int fixReinvestAndNew(List<DReInvestAndNewModel> list);
	
	public int fixCreditPayfee();
	
	public void syncPlatformUserNo();
		
}
