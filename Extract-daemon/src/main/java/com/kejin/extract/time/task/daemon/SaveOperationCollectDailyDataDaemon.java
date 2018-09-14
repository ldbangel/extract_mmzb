package com.kejin.extract.time.task.daemon;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.domainservice.service.SaveStatisticsDataService;
import com.kejin.extract.time.task.common.DaemonTask;

public class SaveOperationCollectDailyDataDaemon implements DaemonTask{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "saveStatisticsDataService")
	private SaveStatisticsDataService saveStatisticsDataService;

	@Override
	public void execute() {
		logger.info("-------同步钉钉通讯录-------");
		saveStatisticsDataService.saveYesterdayOperationInfo();
	}
	
}
