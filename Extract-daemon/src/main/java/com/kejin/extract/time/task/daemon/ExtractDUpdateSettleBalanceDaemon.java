package com.kejin.extract.time.task.daemon;

import javax.annotation.Resource;

import com.kejin.extract.domainservice.construct.MemberBalanceInfoConstruct;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * 将前一天的账户余额同步到member_balance表中
 *
 */
public class ExtractDUpdateSettleBalanceDaemon implements DaemonTask {
	
	@Resource(name = "memberBalanceInfoConstruct")
	private MemberBalanceInfoConstruct memberBalanceInfoConstruct;

	@Override
	public void execute() {
		memberBalanceInfoConstruct.settleBalanceUpdate();
	}

}
