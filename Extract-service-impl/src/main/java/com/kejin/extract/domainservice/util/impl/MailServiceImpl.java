package com.kejin.extract.domainservice.util.impl;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;
import com.kejin.extract.domainservice.common.FreemarkerUtil;
import com.kejin.extract.domainservice.service.ActionDetailsInfoService;
import com.kejin.extract.domainservice.service.BusinessReportInfoService;
import com.kejin.extract.domainservice.service.OperationInfoService;
import com.kejin.extract.domainservice.service.PartTimeFinancierAchievementService;
import com.kejin.extract.domainservice.service.PlatformInfoService;
import com.kejin.extract.domainservice.service.TradeRealTimeDataService;
import com.kejin.extract.domainservice.util.MailService;
import com.sun.mail.util.MailSSLSocketFactory;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("mailService")
public class MailServiceImpl implements MailService {
	
	private String MAIL_HOST = "smtp.exmail.qq.com";
	
	private String MAIL_PORT = "465";
	
	private String MAIL_SMTP_AUTH = "true";
	
	private String MAIL_TRANSPORT_PROTOCOL = "smtp";
	
	private String USER = "mamashuju@88mmmoney.com";
	
	private String PASSWORD = "UZtAaEvA3m6ScgDe";
	
	private String IS_SSL = "true";
	
	@Resource(name = "increasedInfoService")
    private OperationInfoService increasedInfoService;
	
	@Resource(name = "fundFlowInfoService")
    private OperationInfoService fundFlowInfoService;
	
	@Resource(name = "investInfoService")
    private OperationInfoService investInfoService;
	
	@Resource(name = "tradeInfoService")
    private OperationInfoService tradeInfoService;
	
    @Resource(name = "simpleReportInfoService")
    private OperationInfoService simpleReportInfoService;
    
    @Resource(name = "businessReportInfoService")
    private BusinessReportInfoService businessReportInfoService;
    
    @Resource(name = "actionDetailsInfoService")
    private ActionDetailsInfoService actionDetailsInfoService;
    
    @Resource(name = "platformInfoService")
    private PlatformInfoService platformInfoService;
    
    @Resource(name = "tradeRealTimeDataService")
    private TradeRealTimeDataService tradeRealTimeDataService;
    
    @Resource(name = "partTimeFinancierAchievementService")
	private PartTimeFinancierAchievementService partTimeFinancierAchievementService;
    
	public void SendMail(MailTypeEnum mail, Date beginTime, Date endTime) throws Exception{
		Properties prop = new Properties();
		// 开启debug调试，以便在控制台查看
		prop.setProperty("mail.debug", "false");
		// 设置邮件服务器主机名
		prop.setProperty("mail.host", MAIL_HOST);
		prop.setProperty("mail.smtp.port", MAIL_PORT);
		
		// 发送服务器需要身份验证
		prop.setProperty("mail.smtp.auth",MAIL_SMTP_AUTH);
		// 发送邮件协议名称
		prop.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", IS_SSL);
		prop.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session = Session.getInstance(prop);
		// 通过session得到transport对象
		Transport ts = session.getTransport();
		
		BodyPart filebodyPart = generateMsgBody(mail,beginTime,endTime);
		InternetAddress[] internetAddressTo = addressInfo(mail.getSendUsers());
		//zhengyajun@88mmmoney.com,luyongting@88mmmoney.com,
		InternetAddress[] internetAddressCC = addressInfo("liudongbo@88mmmoney.com");
		
		// 创建邮件
		Message message = mssd(session, internetAddressTo , filebodyPart, mail);
		message.setRecipients(Message.RecipientType.CC, internetAddressCC);
		
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect(MAIL_HOST, USER, PASSWORD);
		
		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());
	
		ts.close();
	}
	
	public InternetAddress[] addressInfo(String info) throws AddressException{
		String[] s = info.split(",");
		
		InternetAddress[] address = new InternetAddress[s.length];
		int index = 0;
		for(String i : s){
			address[index]= new InternetAddress(i); 
			index++;
		}
		
		return address;
	}
	
	public  MimeMessage mssd(Session session ,InternetAddress[] receivers,
			BodyPart filebodyPart,MailTypeEnum mail) throws Exception{
		MimeMessage mimeMessage = new MimeMessage(session);  
		mimeMessage.setFrom(new InternetAddress(USER));// 发件人  
		mimeMessage.setRecipients(Message.RecipientType.TO, receivers);
		if("operationOfDay".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】运营日报");  
		}else if("achievementOfDay".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】业绩报表");  
		}else if("actionDetails".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】大额操作报表");  
		}else if("operationOfWeek".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】运营周报"); 
		}else if("operationOfMonth".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】运营月报"); 
		}else if("achievementOfWeek".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】业绩周报"); 
		}else if("memberBalance".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】个人资产");
		}else if("accountBalance".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】账户余额");
		}else if("customerDistribute".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】客户分配");
		}else if("platformInfoDay".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】运营终端日报");
		}else if("platformRealTimeData".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】平台实时交易数据");
		}else if("partTimeFinancierDay".equals(mail.getMailType())){
			mimeMessage.setSubject("【妈妈钱包】兼职理财师业绩日报");
		}
		
		mimeMessage.setSentDate(new Date());// 发送日期
		
		MimeMultipart multipart = new MimeMultipart("mixed");
		if("operationOfDay".equals(mail.getMailType()) 
				|| "operationOfWeek".equals(mail.getMailType())
				|| "operationOfMonth".equals(mail.getMailType())
				|| "achievementOfDay".equals(mail.getMailType())
				|| "achievementOfWeek".equals(mail.getMailType())
				|| "actionDetails".equals(mail.getMailType())
				|| "platformInfoDay".equals(mail.getMailType())
				|| "platformRealTimeData".equals(mail.getMailType())
				|| "partTimeFinancierDay".equals(mail.getMailType())){
			MimeMultipart bodyMultipart = new MimeMultipart("related");// related意味着可以发送html格式的邮件
			bodyMultipart.addBodyPart(filebodyPart);
			BodyPart body = new MimeBodyPart();     //表示正文 
			
			if("operationOfDay".equals(mail.getMailType())
					||"operationOfWeek".equals(mail.getMailType())
					||"operationOfMonth".equals(mail.getMailType())){
				addAttachment(multipart, mail);
			}else if("actionDetails".equals(mail.getMailType())){
				addAttachment(multipart, mail);
			}
			
			/*if("achievementOfDay".equals(mail.getMailType())
					|| "achievementOfWeek".equals(mail.getMailType())){
				MimeBodyPart htmlBodyPart = new MimeBodyPart();
				htmlBodyPart.setContent("<h4>各位客户经理好：<br>"
						+"从2018年3月16日开始，业绩报表全新改版，请注意如下几点：<br>"
						+"1. 定期新增指的是历史净增定期宝投资额；<br>"
						+"2. 定期投资总额指的是定期宝投资金额汇总，包含新增和复投金额；<br>"
						+"3. 年化投资额指的是定期宝投资额折算年化后金额，包含新增和复投金额，例如客户A投资100万6月标，则其年化投资额为50万；<br>"
						+"4. 新增投资客户数指的是通过客户经理邀请码注册进来的投资用户数；<br>"
						+"5. 复投率指的是复投金额占回款金额的比率；<br>"
						+"6. 排名不分先后。</h4>", "text/html;charset=utf-8");
				bodyMultipart.addBodyPart(htmlBodyPart);
			}*/
			//给邮件模板添加内嵌图片
			addImage(bodyMultipart, mail);
			body.setContent(bodyMultipart);
			multipart.addBodyPart(body);
		}else if("memberBalance".equals(mail.getMailType())
				|| "accountBalance".equals(mail.getMailType())
				|| "customerDistribute".equals(mail.getMailType())){
			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			if("memberBalance".equals(mail.getMailType())){
				contentPart.setText("任总，您好：\r\n         附件是截止"+DateFormatUtils.getYesterdayDate1()+"凌晨的个人资产排行榜！");
			}else if("accountBalance".equals(mail.getMailType())){
				contentPart.setText("各位客户经理，大家好：\r\n         附件是截止"+DateFormatUtils.getCurrentHoursDate1()+"投资用户账户余额超过1000元客户的详细信息!\r\n");
			}else if("customerDistribute".equals(mail.getMailType())){
				contentPart.setText("各位客户经理，大家好：\r\n         附件是"+DateFormatUtils.getYesterdayDate1()+"新注册用户分配给一部和二部的详细名单!");
			}
			multipart.addBodyPart(contentPart);
			
			addAttachment(multipart, mail);
		}
		mimeMessage.setContent(multipart); //设置邮件内容对象  
        mimeMessage.saveChanges();
        
        return mimeMessage;
	}
	
	
	/**
	 * 添加附件
	 */
	public void addAttachment(Multipart multipart, MailTypeEnum mail) 
			throws ParseException, MessagingException, UnsupportedEncodingException{
		if("operationOfDay".equals(mail.getMailType()) 
				|| "operationOfWeek".equals(mail.getMailType())
				|| "operationOfMonth".equals(mail.getMailType())){
			/*//附件添加图片
			String[] imagelist = mail.getImageName().split("_");
			for(int i = 1; i < imagelist.length; i++){
				String affix = SysConstantsConfig.IMAGE_OUT_PATH + DateFormatUtils.getYesterdayDate() + imagelist[i] + ".jpg";
				String affixName = imagelist[i] +".jpg";
				FileDataSource source = new FileDataSource(affix);
				BodyPart attach = new MimeBodyPart();
				attach.setDataHandler(new DataHandler(source));
				// 添加附件的标题
				// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
				attach.setFileName(MimeUtility.encodeText(affixName));
				multipart.addBodyPart(attach);
			}*/
			
			//附件添加pdf文件
			String pdfType = "day";
			String affixName = "运营日报" +".pdf";
			if("operationOfWeek".equals(mail.getMailType())){
				pdfType = "week";
				affixName = "运营周报" +".pdf";
			}else if("operationOfMonth".equals(mail.getMailType())){
				pdfType = "month";
				affixName = "运营月报" +".pdf";
			}
			String affix = SysConstantsConfig.PDF_OUT_PATH + DateFormatUtils.getYesterdayDate() + pdfType + "OperationReport.pdf";
			DataSource source = new FileDataSource(affix);
			BodyPart attach = new MimeBodyPart();
			attach.setDataHandler(new DataHandler(source));
			// 添加附件的标题
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
			attach.setFileName(MimeUtility.encodeText(affixName));
			multipart.addBodyPart(attach);
		}else if("actionDetails".equals(mail.getMailType())
				|| "memberBalance".equals(mail.getMailType())
				|| "accountBalance".equals(mail.getMailType())
				|| "customerDistribute".equals(mail.getMailType())){
			String affix = null;
			String affixName = null;
			if("actionDetails".equals(mail.getMailType())){
				affix = SysConstantsConfig.EXCEL_SAVE_PATH + "CashDetails" + DateFormatUtils.getYesterdayDate() + ".xls";
				affixName = DateFormatUtils.getYesterdayDate()+ "提现明细" +".xls";
			}else if("memberBalance".equals(mail.getMailType())){
				affix = SysConstantsConfig.EXCEL_SAVE_PATH + "Operation" + DateFormatUtils.getYesterdayDate() + ".xls";
				affixName = DateFormatUtils.getYesterdayDate()+ "个人资产" +".xls";
			}else if("accountBalance".equals(mail.getMailType())){
				affix = SysConstantsConfig.EXCEL_SAVE_PATH + "AccountBalance" + DateFormatUtils.getCurrentHoursDate() + ".xls";
				affixName = "账户余额" +".xls";
			}else if("customerDistribute".equals(mail.getMailType())){
				affix = SysConstantsConfig.EXCEL_SAVE_PATH + "department" + DateFormatUtils.getYesterdayDate() + ".xls";
				affixName = "客户分配" +".xls";
			}
			DataSource source = new FileDataSource(affix);
			BodyPart attach = new MimeBodyPart();
			attach.setDataHandler(new DataHandler(source));
			// 添加附件的标题
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
			attach.setFileName(MimeUtility.encodeText(affixName));
			multipart.addBodyPart(attach);
		}else if("platformInfoDay".equals(mail.getMailType())){
			
		}
	}
	
	/**
	 * 邮件 中添加图片
	 */
	public void addImage(MimeMultipart mp, MailTypeEnum mail) throws MessagingException, ParseException {
		if(!"".equals(mail.getImageName().trim())){
			String[] imagelist = mail.getImageName().split("_");
			if(imagelist.length >= 1){
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				DataSource fds = new FileDataSource(SysConstantsConfig.IMAGE_OUT_PATH + DateFormatUtils.getYesterdayDate() + imagelist[0] +".jpg");
				messageBodyPart.setDataHandler(new DataHandler(fds));
				messageBodyPart.setContentID("<"+imagelist[0]+">");
				mp.addBodyPart(messageBodyPart);
				/*if(imagelist.length >=2 ){
				if("achievementOfDay".equals(mail.getMailType()) 
						|| "achievementOfWeek".equals(mail.getMailType())){
					for (int i = 1; i < imagelist.length; i++) {
						MimeBodyPart messageBodyPart1 = new MimeBodyPart();
						DataSource fds1 = new FileDataSource(SysConstantsConfig.IMAGE_OUT_PATH + DateFormatUtils.getYesterdayDate() + imagelist[i] +".jpg");
						messageBodyPart1.setDataHandler(new DataHandler(fds1));
						messageBodyPart1.setContentID("<"+imagelist[i]+">");
						mp2.addBodyPart(messageBodyPart1);
					}
				}
			}*/
			}
		}
	}
	
	
	public BodyPart generateMsgBody(MailTypeEnum mail, Date beginTime, Date endTime) throws Exception{
		
		BodyPart filebodyPart = new MimeBodyPart();// 正文
		if("memberBalance".equals(mail.getMailType()) == false){
			Template template = null;
			template = FreemarkerUtil.getTemplate(mail.getTemplateName());
			
			template.setEncoding("utf-8");
			
			String content = null;
			try {
				content = FreeMarkerTemplateUtils.processTemplateIntoString(template, generateData(mail, beginTime, endTime));
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			
			filebodyPart.setDataHandler(new DataHandler(content,
					"text/html;charset=utf-8"));// 网页格式
		}
		
		return filebodyPart;
	}
	
	public Object generateData(MailTypeEnum mail, Date beginTime, Date endTime){
		
		Map<String, Map<String, Object>>  map = new HashMap<String, Map<String, Object>>();
		if("operationOfDay".equals(mail.getMailType()) 
				|| "operationOfWeek".equals(mail.getMailType())
				|| "operationOfMonth".equals(mail.getMailType())){
			//运营报表数据收集
			Map<String, Map<String, Object>> increase = this.getIncreaseInfo(beginTime, endTime);
			
			Map<String, Map<String, Object>> fundFlow = this.getFundFlowInfo(beginTime, endTime);
			
			Map<String, Map<String, Object>> invest = this.getInvestInfo(beginTime, endTime);
			
			Map<String, Map<String, Object>> trade = this.getTradeInfo(beginTime, endTime);
			
			Map<String, Map<String, Object>> simple = this.getSimpleReport(beginTime, endTime);
			
			map.putAll(increase);
			map.putAll(fundFlow);
			map.putAll(invest);
			map.putAll(trade);
			map.putAll(simple);
			return map;		
		}else if("achievementOfDay".equals(mail.getMailType()) 
				|| "achievementOfWeek".equals(mail.getMailType())){
			//业绩报表数据收集
			Map<String, List<Map<String, Object>>> achievement = this.getAchievementReport(beginTime, endTime);
			return achievement;
		}else if("actionDetails".equals(mail.getMailType())){
			//收集提现、充值、投资、回款明细数据
			Map<String, Object> actionDetails = this.getActionDetails(beginTime, endTime);
			return actionDetails;
		}else if("platformInfoDay".equals(mail.getMailType())){
			Map<String, Object> platformData = this.getPlatformInfo(beginTime, endTime);
			return platformData;
		}else if("platformRealTimeData".equals(mail.getMailType())){
			Map<String, Object> platformRealTimeData = tradeRealTimeDataService.getTradeRealTimeData();
			return platformRealTimeData;
		}else if("partTimeFinancierDay".equals(mail.getMailType())){
			Map<String, Object> partTimeFinancierData = this.getPartTimeFinancierAchievementInfo(beginTime, endTime);
			return partTimeFinancierData;
		}
		return null;
	}
	
	
	public Map<String, Map<String, Object>> getIncreaseInfo(Date beginTime ,Date endTime ){		
		//获取作日的数据
		String yesterdayData = increasedInfoService.getInfoOfDay(beginTime, null);
		
		//获取本周的数据
		String weekData = increasedInfoService.getInfoOfWeek(beginTime);
		
		//获取本月的数据
		String monthData = increasedInfoService.getInfoOfMonth(beginTime);
		
		//获取本年的数据
		String yearData = increasedInfoService.getInfoOfYear(beginTime);
		
		//获取全部的数据
		String allData = increasedInfoService.getInfoOfAll(null);
	 
	    JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("increase_day", j2t.get("day"));
	    
	    JSONObject j3t = JSON.parseObject(weekData);
	    JSONObject j3 = new JSONObject();
	    j3.put("increase_week", j3t.get("week"));
	    
	    JSONObject j4t = JSON.parseObject(monthData);
	    JSONObject j4 = new JSONObject();
	    j4.put("increase_month", j4t.get("month"));
	    
	    JSONObject j5t = JSON.parseObject(yearData);
	    JSONObject j5 = new JSONObject();
	    j5.put("increase_year", j5t.get("year"));
	    
	    JSONObject j6t = JSON.parseObject(allData);
	    JSONObject j6 = new JSONObject();
	    j6.put("increase_all", j6t.get("all"));

	    JSONObject jsonAll= new JSONObject();  
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    jsonAll.putAll(j4);
	    jsonAll.putAll(j5);
	    jsonAll.putAll(j6);

		 Map<String, Map<String, Object>> map = JSON.parseObject(
				 jsonAll.toJSONString(),new TypeReference<Map<String, Map<String,Object>>>(){} );
		 
		 return map;
		
	}
	
	public Map<String, Map<String, Object>> getFundFlowInfo(Date beginTime ,Date endTime ){		
		//获取作日的数据
		String yesterdayData = fundFlowInfoService.getInfoOfDay(beginTime, null);
		
		//获取本周的数据
		String weekData = fundFlowInfoService.getInfoOfWeek(beginTime);
		
		//获取本月的数据
		String monthData = fundFlowInfoService.getInfoOfMonth(beginTime);
		
		//获取本年的数据
		String yearData = fundFlowInfoService.getInfoOfYear(beginTime);
		
		//获取全部的数据
		String allData = fundFlowInfoService.getInfoOfAll(null);
	 
	    JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("fundflow_day", j2t.get("day"));
	    
	    JSONObject j3t = JSON.parseObject(weekData);
	    JSONObject j3 = new JSONObject();
	    j3.put("fundflow_week", j3t.get("week"));
	    
	    JSONObject j4t = JSON.parseObject(monthData);
	    JSONObject j4 = new JSONObject();
	    j4.put("fundflow_month", j4t.get("month"));
	    
	    JSONObject j5t = JSON.parseObject(yearData);
	    JSONObject j5 = new JSONObject();
	    j5.put("fundflow_year", j5t.get("year"));
	    
	    JSONObject j6t = JSON.parseObject(allData);
	    JSONObject j6 = new JSONObject();
	    j6.put("fundflow_all", j6t.get("all"));

	    JSONObject jsonAll= new JSONObject();  
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    jsonAll.putAll(j4);
	    jsonAll.putAll(j5);
	    jsonAll.putAll(j6);

		 Map<String, Map<String, Object>> map = JSON.parseObject(
				 jsonAll.toJSONString(),new TypeReference<Map<String, Map<String,Object>>>(){} );
		 
		 return map;
		
	}
	
	public Map<String, Map<String, Object>> getInvestInfo(Date beginTime ,Date endTime ){		
		//获取作日的数据
		String yesterdayData = investInfoService.getInfoOfDay(beginTime, null);
		
		//获取本周的数据
		String weekData = investInfoService.getInfoOfWeek(beginTime);
		
		//获取本月的数据
		String monthData = investInfoService.getInfoOfMonth(beginTime);
		
		//获取本年的数据
		String yearData = investInfoService.getInfoOfYear(beginTime);
		
		//获取全部的数据
		String allData = investInfoService.getInfoOfAll(null);
	 
	    JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("invest_day", j2t.get("day"));
	    
	    JSONObject j3t = JSON.parseObject(weekData);
	    JSONObject j3 = new JSONObject();
	    j3.put("invest_week", j3t.get("week"));
	    
	    JSONObject j4t = JSON.parseObject(monthData);
	    JSONObject j4 = new JSONObject();
	    j4.put("invest_month", j4t.get("month"));
	    
	    JSONObject j5t = JSON.parseObject(yearData);
	    JSONObject j5 = new JSONObject();
	    j5.put("invest_year", j5t.get("year"));
	    
	    JSONObject j6t = JSON.parseObject(allData);
	    JSONObject j6 = new JSONObject();
	    j6.put("invest_all", j6t.get("all"));

	    JSONObject jsonAll= new JSONObject();  
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    jsonAll.putAll(j4);
	    jsonAll.putAll(j5);
	    jsonAll.putAll(j6);

		 Map<String, Map<String, Object>> map = JSON.parseObject(
				 jsonAll.toJSONString(),new TypeReference<Map<String, Map<String,Object>>>(){} );
		 
		 return map;
		
	}
	
	
	public Map<String, Map<String, Object>> getTradeInfo(Date beginTime ,Date endTime ){		
		//获取作日的数据
		String yesterdayData = tradeInfoService.getInfoOfDay(beginTime, null);
		
		//获取本周的数据
		String weekData = tradeInfoService.getInfoOfWeek(beginTime);
		
		//获取本月的数据
		String monthData = tradeInfoService.getInfoOfMonth(beginTime);
		
		//获取本年的数据
		String yearData = tradeInfoService.getInfoOfYear(beginTime);
		
		//获取全部的数据
		String allData = tradeInfoService.getInfoOfAll(null);
	 
	    JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("trade_day", j2t.get("day"));
	    
	    JSONObject j3t = JSON.parseObject(weekData);
	    JSONObject j3 = new JSONObject();
	    j3.put("trade_week", j3t.get("week"));
	    
	    JSONObject j4t = JSON.parseObject(monthData);
	    JSONObject j4 = new JSONObject();
	    j4.put("trade_month", j4t.get("month"));
	    
	    JSONObject j5t = JSON.parseObject(yearData);
	    JSONObject j5 = new JSONObject();
	    j5.put("trade_year", j5t.get("year"));
	    
	    JSONObject j6t = JSON.parseObject(allData);
	    JSONObject j6 = new JSONObject();
	    j6.put("trade_all", j6t.get("all"));

	    JSONObject jsonAll= new JSONObject();  
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    jsonAll.putAll(j4);
	    jsonAll.putAll(j5);
	    jsonAll.putAll(j6);

		Map<String, Map<String, Object>> map = JSON.parseObject(
				jsonAll.toJSONString(),new TypeReference<Map<String, Map<String,Object>>>(){} );
		 
		return map;
	}
	
	public Map<String, Map<String, Object>> getSimpleReport(Date beginTime ,Date endTime){
		JSONObject j = new JSONObject();
		long dayNum = (endTime.getTime()-beginTime.getTime())/(3600*24*1000);
		if(dayNum == 1){
			//获取昨日运营简报数据
			String yesterdayData = simpleReportInfoService.getInfoOfDay(beginTime, null);
			JSONObject jt = JSON.parseObject(yesterdayData);
			j.put("simple_day", jt.get("day"));
		}else if(dayNum == 7){
			//获取上周运营简报数据
			String weekData = simpleReportInfoService.getInfoOfWeek(beginTime);
			JSONObject jt = JSON.parseObject(weekData);
			j.put("simple_week", jt.get("week"));
		}
	    Map<String, Map<String, Object>> map = JSON.parseObject(j.toJSONString(),
	    		new TypeReference<Map<String, Map<String,Object>>>(){} );
		return map;
	}
	
	public Map<String, List<Map<String, Object>>> getAchievementReport(Date beginTime ,Date endTime){
		//获取每个业务经理昨日业绩数据
		String yesterdayData = businessReportInfoService.getFinancialManagerBusinessDayInfo(beginTime, endTime);
		//获取每个业务经理本月业绩
		String monthData = businessReportInfoService.getFinancialManagerBusinessMonthInfo(beginTime, null);
		
		JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("achievement_day", j2t.get("managerBalanceDay"));
	    
	    JSONObject j3t = JSON.parseObject(monthData);
	    JSONObject j3 = new JSONObject();
	    j3.put("achievement_month", j3t.get("managerBalanceMonth"));
	    
	    JSONObject jsonAll= new JSONObject();  
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    
	    Map<String, List<Map<String, Object>>> map = JSON.parseObject(
				 jsonAll.toJSONString(),new TypeReference<Map<String, List<Map<String, Object>>>>(){} );
		return map;
	}
	
	public Map<String, Object> getActionDetails(Date beginTime ,Date endTime){
		List<Map<String, Object>> cashList = actionDetailsInfoService.getLargeCashDetails(beginTime, endTime);
		List<Map<String, Object>> chargeList = actionDetailsInfoService.getChargeDetails(beginTime, endTime);
		List<Map<String, Object>> investList = actionDetailsInfoService.getInvestDetails(beginTime, endTime);
		List<Map<String, Object>> recoveryList = actionDetailsInfoService.getRecoveryDetails(beginTime, endTime);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cashDetails", cashList);
		map.put("chargeDetails", chargeList);
		map.put("investDetails", investList);
		map.put("recoveryDetails", recoveryList);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(beginTime);
		ymd = ymd.replaceFirst("-","年");
		ymd = ymd.replaceFirst("-","月");
		ymd = ymd+"日";
		map.put("titleDate", ymd);
		return map;
	}
	
	public Map<String,Object> getPlatformInfo(Date beginTime ,Date endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = platformInfoService.getPlatformInfo(beginTime, endTime);
		List<Map<String, Object>> listDay = platformInfoService.getGrowingioDayInfo();
		List<Map<String, Object>> listMonth = platformInfoService.getGrowingioMonthInfo();
		map.putAll(map1);
		map.put("growingioDayData", listDay);
		map.put("growingioMonthData", listMonth);
		
		return map;
	}
	
	public Map<String,Object> getPartTimeFinancierAchievementInfo(Date beginTime ,Date endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listMapDay = partTimeFinancierAchievementService.getPartTimeFinancierAchievementDayData(beginTime, endTime);
		
		Calendar thisMonthBegin = Calendar.getInstance();
		thisMonthBegin.setTime(new Date());
		thisMonthBegin.set(Calendar.DAY_OF_MONTH,1);
		
		thisMonthBegin.set(thisMonthBegin.get(Calendar.YEAR),
				thisMonthBegin.get(Calendar.MONTH),
				thisMonthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar thisMonthEnd = Calendar.getInstance();
		thisMonthEnd.setTime(thisMonthBegin.getTime());
		thisMonthEnd.set(Calendar.MONTH, thisMonthEnd.get(Calendar.MONTH) + 1);
		thisMonthEnd.set(Calendar.DATE, thisMonthEnd.get(Calendar.DATE)-1);
		thisMonthEnd.set(thisMonthEnd.get(Calendar.YEAR),
				thisMonthEnd.get(Calendar.MONTH),
				thisMonthEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		List<Map<String, Object>> listMapMonth = partTimeFinancierAchievementService.getPartTimeFinancierAchievementDayData(thisMonthBegin.getTime(), thisMonthEnd.getTime());
		map.put("partTimeFinancierListDay", listMapDay);
		map.put("partTimeFinancierListMonth", listMapMonth);

		return map;
	}

}
