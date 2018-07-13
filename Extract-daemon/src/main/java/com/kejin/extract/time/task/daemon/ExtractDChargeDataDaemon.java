package com.kejin.extract.time.task.daemon;


import java.util.Date;

import javax.annotation.Resource;

import com.kejin.extract.domainservice.construct.ChargeInfoConstruct;
import com.kejin.extract.time.task.common.AbstractExtract;
import com.kejin.extract.time.task.common.DaemonTask;

public class ExtractDChargeDataDaemon extends AbstractExtract implements DaemonTask {
	@Resource(name="chargeInfoConstruct")
	private ChargeInfoConstruct chargeInfoConstruct;
	
	public String HANDLE_DATA = "d_charge";
	
    //定时任务的时间应该比这个多
	public String INTERVAL_TIME = "60";
	//至少延后半个小时
	public String AFTER_TIME = "30";
	
	public ExtractDChargeDataDaemon(){
		super.HANDLE_DATA = this.HANDLE_DATA;
		super.INTERVAL_TIME = this.INTERVAL_TIME;
		super.AFTER_TIME = this.AFTER_TIME;
	}

	@Override
	public int construct(Date recordBeginDatetime, Date recordEndDatetime) {
		// TODO Auto-generated method stub
		return chargeInfoConstruct.constructCharge(recordBeginDatetime, recordEndDatetime);	
	}
}
