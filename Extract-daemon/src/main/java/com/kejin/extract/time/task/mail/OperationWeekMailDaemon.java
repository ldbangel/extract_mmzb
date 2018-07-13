package com.kejin.extract.time.task.mail;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

public class OperationWeekMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;
	
	@Override
	public void execute() {
		Calendar weekEnd = Calendar.getInstance();
    	weekEnd.setTime(new Date());
    	weekEnd.set(weekEnd.get(Calendar.YEAR),
    			weekEnd.get(Calendar.MONTH),
    			weekEnd.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	
    	weekEnd.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	
    	Calendar weekBegin = Calendar.getInstance();
    	weekBegin.setTime(weekEnd.getTime());
    	weekBegin.set(Calendar.DATE, weekBegin.get(Calendar.DATE)-7);
    	
    	Date beginTime = weekBegin.getTime();
    	Date endTime = weekEnd.getTime();
		try {
			mailService.SendMail(MailTypeEnum.OPERATION_WEEK, beginTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送运营周报邮件出错",e);
		}
	}
	
}
