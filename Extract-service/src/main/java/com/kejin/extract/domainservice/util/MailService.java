package com.kejin.extract.domainservice.util;

import java.util.Date;

import com.kejin.extract.common.enums.MailTypeEnum;

public interface MailService {
	
	public void SendMail(MailTypeEnum mail,Date beginTime,Date endTime) throws Exception;
	
	public void SendBalanceMail(String mailAddressTo, String financialManager) throws Exception;
	
}
