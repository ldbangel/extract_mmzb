package com.kejin.extract.web.info.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kejin.extract.domainservice.user.UserInfoDetailService;
import com.kejin.extract.domainservice.user.UserTradeInfoDetailService;

@Controller
public class UserTradeInfoDetailAction {
	
	@Resource(name = "userInfoDetailService")
	private UserInfoDetailService userInfoDetailService;

	@Resource(name = "userTradeInfoDetailService")
	private UserTradeInfoDetailService userTradeInfoDetailService;

	
	@RequestMapping(value = "/user/getUserInvestInfoDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserInvestInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
       
		try {
			String phone = req.getParameter("phone");
			String memberId = this.getMemberIdFromPhone(phone);
			if(memberId==null){
				throw new Exception("无memberId");
			}
	  
			String page = req.getParameter("page");
			boolean isNum =page!=null?page.matches("[0-9]+"):false; 
			if(!isNum){
				page = "1";
			}
	
			String bengin = req.getParameter("beginTime");
			String end = req.getParameter("endTime");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
		
			Date beginDate = sdf.parse(bengin);
			Date endDate = sdf.parse(end);
			
			String investInfos = userTradeInfoDetailService.getUserInvestInfoDetail(memberId, beginDate, endDate, Integer.valueOf(page));
			return "getUserInvestInfoDetail("+investInfos+")";
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}catch(Exception e){
			e.printStackTrace();
			return "getError({\"error\":\"输入的用户电话号码有误\"})";
		}
	}
	
	
	@RequestMapping(value = "/user/getUserRecovryInfoDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserRecovryInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		try {
			String phone = req.getParameter("phone");
			String memberId = this.getMemberIdFromPhone(phone);
			if(memberId==null){
				throw new Exception("无memberId");
			}
		
			String page = req.getParameter("page");
			boolean isNum =page!=null?page.matches("[0-9]+"):false; 
			if(!isNum){
				page = "0";
			}
			
	
			String bengin = req.getParameter("beginTime");
			
			String end = req.getParameter("endTime");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
	
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			 String recovryInfos = userTradeInfoDetailService.getUserRecoveryInfoDetail(memberId, beginDate, endDate, Integer.valueOf(page));
			 return "getUserRecovryInfoDetail("+recovryInfos+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}catch(Exception e){
			e.printStackTrace();
			return "getError({\"error\":\"输入的用户电话号码有误\"})";
		}
	}
	
	/**
	 * 使用统计数据库，会有对应的延迟
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/getUserChargeInfoDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserChargeInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		try {
			String phone = req.getParameter("phone");
			String memberId = this.getMemberIdFromPhone(phone);
			if(memberId==null){
				throw new Exception("无memberId");
			}
		
		
			String page = req.getParameter("page");
			boolean isNum =page!=null?page.matches("[0-9]+"):false; 
			if(!isNum){
				page = "0";
			}
			

		String bengin = req.getParameter("beginTime");
		
		String end = req.getParameter("endTime");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			 String chargeInfos = userTradeInfoDetailService.getUseChargeInfoDetail(memberId, beginDate, endDate, Integer.valueOf(page));
			 return "getUserChargeInfoDetail("+chargeInfos+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}catch(Exception e){
			e.printStackTrace();
			return "getError({\"error\":\"输入的用户电话号码有误\"})";
		}
	}
	
	/**
	 * 使用统计数据库，会有对应的延迟
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/getUserCashInfoDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserCashInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		try {
			String phone = req.getParameter("phone");
			String memberId = this.getMemberIdFromPhone(phone);
			if(memberId==null){
				throw new Exception("无memberId");
			}
		
			
			String page = req.getParameter("page");
			boolean isNum =page!=null?page.matches("[0-9]+"):false; 
			if(!isNum){
				page = "0";
			}
			
	
			String bengin = req.getParameter("beginTime");
			
			String end = req.getParameter("endTime");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
	
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			 String chargeInfos = userTradeInfoDetailService.getUserCashInfoDetail(memberId, beginDate, endDate, Integer.valueOf(page));
			 return "getUserCashInfoDetail("+chargeInfos+")";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}catch(Exception e){
			e.printStackTrace();
			return "getError({\"error\":\"输入的用户电话号码有误\"})";
		}
	}
	
	
	private String getMemberIdFromPhone(String phone){
		
		return userInfoDetailService.getMemberIdOfUser(phone);
		
	}
	
	

}
