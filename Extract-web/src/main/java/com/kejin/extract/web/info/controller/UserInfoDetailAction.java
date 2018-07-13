package com.kejin.extract.web.info.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kejin.extract.domainservice.user.UserInfoDetailService;

@Controller
public class UserInfoDetailAction {

	@Resource(name = "userInfoDetailService")
	private UserInfoDetailService userInfoDetailService;

	@RequestMapping(value = "/user/getUserInfoDetail.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getUserInfoDetail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		String phone = req.getParameter("phone"); ;
		return "getUserInfoDetail("+userInfoDetailService.getUserBaseInfoDetail(phone)+")";
	}

}
