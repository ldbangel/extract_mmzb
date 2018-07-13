package com.kejin.extract.kejin.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kejin.extract.domainservice.service.BusinessReportInfoService;

/**
 * 业绩报表接口
 * @author liudongbo
 *
 */
@Controller
public class BusinessReportInfoAction {
	private Logger logger = Logger.getLogger(this.getClass());
	 
	@Resource(name="businessReportInfoService")
	private BusinessReportInfoService businessReportInfoService;
	
	/**
	 * 获取当日的每个客户经理的业绩 
	 *
	 */
	@RequestMapping(value = "getManagerBusinessDayInfo.htm",produces = "text/html; charset=utf-8")
    public @ResponseBody String GetFinancialManagerBusinessDayInfo(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
    	logger.info("--------统计客户经理业绩开始--------");
    	Calendar begin = Calendar.getInstance();
    	begin.setTime(new Date());
    	begin.set(begin.get(Calendar.YEAR),
    			begin.get(Calendar.MONTH),
    			begin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	
    	Calendar end = Calendar.getInstance();
    	end.setTime(begin.getTime());
    	end.set(Calendar.DATE, end.get(Calendar.DATE)+1);
    	
    	String result = businessReportInfoService.getFinancialManagerBusinessDayInfo(begin.getTime(), end.getTime());
    	
    	return "getManagerBusinessDayInfo("+result+")";
    }
	
	/**
	 * 获取上周的每个客户经理的业绩 
	 *
	 */
	@RequestMapping(value = "getManagerBusinessWeekInfo.htm",produces = "text/html; charset=utf-8")
    public @ResponseBody String GetFinancialManagerBusinessWeekInfo(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
		Calendar weekBegin = Calendar.getInstance();
	    weekBegin.setTime(new Date());
	    weekBegin.set(Calendar.DATE, weekBegin.get(Calendar.DATE) - 1);
	    weekBegin.setTime(weekBegin.getTime());
		weekBegin.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		weekBegin.set(weekBegin.get(Calendar.YEAR),
				weekBegin.get(Calendar.MONTH),
				weekBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar weekEnd = Calendar.getInstance();
		weekEnd.setTime(weekBegin.getTime());
		weekEnd.set(Calendar.DATE, weekEnd.get(Calendar.DATE)+6);
		weekEnd.set(weekEnd.get(Calendar.YEAR),
				weekEnd.get(Calendar.MONTH),
				weekEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
    	
    	Date beginTime = weekBegin.getTime();
    	Date endTime = weekEnd.getTime();
    	
    	String result = businessReportInfoService.getFinancialManagerBusinessDayInfo(beginTime, endTime);
    	
    	return "getManagerBusinessWeekInfo("+result+")";
    }
	
	/**
	 * 获取本月每个客户经理的业绩总和
	 */
	@RequestMapping(value = "getManagerBusinessMonthInfo.htm",produces = "text/html; charset=utf-8")
    public @ResponseBody String GetFinancialManagerBusinessMonthInfo(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
		String result = businessReportInfoService.getFinancialManagerBusinessMonthInfo(null, null);
    	return "getManagerBusinessMonthInfo("+result+")";
	}
}
