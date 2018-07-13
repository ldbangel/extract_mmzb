package com.kejin.extract.web.info.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kejin.extract.domainservice.user.CreditInfoService;
import com.kejin.extract.domainservice.user.UserInfoDetailService;

@Controller
public class UserCreditDetailAction {
	
	@Resource(name = "userInfoDetailService")
	private UserInfoDetailService userInfoDetailService;
	
	@Resource(name = "creditInfoService")
	private CreditInfoService creditInfoService;
	
	
	@RequestMapping(value = "/user/getUserCreditDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserCreditDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
       
		try {
			String phone = req.getParameter("phone");
			String memberId =userInfoDetailService.getMemberIdOfUser(phone);
			if(memberId==null){
				throw new Exception("无memberId");
			}
		
			String creditInfos = creditInfoService.generateCreditInfo(memberId);
			return "getUserCreditDetail("+creditInfos+")";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "getError({\"error\":\"时间格式不对  or 请求数据错误\"})";
		}catch(Exception e){
			e.printStackTrace();
			return "getError({\"error\":\"输入的用户电话号码有误\"})";
		}
	}

}
