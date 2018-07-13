package com.kejin.extract.kejin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kejin.extract.domainservice.service.BaseInfoService;

@Controller
public class BaseInfoAction {

	@Resource(name = "baseInfoService")
	private BaseInfoService baseInfoService;

	/**
	 * 妈妈钱包首页信息
	 */
	@RequestMapping(value = "getBaseInfo.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String toLogin(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {

		return "getBaseInfo("+baseInfoService.getBaseInfo()+")";
	}

}
