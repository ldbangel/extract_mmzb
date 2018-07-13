package com.kejin.extract.kejin.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kejin.extract.domainservice.service.OperationInfoService;

@Controller
public class SimpleReportInfoAction {
	
	@Resource(name = "simpleReportInfoService")
    private OperationInfoService operationInfoService;
	
	
	@RequestMapping(value = "getSimpleReportInfoOfYesterday.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoYesterday(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {	
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();

		yesterday.setTime(today.getTime());

		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		
		//获取作日的数据
		String yesterdayData = operationInfoService.getInfoOfDay(beginTime, null);
			
		return "getSimpleReportInfoOfYesterday("+yesterdayData+")";
	}


	@RequestMapping(value = "getSimpleReportInfoOfWeek.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfWeek(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(new Date());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		yesterday.set(yesterday.get(Calendar.YEAR),
				yesterday.get(Calendar.MONTH),
				yesterday.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Date beginTime = yesterday.getTime();
     	//获取上周的数据
		String weekData = operationInfoService.getInfoOfWeek(beginTime);    
		
		return "getSimpleReportInfoOfWeek("+weekData+")";
	}
	
	@RequestMapping(value = "getSimpleReportInfoOfMonth.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfMonth(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本月的数据
		String monthData = operationInfoService.getInfoOfMonth(null);    
		
		return "getSimpleReportInfoOfMonth("+monthData+")";
	}
}
