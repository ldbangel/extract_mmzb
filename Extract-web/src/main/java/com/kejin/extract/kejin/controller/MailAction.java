package com.kejin.extract.kejin.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.DocumentException;
import com.kejin.extract.common.enums.MailTypeEnum;
import com.kejin.extract.common.enums.ScreenShotTypeEnum;
import com.kejin.extract.domainservice.common.PdfGeneraterUtils;
import com.kejin.extract.domainservice.common.ScreenShotUtils;
import com.kejin.extract.domainservice.construct.LoanInfoConstruct;
import com.kejin.extract.domainservice.construct.MemberBalanceInfoConstruct;
import com.kejin.extract.domainservice.construct.RegularInvestInfoConstruct;
import com.kejin.extract.domainservice.dingding.AuthHelper;
import com.kejin.extract.domainservice.dingding.Env;
import com.kejin.extract.domainservice.dingding.EventChangeHelper;
import com.kejin.extract.domainservice.dingding.OApiException;
import com.kejin.extract.domainservice.service.ActionDetailsInfoService;
import com.kejin.extract.domainservice.service.BusinessReportInfoService;
import com.kejin.extract.domainservice.service.CustomerInfoService;
import com.kejin.extract.domainservice.service.DingDingInfoService;
import com.kejin.extract.domainservice.service.MemberBalanceInfoService;
import com.kejin.extract.domainservice.service.PartTimeFinancierAchievementService;
import com.kejin.extract.domainservice.service.ThreadService;
import com.kejin.extract.domainservice.service.TradeRealTimeDataService;
import com.kejin.extract.domainservice.service.WechatInfoService;
import com.kejin.extract.domainservice.util.MailService;
import com.kejin.extract.integration.custody.CustodyMemberService;
import com.kejin.extract.kejin.process.dao.DEmployeeDao;
import com.mmzb.custody.shbk.service.request.EnterpriseInfoRequest;
import com.mmzb.custody.shbk.service.request.QueryUserInfoRequest;
import com.mmzb.custody.shbk.service.response.UserInformationResponse;

/**
 * 用来测试定时任务接口
 */
@Controller
public class MailAction {
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name="mailService")
    private MailService mailService;
    @Resource(name = "memberBalanceInfoConstruct")
	private MemberBalanceInfoConstruct memberBalanceInfoConstruct;
    @Resource(name="memberBalanceInfoService")
	private MemberBalanceInfoService memberBalanceInfoService;
    @Resource(name="actionDetailsInfoService")
	private ActionDetailsInfoService actionDetailsInfoService;
    @Resource(name="businessReportInfoService")
   	private BusinessReportInfoService businessReportInfoService;
    @Resource(name="customerInfoService")
   	private CustomerInfoService customerInfoService;
	@Resource(name="loanInfoConstruct")
    private LoanInfoConstruct loanInfoConstruct;
	@Resource(name="regularInvestInfoConstruct")
	private RegularInvestInfoConstruct regularInvestInfoConstruct;
	@Resource(name="tradeRealTimeDataService")
	private TradeRealTimeDataService tradeRealTimeDataService;
	@Resource(name = "partTimeFinancierAchievementService")
	private PartTimeFinancierAchievementService partTimeFinancierAchievementService;
	@Resource(name = "dingDingInfoService")
	private DingDingInfoService dingDingInfoService;
	@Resource(name = "custodyMemberService")
	private CustodyMemberService custodyMemberService;
	@Resource(name = "threadService")
	private ThreadService threadService;
	@Resource(name = "wechatInfoService")
	private WechatInfoService wechatInfoService;
	@Autowired
	private DEmployeeDao dEmployeeDao;
	
	//同步统计个人资产(定期债权)
	@RequestMapping(value = "syncMemberBalance.htm",produces = "text/html; charset=utf-8")
    public void syncMemberBalance(HttpServletRequest req, HttpServletResponse resp, ModelMap model){
		memberBalanceInfoConstruct.memberBalanceConstruct();
	}
	//同步统计个人余额
	@RequestMapping(value = "syncSettleBalance.htm",produces = "text/html; charset=utf-8")
    public void syncSettleBalance(HttpServletRequest req, HttpServletResponse resp, ModelMap model){
		memberBalanceInfoConstruct.settleBalanceUpdate();
	}
    
    //保存个人资产到excel表格中--测试
    @RequestMapping(value = "saveExcelOfMemberBalance.htm",produces = "text/html; charset=utf-8")
    public void SaveExcelOfMemberBalance(HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
    	/*logger.info("--------保存个人资产excel开始--------");
    	memberBalanceInfoService.getMemberBalanceInfo();*/
    	
    	Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		Date endTime = today.getTime();
		logger.info("--------保存提现明细excel--------");
    	actionDetailsInfoService.exportCashDetailsExcel(beginTime, endTime);
    }
    
    //邮件发送--测试
    @RequestMapping(value = "dayMailTest.htm",produces = "text/html; charset=utf-8")
    public void DayMailTest(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
    	Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		Date endTime = today.getTime();
		
    	/*logger.info("--------发送运营日报邮件开始--------");
    	mailService.SendMail(MailTypeEnum.OPERATION_DAY, beginTime, endTime);*/
		
    	/*logger.info("--------发送业绩报表邮件开始--------");
    	mailService.SendMail(MailTypeEnum.ACHIEVEMENT_DAY, beginTime, endTime);*/
    	
    	/*logger.info("--------发送提现明细报表邮件开始--------");
    	mailService.SendMail(MailTypeEnum.ACTION_DETAILS, beginTime, endTime);*/
		
    	/*logger.info("--------发送个人资产报表邮件开始--------");
    	mailService.SendMail(MailTypeEnum.MEMBERBALANCE, beginTime, endTime);*/
		
		logger.info("-------平台交易实时数据邮件发送-------");
		mailService.SendMail(MailTypeEnum.PLATFORM_REALTIME_DATA, new Date(), new Date());
		logger.info("-----账户余额邮件发送开始----");
		mailService.SendMail(MailTypeEnum.ACCOUNT_BALANCE, new Date(), new Date());
		
		/*logger.info("-----客户分配-----");
		customerInfoService.GeneraterDistributeExcel(beginTime, endTime);
		mailService.SendMail(MailTypeEnum.CUSTOMER_DISTRIBUTE, beginTime, endTime);*/
		
		/*logger.info("-----大额报表-----");
		mailService.SendMail(MailTypeEnum.ACTION_DETAILS, beginTime, endTime);*/
		
		/*logger.info("-----运营终端统计日报-----");
		mailService.SendMail(MailTypeEnum.PLATFORM_INFO_DAY, beginTime, endTime);*/
		
		/*logger.info("-----兼职理财师日报-----");
		mailService.SendMail(MailTypeEnum.PARTTIME_FINANCIER_DAY, beginTime, endTime);*/
		
		/*logger.info("-----微信统计数据报表-----");
		mailService.SendMail(MailTypeEnum.WECHAT_STATISTICS_DATA, beginTime, endTime);*/
    }
    
    @RequestMapping(value = "weekMailTest.htm",produces = "text/html; charset=utf-8")
    public void WeekMailTest(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
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
    	
    	logger.info("开始发送业绩周报");
    	mailService.SendMail(MailTypeEnum.ACHIEVEMENT_WEEK, beginTime, endTime);
    	
    	logger.info("开始发送运营周报");
    	mailService.SendMail(MailTypeEnum.OPERATION_WEEK, beginTime, endTime);
    }
    
    
    @RequestMapping(value = "monthMailTest.htm",produces = "text/html; charset=utf-8")
    public void monthMailTest(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
    	Calendar weekEnd = new GregorianCalendar(2018, 02, 01, 00, 10, 00);
    	//Calendar weekEnd = Calendar.getInstance();
    	weekEnd.setTime(new Date());
    	weekEnd.set(Calendar.DAY_OF_MONTH,1);
    	weekEnd.set(weekEnd.get(Calendar.YEAR),
    			weekEnd.get(Calendar.MONTH),
    			weekEnd.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	
    	Calendar weekBegin = Calendar.getInstance();
    	weekBegin.setTime(weekEnd.getTime());
    	weekBegin.set(Calendar.DATE, weekBegin.get(Calendar.MONTH)-1);
    	
    	Date beginTime = weekBegin.getTime();
    	Date endTime = weekEnd.getTime();
    	
    	logger.info("开始发送运营月报");
    	mailService.SendMail(MailTypeEnum.OPERATION_MONTH, beginTime, endTime);
    }
    
    //提现、充值、回款、投资明细
    @RequestMapping(value = "actionDetailsTest.htm",produces = "text/html; charset=utf-8")
    public @ResponseBody String ActionDetailsInfo(HttpServletRequest req, HttpServletResponse resp) {
    	Calendar begin = Calendar.getInstance();
		begin.setTime(new Date());
		begin.set(Calendar.DATE, begin.get(Calendar.DATE)-1);
		begin.set(begin.get(Calendar.YEAR),
				begin.get(Calendar.MONTH),
				begin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(begin.getTime());
		end.set(Calendar.DATE, end.get(Calendar.DATE)+1);
		//测试提现明细
		/*List<Map<String,Object>> listCashInfo = actionDetailsInfoService.getLargeCashDetails(begin.getTime(), end.getTime());
		String data = JSON.toJSONString(listCashInfo);*/
		//测试充值明细
		/*List<Map<String,Object>> listChargeInfo = actionDetailsInfoService.getChargeDetails(begin.getTime(), end.getTime());
		String data = JSON.toJSONString(listChargeInfo);*/
		//测试投资明细
		/*List<Map<String,Object>> listInvestInfo = actionDetailsInfoService.getInvestDetails(begin.getTime(), end.getTime());
		String data = JSON.toJSONString(listInvestInfo);*/
		//测试回款明细
		List<Map<String,Object>> listRecoveryInfo = actionDetailsInfoService.getRecoveryDetails(begin.getTime(), end.getTime());
		String data = JSON.toJSONString(listRecoveryInfo);
		return data;
    }
    
    /**
     * 各种报表需要的截图生成测试
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value = "screenShot.htm",produces = "text/html; charset=utf-8")
    public void ScreenShot() throws IOException, ParseException{
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_ACHIEVEMENT_REPORT);
    	/*ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_INCREASE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_REGULAR_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_TRADE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_FUNDFLOW_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_SIMPLE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_CASH_CHART);*/
    	
    	
    	/*ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_ACHIEVEMENT_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_SIMPLE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_FUNDFLOW_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_INCREASE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_REGULAR_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_TRADE_REPORT);*/
    	
    	/*ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.MONTH_SIMPLE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.MONTH_FUNDFLOW_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.MONTH_INCREASE_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.MONTH_REGULAR_REPORT);
    	ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.MONTH_TRADE_REPORT);*/
    }
    
    /**
     * 运营报表的pdf附件生成测试
     * @throws MalformedURLException
     * @throws DocumentException
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "operationPdfGenerater.htm",produces = "text/html; charset=utf-8")
    public void operationPdfGenerater() 
    		throws MalformedURLException, DocumentException, ParseException, IOException{
    	PdfGeneraterUtils.generaterOperationPdf("day");
    	//PdfGeneraterUtils.generaterOperationPdf("week");
    	//PdfGeneraterUtils.generaterOperationPdf("month");
    }
    
    @RequestMapping(value = "getTradeRealTimeData.htm",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String getTradeRealTimeData() {
    	Map<String,Object> map = tradeRealTimeDataService.getTradeRealTimeData();
    	return JSON.toJSONString(map);
    }
    
    @RequestMapping(value = "getDingDingInfo.htm",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String getDingDingInfo() {
    	dingDingInfoService.SyncDingDing2Kejin();
    	return "success";
    }
    
    @RequestMapping(value = "getCustodyUserinfo.htm",produces = "text/html; charset=utf-8")
    @ResponseBody
    public String getCustodyUserinfo(HttpServletRequest req, HttpServletResponse resp) {
    	String memberId = req.getParameter("memberId");
    	QueryUserInfoRequest request = new QueryUserInfoRequest();
    	request.setMemberId(memberId);
    	request.setUserRole("INVESTOR");
    	List<String> userRoles = new ArrayList<String>();
    	userRoles.add("INVESTOR");
    	userRoles.add("BORROWERS");
    	UserInformationResponse userinfo = custodyMemberService.queryUserInfomation(request);
    	return JSON.toJSONString(userinfo);
    }
    
    @RequestMapping(value = "testDingdingApproval.htm",produces = "text/html; charset=utf-8")
    public String testDingdingApprovalProcess() throws OApiException{
    	System.out.println("testDingdingApproval is coming");
    	List<String> callBackTag = new ArrayList<String>();
		callBackTag.add("bpms_task_change");
		callBackTag.add("bpms_instance_change");
		String url = "http://liudongbo.vaiwan.com/extract/dingdingCallBack.htm";
		EventChangeHelper.registerEventChange(AuthHelper.getAccessToken(), callBackTag, Env.TOKEN, Env.ENCODING_AES_KEY, url);
    	return null;
    }
    
    @RequestMapping(value = "dingdingCallBack.htm",produces = "text/html; charset=utf-8")
    public void dingdingCallBack(@RequestBody String requestBody,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	String signature = request.getParameter("signature");
    	String timestamp = request.getParameter("timestamp");
    	String nonce = request.getParameter("nonce");
    	System.out.println("requestBody:"+requestBody);
    	JSONObject json = JSONObject.parseObject(requestBody);
    	String encrypt = json.getString("encrypt");
    	System.out.println("signature:"+signature+"---timestamp:"+timestamp+"---nonce:"+nonce+"---encrypt:"+encrypt);
    	String result = EventChangeHelper.encryptCallBackInfo(signature, timestamp, nonce, encrypt);
    	response.getWriter().append(result);
    }
    
    @RequestMapping(value = "getWechatPushInfoNums.htm",produces = "text/html; charset=utf-8")
    public String getWechatPushInfoNums(){
    	Map<String,Object> result = wechatInfoService.getWechatPushInfoNums();
    	return null;
    }
    
    //获取企业投资的可用余额
    @RequestMapping(value = "getEnterInvestorInfo.htm",produces = "text/html; charset=utf-8")
    public String getEnterInvestorInfo(){
    	EnterpriseInfoRequest request = new EnterpriseInfoRequest();
    	request.setMemberId("100001280563");
    	request.setUserRole("INVESTOR");
    	UserInformationResponse response = custodyMemberService.queryEnterpriseInfo(request);
    	System.out.println("企业投资人的可用余额为:"+response.getAvailableAmount());
    	return null;
    }
    
  //账户余额邮件发送
    @RequestMapping(value = "accountBalanceMailSend.htm",produces = "text/html; charset=utf-8")
    public String accountBalanceMailSend() throws Exception{
    	mailService.SendMail(MailTypeEnum.PLATFORM_REALTIME_DATA, new Date(), new Date());
    	/*List<DEmployeeModel> financialManagers = dEmployeeDao.select();
    	for(DEmployeeModel model : financialManagers){
    		mailService.SendBalanceMail("liudongbo@88mmmoney.com", model.getName());
    	}
    	mailService.SendMail(MailTypeEnum.ACCOUNT_BALANCE, new Date(), new Date());*/
    	
    	return null;
    }
    
    
}
