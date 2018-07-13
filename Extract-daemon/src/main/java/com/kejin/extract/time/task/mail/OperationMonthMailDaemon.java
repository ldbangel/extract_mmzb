package com.kejin.extract.time.task.mail;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

public class OperationMonthMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;
	
	@Override
	public void execute() {
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.setTime(new Date());
		monthEnd.set(Calendar.DAY_OF_MONTH,1);
		monthEnd.set(monthEnd.get(Calendar.YEAR),
				monthEnd.get(Calendar.MONTH),
				monthEnd.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	
    	Calendar monthBegin = Calendar.getInstance();
    	monthBegin.setTime(monthEnd.getTime());
    	monthBegin.set(Calendar.DATE, monthBegin.get(Calendar.MONTH)-1);
    	
    	Date beginTime = monthBegin.getTime();
    	Date endTime = monthBegin.getTime();
    	
		try {
			mailService.SendMail(MailTypeEnum.OPERATION_MONTH, beginTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送运营周报邮件出错",e);
		}
	}
	
}
