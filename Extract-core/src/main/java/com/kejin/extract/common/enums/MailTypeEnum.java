package com.kejin.extract.common.enums;

import com.kejin.extract.common.utils.SysConstantsConfig;

public enum MailTypeEnum {
	ACTION_DETAILS("actionDetails", "actionDetails.ftl", "dayCashChart", SysConstantsConfig.ACTION_DETAILS),
	ACHIEVEMENT_DAY("achievementOfDay", "achievementOfDay.ftl", "dayAchievementReport", SysConstantsConfig.ACHIEVEMENT),
	OPERATION_DAY("operationOfDay", "operationOfDay.ftl",
			"daySimpleReport_dayIncreaseReport_dayFundflowReport_dayTradeReport_dayRegularReport", SysConstantsConfig.OPERATION),
	ACHIEVEMENT_WEEK("achievementOfWeek", "achievementOfWeek.ftl", "weekAchievementReport", SysConstantsConfig.ACHIEVEMENT),
	OPERATION_WEEK("operationOfWeek", "operationOfWeek.ftl",
			"weekSimpleReport_weekIncreaseReport_weekFundflowReport_weekTradeReport_weekRegularReport", SysConstantsConfig.OPERATION),
	OPERATION_MONTH("operationOfMonth", "operationOfMonth.ftl",
			"monthSimpleReport_monthIncreaseReport_monthFundflowReport_monthTradeReport_monthRegularReport", SysConstantsConfig.OPERATION),
	//个人资产邮件
	MEMBERBALANCE("memberBalance", "", "", SysConstantsConfig.MEMBER_BALANCE),
	//账户余额邮件
	ACCOUNT_BALANCE("accountBalance", "", "", SysConstantsConfig.ACCOUNT_BALANCE),
	//新注册客户名单分配
	CUSTOMER_DISTRIBUTE("customerDistribute", "", "", SysConstantsConfig.FINANCIAL_DEPARTMENT),
	//运营终端日报
	PLATFORM_INFO_DAY("platformInfoDay", "platformInfoDay.ftl", "", SysConstantsConfig.PLATFORM_INFO),
	//平台交易实时数据日报
	PLATFORM_REALTIME_DATA("platformRealTimeData", "platformRealTimeData.ftl", "", SysConstantsConfig.PLATFORM_REALTIME_DATA),
	//平台交易实时数据日报
	PARTTIME_FINANCIER_DAY("partTimeFinancierDay", "partTimeFinancierDay.ftl", "", SysConstantsConfig.PARTTIME_FINANCIER_DAY);
	
	private String mailType;
	private String templateName;
	private String imageName;
	private String sendUsers;
	
	MailTypeEnum(String mailType, String templateName, String imageName, String sendUsers){
		this.mailType = mailType;
		this.templateName = templateName;
		this.imageName = imageName;
		this.sendUsers = sendUsers;
	}

	public String getMailType() {
		return mailType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public String getImageName() {
		return imageName;
	}

	public String getSendUsers() {
		return sendUsers;
	}
	
}
