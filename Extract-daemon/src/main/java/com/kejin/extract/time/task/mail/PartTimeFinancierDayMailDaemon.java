package com.kejin.extract.time.task.mail;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * @Author Leo
 */
public class PartTimeFinancierDayMailDaemon implements DaemonTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="mailService")
    private MailService mailService;

    @Override
    public void execute() {
        logger.info("-------兼职理财师业绩报表日报数据邮件发送-------");
        Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		Date endTime = today.getTime();
        try {
            mailService.SendMail(MailTypeEnum.PARTTIME_FINANCIER_DAY, beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
