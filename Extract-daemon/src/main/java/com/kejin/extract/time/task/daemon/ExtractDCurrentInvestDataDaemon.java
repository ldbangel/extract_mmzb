package com.kejin.extract.time.task.daemon;


import java.util.Date;

import javax.annotation.Resource;

import com.kejin.extract.domainservice.construct.CurrentInvestInfoConstruct;
import com.kejin.extract.time.task.common.AbstractExtract;
import com.kejin.extract.time.task.common.DaemonTask;

public class ExtractDCurrentInvestDataDaemon extends AbstractExtract implements DaemonTask {
	
	
	
	
	@Resource(name="currentInvestInfoConstruct")
	private CurrentInvestInfoConstruct currentInvestInfoConstruct;

	
	public String HANDLE_DATA = "d_current_invest";
	
    //定时任务的时间应该比这个多
	public String INTERVAL_TIME = "60";
	
	//至少延后半个小时
	public String AFTER_TIME = "30";
	
	public ExtractDCurrentInvestDataDaemon(){
		super.HANDLE_DATA = this.HANDLE_DATA;
		super.INTERVAL_TIME = this.INTERVAL_TIME;
		super.AFTER_TIME = this.AFTER_TIME;
	}


	@Override
	public int construct(Date recordBeginDatetime, Date recordEndDatetime) {
		// TODO Auto-generated method stub
		return currentInvestInfoConstruct.constructCurrentInvest(recordBeginDatetime, recordEndDatetime);	
		
	}
	
	
	
}
