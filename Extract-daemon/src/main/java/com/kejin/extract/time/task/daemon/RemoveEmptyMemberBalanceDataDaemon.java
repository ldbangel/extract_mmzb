package com.kejin.extract.time.task.daemon;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.kejin.extract.domainservice.construct.MemberBalanceInfoConstruct;
import com.kejin.extract.time.task.common.DaemonTask;

public class RemoveEmptyMemberBalanceDataDaemon implements DaemonTask {
	
	@Resource(name = "memberBalanceInfoConstruct")
	private MemberBalanceInfoConstruct memberBalanceInfoConstruct;
	
	@Override
	public void execute() {
		Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		Date beginTime = yesterday.getTime();
		
		memberBalanceInfoConstruct.deleteEmptyMemberBalance(beginTime);
	}

}
