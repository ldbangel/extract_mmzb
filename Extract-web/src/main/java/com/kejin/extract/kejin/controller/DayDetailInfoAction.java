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

import com.kejin.extract.domainservice.service.DayDetailInfoService;
import com.kejin.extract.entity.service.util.TimeInterval;

@Controller
public class DayDetailInfoAction {
	@Resource(name = "dayDetailInfoService")
	private DayDetailInfoService dayDetailInfoService;

	@RequestMapping(value = "getDayCashInfoSummary.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayCashInfoSummary(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		String s = dayDetailInfoService.getDayCashInfoSummary(null,null);
			
		return "getDayCashInfoSummary("+s+")";
	}
	
	@RequestMapping(value = "getDayCashInfoDetail.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayCashInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String page = req.getParameter("page");
		boolean isNum =page!=null?page.matches("[0-9]+"):false; 
		if(!isNum){
			page = "1";
		}
		
		String s = dayDetailInfoService.getDayCashInfoDetail(null,null,Integer.valueOf(page));
			
		return "getDayCashInfoDetail("+s+")";
	}
	
	@RequestMapping(value = "getDayChargeInfoDetail.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayChargeInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String page = req.getParameter("page");
		boolean isNum =page!=null?page.matches("[0-9]+"):false; 
		if(!isNum){
			page = "1";
		}
		
		String s = dayDetailInfoService.getDayChargeInfoDetail(null,null,Integer.valueOf(page));
			
		return "getDayChargeInfoDetail("+s+")";
	}
	
	
	@RequestMapping(value = "getDayChargeInfoSummay.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayChargeInfoSummay(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String s = dayDetailInfoService.getDayChargeInfoSummay(null,null);
			
		return "getDayChargeInfoSummay("+s+")";
	}
	
	@RequestMapping(value = "getDayInvestInfoDetail.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayInvestInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String page = req.getParameter("page");
		boolean isNum =page!=null?page.matches("[0-9]+"):false; 
		if(!isNum){
			page = "1";
		}
		
		String s = dayDetailInfoService.getDayInvestInfoDetail(null,null,Integer.valueOf(page));
			
		return "getDayInvestInfoDetail("+s+")";
	}
	
	@RequestMapping(value = "getDayInvsetInfoSummay.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayInvsetInfoSummay(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String s = dayDetailInfoService.getDayInvsetInfoSummay(null,null);
			
		return "getDayInvsetInfoSummay("+s+")";
	}
	
	
	@RequestMapping(value = "getDayCashInfoSummaryOfFix.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayCashInfoSummaryOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String bengin = req.getParameter("beginTime");

		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			TimeInterval interval = generateDateInterval(beginDate);
		
		
			String s = dayDetailInfoService.getDayCashInfoSummary(interval.getBeginTime(),interval.getEndTime());
				
			return "getDayCashInfoSummaryOfFix("+s+")";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	@RequestMapping(value = "getDayCashInfoDetailOfFix.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayCashInfoDetailOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String bengin = req.getParameter("beginTime");
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
		String page = req.getParameter("page");
		boolean isNum =page!=null?page.matches("[0-9]+"):false; 
		if(!isNum){
			page = "1";
		}
		
		try {
			Date beginDate=sdf.parse(bengin);
			TimeInterval interval = generateDateInterval(beginDate);
		
		
			String s = dayDetailInfoService.getDayCashInfoDetail(interval.getBeginTime(),interval.getEndTime(),Integer.valueOf(page));
				
			return "getDayCashInfoDetailOfFix("+s+")";
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	@RequestMapping(value = "getDayChargeInfoDetailOfFix.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayChargeInfoDetailOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		

		String bengin = req.getParameter("beginTime");
		

		String page = req.getParameter("page");
		boolean isNum =page!=null?page.matches("[0-9]+"):false; 
		if(!isNum){
			page = "1";
		}
			
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			TimeInterval interval = generateDateInterval(beginDate);
			
			String s = dayDetailInfoService.getDayChargeInfoDetail(interval.getBeginTime(),interval.getEndTime(),Integer.valueOf(page));
				
			return "getDayChargeInfoDetailOfFix("+s+")";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	
	@RequestMapping(value = "getDayChargeInfoSummayOfFix.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayChargeInfoSummayOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
	
		String bengin = req.getParameter("beginTime");
		

		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			TimeInterval interval = generateDateInterval(beginDate);
		
			String s = dayDetailInfoService.getDayChargeInfoSummay(interval.getBeginTime(),interval.getEndTime());
				
			return "getDayChargeInfoSummayOfFix("+s+")";
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	@RequestMapping(value = "getDayInvestInfoDetailOfFix.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayInvestInfoDetailOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String bengin = req.getParameter("beginTime");
		

		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		
		String page = req.getParameter("page");
		boolean isNum =page!=null?page.matches("[0-9]+"):false; 
		if(!isNum){
			page = "1";
		}
		
		try {
			Date beginDate=sdf.parse(bengin);
			TimeInterval interval = generateDateInterval(beginDate);
		
			String s = dayDetailInfoService.getDayInvestInfoDetail(interval.getBeginTime(),interval.getEndTime(),Integer.valueOf(page));
				
			return "getDayInvestInfoDetailOfFix("+s+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	@RequestMapping(value = "getDayInvsetInfoSummayOfFix.htm",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getDayInvsetInfoSummayOfFix(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		
		String bengin = req.getParameter("beginTime");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
		try {
			Date beginDate=sdf.parse(bengin);
			TimeInterval interval = generateDateInterval(beginDate);
		
			String s = dayDetailInfoService.getDayInvsetInfoSummay(interval.getBeginTime(),interval.getEndTime());
				
			return "getDayInvsetInfoSummayOfFix("+s+")";
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}
	}
	
	
	private TimeInterval generateDateInterval(Date beginDate){
		
		Calendar calendarBegin = Calendar.getInstance();
		
		calendarBegin.setTime(beginDate);

		calendarBegin.set(calendarBegin.get(Calendar.YEAR),
				calendarBegin.get(Calendar.MONTH),
				calendarBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar calendarEnd = Calendar.getInstance();

		calendarEnd.setTime(calendarBegin.getTime());

		calendarEnd.set(Calendar.DATE, calendarEnd.get(Calendar.DATE) + 1);
		
		Date beginTime = calendarBegin.getTime();
		Date endTime = calendarEnd.getTime();	
		
		TimeInterval interval = new TimeInterval();
		
		interval.setBeginTime(beginTime);
		interval.setEndTime(endTime);
		
		return interval;
		
	}

}
