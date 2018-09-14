package com.kejin.extract.time.task.mail;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.domainservice.service.ThreadService;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.entity.kejinTest.DEmployeeModel;
import com.kejin.extract.kejin.process.dao.DEmployeeDao;
import com.kejin.extract.kejin.process.dao.DRealTimeCollectDao;
import com.kejin.extract.time.task.common.DaemonTask;

public class AccountBalanceDayMailDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="mailService")
	private MailService mailService;
	@Resource(name = "threadService")
	private ThreadService threadService;
	@Autowired
	private DEmployeeDao dEmployeeDao;
	
	@Override
	public void execute() {
		//生成账户余额excel
		threadService.exportMemberBalanceExcel2();
		try {
			logger.info("-----账户余额邮件发送开始----");
			List<DEmployeeModel> financialManagers = dEmployeeDao.select();
	    	for(DEmployeeModel model : financialManagers){
	    		if(model.getEmail()!=null && !"".equals(model.getEmail())
	    				&& model.getName()!=null && !"".equals(model.getName())){
	    			mailService.SendBalanceMail(model.getEmail(), model.getName());
	    		}
	    	}
	    	//账户余额总名单邮件发送
	    	mailService.SendMail(MailTypeEnum.ACCOUNT_BALANCE, new Date(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("账户余额邮件出错",e);
		}
	}

}
