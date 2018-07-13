package com.kejin.extract.time.task.daemon;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import com.kejin.extract.domainservice.service.ActionDetailsInfoService;
import com.kejin.extract.domainservice.service.MemberBalanceInfoService;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * 保存个人资产excel表格
 * @author chenxiaojian
 *
 */
public class DayMemberBalanceDaemon implements DaemonTask {
	@Resource(name="memberBalanceInfoService")
	private MemberBalanceInfoService memberBalanceInfoService;
	
	@Resource(name = "actionDetailsInfoService")
	private ActionDetailsInfoService actionDetailsInfoService;

	@Override
	public void execute() {
		//保存个人资产excel 
		memberBalanceInfoService.getMemberBalanceInfo();
		
		Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		Date endTime = today.getTime();
		//导出昨日提现明细报表
		actionDetailsInfoService.exportCashDetailsExcel(beginTime, endTime);
	}
	
}
