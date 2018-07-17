package com.kejin.extract.time.task.mail;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

public class WechatStatisticsMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;

	@Override
	public void execute() {
		try {
			logger.info("-------钱包微信推送数据邮件发送-------");
			mailService.SendMail(MailTypeEnum.WECHAT_STATISTICS_DATA, new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
