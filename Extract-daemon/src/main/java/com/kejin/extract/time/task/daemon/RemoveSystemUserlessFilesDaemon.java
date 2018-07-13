package com.kejin.extract.time.task.daemon;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.domainservice.service.SystemResourcesService;
import com.kejin.extract.time.task.common.DaemonTask;

public class RemoveSystemUserlessFilesDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "systemResourcesService")
	private SystemResourcesService systemResourcesService;
	
	@Override
	public void execute() {
		logger.info("-------删除生成的无用的图片或者excel等文件-------");
		systemResourcesService.removeUselessFiles();
	}

}
