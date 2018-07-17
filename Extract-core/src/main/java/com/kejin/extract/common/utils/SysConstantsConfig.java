package com.kejin.extract.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SysConstantsConfig {
	private static Logger logger = Logger.getLogger(SysConstantsConfig.class);
	
	public static Properties Pro_Sys_FilePath_Config = new Properties();
	
	public static String EXCEL_SAVE_PATH;
	
	//Email
	public static String MAIL_HOST;
	public static String MAIL_PORT;
	public static String MAIL_TRANSPORT_PROTOCOL;
	public static String USER;
	public static String PASSWORD;
	public static String SENDS;
	public static String MAIL_SMTP_AUTH;
	
	//phantomJS screen shot
	public static String PHANTOMJS_PATH;
	public static String SCRIPT_PATH;
	public static String TARGET_URL;
	public static String IMAGE_OUT_PATH;
	public static String PDF_OUT_PATH;
	
	//email send to users
	public static String ACTION_DETAILS;
	public static String ACHIEVEMENT;
	public static String OPERATION;
	public static String MEMBER_BALANCE;
	public static String ACCOUNT_BALANCE;
	public static String FINANCIAL_DEPARTMENT;
	public static String PLATFORM_INFO;
	public static String PLATFORM_REALTIME_DATA;
	public static String PARTTIME_FINANCIER_DAY;
	public static String WECHAT_STATISTICS_DATA;
	
	 
	
	static {
		loadConfigFiles();
	}
	
	public static void reloadConfigs() {
		loadConfigFiles();
	}
	
	private static void loadConfigFiles() {
		Class clazz = SysConstantsConfig.class;
		String fileName = "";
		try {
			fileName = "/properties/config.properties";
			Pro_Sys_FilePath_Config.load(clazz.getResourceAsStream(fileName));
		} catch (IOException e) {
			logger.error("加载配置文件失败。文件名是：" + fileName, e);
		}
		initConfig();
	}
	
	
	private static void initConfig() {
		EXCEL_SAVE_PATH = Pro_Sys_FilePath_Config.getProperty("EXCEL_SAVE_PATH");
		MAIL_HOST = Pro_Sys_FilePath_Config.getProperty("MAIL_HOST");
		MAIL_PORT = Pro_Sys_FilePath_Config.getProperty("MAIL_PORT");
		MAIL_TRANSPORT_PROTOCOL = Pro_Sys_FilePath_Config.getProperty("MAIL_TRANSPORT_PROTOCOL");
		USER = Pro_Sys_FilePath_Config.getProperty("USER");
		PASSWORD = Pro_Sys_FilePath_Config.getProperty("PASSWORD");
		SENDS = Pro_Sys_FilePath_Config.getProperty("SENDS");
		PHANTOMJS_PATH = Pro_Sys_FilePath_Config.getProperty("PHANTOMJS_PATH");
		SCRIPT_PATH = Pro_Sys_FilePath_Config.getProperty("SCRIPT_PATH");
		TARGET_URL = Pro_Sys_FilePath_Config.getProperty("TARGET_URL");
		IMAGE_OUT_PATH = Pro_Sys_FilePath_Config.getProperty("IMAGE_OUT_PATH");
		PDF_OUT_PATH = Pro_Sys_FilePath_Config.getProperty("PDF_OUT_PATH");
		
		ACTION_DETAILS = Pro_Sys_FilePath_Config.getProperty("ACTION_DETAILS");
		ACHIEVEMENT = Pro_Sys_FilePath_Config.getProperty("ACHIEVEMENT");
		OPERATION = Pro_Sys_FilePath_Config.getProperty("OPERATION");
		MEMBER_BALANCE = Pro_Sys_FilePath_Config.getProperty("MEMBER_BALANCE");
		ACCOUNT_BALANCE = Pro_Sys_FilePath_Config.getProperty("ACCOUNT_BALANCE");
		FINANCIAL_DEPARTMENT = Pro_Sys_FilePath_Config.getProperty("FINANCIAL_DEPARTMENT");
		PLATFORM_INFO = Pro_Sys_FilePath_Config.getProperty("PLATFORM_INFO");
		PLATFORM_REALTIME_DATA = Pro_Sys_FilePath_Config.getProperty("PLATFORM_REALTIME_DATA");
		PARTTIME_FINANCIER_DAY = Pro_Sys_FilePath_Config.getProperty("PARTTIME_FINANCIER_DAY");
		WECHAT_STATISTICS_DATA = Pro_Sys_FilePath_Config.getProperty("WECHAT_STATISTICS_DATA");
	}
}
