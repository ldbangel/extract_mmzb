package com.kejin.extract.time.task.mail;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

public class TradeRealTimeDataDayMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;

	@Override
	public void execute() {
		try {
			logger.info("-------平台交易实时数据邮件发送-------");
			mailService.SendMail(MailTypeEnum.PLATFORM_REALTIME_DATA, new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
