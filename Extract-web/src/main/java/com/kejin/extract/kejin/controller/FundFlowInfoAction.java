package com.kejin.extract.kejin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kejin.extract.domainservice.service.OperationInfoService;

/**
 * 资金流
 * @author chenxiaojian
 *
 */
@Controller
public class FundFlowInfoAction {
	@Resource(name = "fundFlowInfoService")
    private OperationInfoService operationInfoService;

	@RequestMapping(value = "getFundFlowInfo.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfo(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		//获取今日的数据
		//String todayData = operationInfoService.getInfoOfDay(null, null);
		
		Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		
		//获取作日的数据
		String yesterdayData = operationInfoService.getInfoOfDay(beginTime, null);
		//获取本周的数据
		String weekData = operationInfoService.getInfoOfWeek(null);
		//获取本月的数据
		String monthData = operationInfoService.getInfoOfMonth(null);
		//获取本年的数据
		String yearData = operationInfoService.getInfoOfYear(null);
		//获取全部的数据
		String allData = operationInfoService.getInfoOfAll(null);
		
	    /*JSONObject j1t = JSON.parseObject(todayData);
	    JSONObject j1 = new JSONObject();
	    j1.put("today", j1t.get("day"));*/
	    JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("yesterday", j2t.get("day"));
	    JSONObject j3 = JSON.parseObject(weekData);
	    JSONObject j4 = JSON.parseObject(monthData);
	    JSONObject j5 = JSON.parseObject(yearData);
	    JSONObject j6 = JSON.parseObject(allData);
		
	    JSONObject jsonAll= new JSONObject();  
	   // jsonAll.putAll(j1);
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    jsonAll.putAll(j4);
	    jsonAll.putAll(j5);
	    jsonAll.putAll(j6); 
	    
		return "getFundFlowInfo("+jsonAll.toJSONString()+")";
	}

	
	
	@RequestMapping(value = "getFundFlowInfoOfToday.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoToday(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {		
		//获取今日的数据
		String todayData = operationInfoService.getInfoOfDay(null, null);		
		return "getFundFlowInfoOfToday("+todayData+")";
	}
	
	@RequestMapping(value = "getFundFlowInfoOfYesterday.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoYesterday(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {	
		Calendar today = Calendar.getInstance();

		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();

		yesterday.setTime(today.getTime());

		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		Date endTime = today.getTime();	
		
		//获取作日的数据
		String yesterdayData = operationInfoService.getInfoOfDay(beginTime, null);
			
		return "getFundFlowInfoOfYesterday("+yesterdayData+")";
	}


	@RequestMapping(value = "getFundFlowInfoOfWeek.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfWeek(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
     	//获取本周的数据
		String weekData = operationInfoService.getInfoOfWeek(null);    
		
		return "getFundFlowInfoOfWeek("+weekData+")";
	}
	
	@RequestMapping(value = "getFundFlowInfoOfMonth.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfMonth(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本月的数据
		String monthData = operationInfoService.getInfoOfMonth(null);    
		
		return "getFundFlowInfoOfMonth("+monthData+")";
	}

	@RequestMapping(value = "getFundFlowInfoOfYear.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfYear(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本年的数据
		String yearData = operationInfoService.getInfoOfYear(null);		
		return "getFundFlowInfoOfYear("+yearData+")";
	}
	
	@RequestMapping(value = "getFundFlowInfoOfAll.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfAll(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		//获取全部的数据
		String allData = operationInfoService.getInfoOfAll(null);
		return "getFundFlowInfoOfAll("+allData+")";
	}
	
	@RequestMapping(value = "getFundFlowInfoOfDayDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfDayDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本周每日的详细数据
	    String dayDetail = operationInfoService.getInfoOfDayDetail(null, null);
		return "getFundFlowInfoOfDayDetail("+dayDetail+")";
	}
	
	//获取前四周的详细数据
	@RequestMapping(value = "getFundFlowInfoOfWeekDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfWeekDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
	    String weekDetail = operationInfoService.getInfoOfWeekDetail(null, null);
		return "getFundFlowInfoOfDayWeek("+weekDetail+")";
	}
	
	@RequestMapping(value = "getFundFlowInfoOfMonthDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfMonthDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		 //获取本年每月详细的数据
		String monthDetail = operationInfoService.getInfoOfMonthDetail(null);
		return "getFundFlowInfoOfMonthDetail("+monthDetail+")";
	}
	
	@RequestMapping(value = "getFundFlowInfoOfFix.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {	
		
		String bengin = req.getParameter("beginTime");
		
		String end = req.getParameter("endTime");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			//获取今日的数据
			String todayData = operationInfoService.getInfoOfDay(beginDate, endDate);
			String yearData = operationInfoService.getInfoOfYear(beginDate);	
			String allData = operationInfoService.getInfoOfAll(null);
			String dayDetail = operationInfoService.getInfoOfDayDetail(beginDate, endDate);
			
			JSONObject j1 = JSON.parseObject(todayData);
			JSONObject j2 = JSON.parseObject(yearData);
			JSONObject j3 = JSON.parseObject(allData);
			JSONObject j4 = JSON.parseObject(dayDetail);
			
		    JSONObject jsonAll= new JSONObject();  
		    jsonAll.putAll(j1);
		    jsonAll.putAll(j2);
		    jsonAll.putAll(j3);
		    jsonAll.putAll(j4);
			
			return "getFundFlowInfoOfFix("+jsonAll.toJSONString()+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	@RequestMapping(value = "getFundFlowInfoOfFixDayDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getFundFlowInfoOfFixDayDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {	
		
		String bengin = req.getParameter("beginTime");
		
		String end = req.getParameter("endTime");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			 String dayDetail = operationInfoService.getInfoOfDayDetail(beginDate, endDate);
			 return "getFundFlowInfoOfFixDayDetail("+dayDetail+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
}
