package com.kejin.extract.time.task.mail;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.service.CustomerInfoService;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

public class CustomerDistributeDayMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;
	
	@Resource(name="customerInfoService")
   	private CustomerInfoService customerInfoService;
	
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
		Date endTime = today.getTime();
		customerInfoService.GeneraterDistributeExcel(beginTime, endTime);
		try {
			logger.info("-----客户分配邮件发送开始----");
			mailService.SendMail(MailTypeEnum.CUSTOMER_DISTRIBUTE, new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("客户分配邮件出错",e);
		}
	}

}
