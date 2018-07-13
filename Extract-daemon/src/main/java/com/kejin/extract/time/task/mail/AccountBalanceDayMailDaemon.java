package com.kejin.extract.time.task.mail;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.service.ThreadService;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

public class AccountBalanceDayMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;
	@Resource(name = "threadService")
	private ThreadService threadService;
	
	@Override
	public void execute() {
		try {
			logger.info("-------平台交易实时数据邮件发送-------");
			mailService.SendMail(MailTypeEnum.PLATFORM_REALTIME_DATA, new Date(), new Date());
			/*	因为实时交易数据也会去调用用户个人信息查询接口，导出Excel，
			 *  这里就写在一起了，然后9点和15点就不起TradeRealTimeDataDayMailDaemon定时任务了
			 */
			logger.info("-----账户余额邮件发送开始----");
			mailService.SendMail(MailTypeEnum.ACCOUNT_BALANCE, new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户余额邮件出错",e);
		}
	}

}
