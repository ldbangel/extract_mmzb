package com.kejin.extract.time.task.daemon;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.domainservice.service.DingDingInfoService;
import com.kejin.extract.time.task.common.DaemonTask;

public class SyncDingDingAddressListDaemon implements DaemonTask{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "dingDingInfoService")
	private DingDingInfoService dingDingInfoService;

	@Override
	public void execute() {
		logger.info("-------同步钉钉通讯录-------");
		dingDingInfoService.SyncDingDing2Kejin();
	}
	
}
