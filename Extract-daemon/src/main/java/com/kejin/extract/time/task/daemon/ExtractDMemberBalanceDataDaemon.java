package com.kejin.extract.time.task.daemon;

import javax.annotation.Resource;

import com.kejin.extract.domainservice.construct.MemberBalanceInfoConstruct;
import com.kejin.extract.time.task.common.DaemonTask;

public class ExtractDMemberBalanceDataDaemon implements DaemonTask {
	
	@Resource(name = "memberBalanceInfoConstruct")
	private MemberBalanceInfoConstruct memberBalanceInfoConstruct;

	@Override
	public void execute() {
		memberBalanceInfoConstruct.memberBalanceConstruct();
	}

}
