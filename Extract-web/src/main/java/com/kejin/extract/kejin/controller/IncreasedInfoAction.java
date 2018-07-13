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
 * 新增用户
 * @author chenxiaojian
 *
 */
@Controller
public class IncreasedInfoAction {

	@Resource(name = "increasedInfoService")
    private OperationInfoService operationInfoService;

	/**
	 * 获取新增用户数据
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getIncreasedInfo.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfo(HttpServletRequest req,
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
		
	
		
	   // JSONObject j1t = JSON.parseObject(todayData);
	    JSONObject j1 = new JSONObject();
	    //j1.put("today", j1t.get("day"));
	    JSONObject j2t = JSON.parseObject(yesterdayData);
	    JSONObject j2 = new JSONObject();
	    j2.put("yesterday", j2t.get("day"));
	    JSONObject j3 = JSON.parseObject(weekData);
	    JSONObject j4 = JSON.parseObject(monthData);
	    JSONObject j5 = JSON.parseObject(yearData);
	    JSONObject j6 = JSON.parseObject(allData);
	
		
	    JSONObject jsonAll= new JSONObject();  
	    jsonAll.putAll(j1);
	    jsonAll.putAll(j2);
	    jsonAll.putAll(j3);
	    jsonAll.putAll(j4);
	    jsonAll.putAll(j5);
	    jsonAll.putAll(j6);
	    
	    //添加昨日日期
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(beginTime);
		ymd = ymd.replaceFirst("-","年");
		ymd = ymd.replaceFirst("-","月");
		ymd = ymd+"日";
	    jsonAll.put("dayTitleDate", ymd);
	    
	    //添加周日期
	    Calendar weekBegin = Calendar.getInstance();
	    weekBegin.setTime(beginTime);
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
		
		String ymd1 = sdf.format(weekBegin.getTime());
		ymd1 = ymd1.replaceFirst("-","年");
		ymd1 = ymd1.replaceFirst("-","月");
		ymd1 = ymd1+"日";
		String ymd2 = sdf.format(weekEnd.getTime());
		ymd2 = ymd2.replaceFirst("-","年");
		ymd2 = ymd2.replaceFirst("-","月");
		ymd2 = ymd2+"日";
		jsonAll.put("weekTitleDate", ymd1+"-"+ymd2);
    	
		return "getIncreasedInfo("+jsonAll.toJSONString()+")";
	}

	
	
	@RequestMapping(value = "getIncreasedInfoOfToday.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoToday(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {		
		//获取今日的数据
		String todayData = operationInfoService.getInfoOfDay(null, null);		
		return "getIncreasedInfoOfToday("+todayData+")";
	}
	
	@RequestMapping(value = "getIncreasedInfoOfYesterday.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoYesterday(HttpServletRequest req,
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
			
		return "getIncreasedInfoOfYesterday("+yesterdayData+")";
	}


	@RequestMapping(value = "getIncreasedInfoOfWeek.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfWeek(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
     	//获取本周的数据
		String weekData = operationInfoService.getInfoOfWeek(null);    
		
		return "getIncreasedInfoOfWeek("+weekData+")";
	}
	
	@RequestMapping(value = "getIncreasedInfoOfMonth.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfMonth(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本月的数据
		String monthData = operationInfoService.getInfoOfMonth(null);    
		
		return "getIncreasedInfoOfMonth("+monthData+")";
	}

	@RequestMapping(value = "getIncreasedInfoOfYear.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfYear(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本年的数据
		String yearData = operationInfoService.getInfoOfYear(null);		
		return "getIncreasedInfoOfYear("+yearData+")";
	}
	
	@RequestMapping(value = "getIncreasedInfoOfAll.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfAll(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		//获取全部的数据
		String allData = operationInfoService.getInfoOfAll(null);
		return "getIncreasedInfoOfAll("+allData+")";
	}
	
	@RequestMapping(value = "getIncreasedInfoOfDayDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfDayDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		//获取本周每日的详细数据
	    String dayDetail = operationInfoService.getInfoOfDayDetail(null, null);
		return "getIncreasedInfoOfDayDetail("+dayDetail+")";
	}
	
	//获取前四周的详细数据
	@RequestMapping(value = "getIncreasedInfoOfWeekDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfWeekDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
	    String weekDetail = operationInfoService.getInfoOfWeekDetail(null, null);
		return "getIncreasedInfoOfWeekDetail("+weekDetail+")";
	}
	
	@RequestMapping(value = "getIncreasedInfoOfMonthDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfMonthDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		 //获取本年每月详细的数据
		String monthDetail = operationInfoService.getInfoOfMonthDetail(null);
		return "getIncreasedInfoOfMonthDetail("+monthDetail+")";
	}
	
	
	@RequestMapping(value = "getIncreasedInfoOfFix.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfFix(HttpServletRequest req,
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
			
			return "getIncreasedInfoOfFix("+jsonAll.toJSONString()+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	
	
	
	@RequestMapping(value = "getIncreasedInfoOfFixDay.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfFixDay(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {	
		
		String bengin = req.getParameter("beginTime");
		
		String end = req.getParameter("endTime");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			//获取今日的数据
			String todayData = operationInfoService.getInfoOfDay(beginDate, endDate);		
			return "getIncreasedInfoOfFixDay("+todayData+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	@RequestMapping(value = "getIncreasedInfoOfFixDayDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getIncreasedInfoOfFixDayDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {	
		
		String bengin = req.getParameter("beginTime");
		
		String end = req.getParameter("endTime");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			 String dayDetail = operationInfoService.getInfoOfDayDetail(beginDate, endDate);
			 return "getIncreasedInfoOfFixDayDetail("+dayDetail+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	


}
