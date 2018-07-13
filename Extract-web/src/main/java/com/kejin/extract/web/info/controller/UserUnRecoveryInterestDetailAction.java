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

import com.kejin.extract.domainservice.user.UnRecoveryInterestService;
import com.kejin.extract.domainservice.user.UserInfoDetailService;

@Controller
public class UserUnRecoveryInterestDetailAction {
	
	@Resource(name = "userInfoDetailService")
	private UserInfoDetailService userInfoDetailService;
	
	@Resource(name = "unRecoveryInterestService")
	private UnRecoveryInterestService unRecoveryInterestService;
	
	@RequestMapping(value = "/user/getUserUnRecovryInfoDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserUnRecovryInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
       
		try {
			String phone = req.getParameter("phone");
			String memberId = userInfoDetailService.getMemberIdOfUser(phone);
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
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
	
			Date beginDate=sdf.parse(bengin);
			Date endDate=sdf.parse(end);
			
			String unRecovryInfos = unRecoveryInterestService.getUserUnRecoveryInterest(memberId, beginDate, endDate, Integer.valueOf(page));
			
			return "getUserUnRecovryInfoDetail("+unRecovryInfos+")";
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}catch(Exception e){
			e.printStackTrace();
			return "getError({\"error\":\"输入的用户电话号码有误\"})";
		}
	}

}
