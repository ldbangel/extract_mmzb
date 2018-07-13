package com.kejin.extract.kejin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminLoginAction {
	/*@Resource(name = "systemAdminLoginService")
	private SystemAdminLoginService systemAdminLoginService;*/
	
	@RequestMapping(value = "adminLogin.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getTradeInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("password", password);
        
       // String permissions = systemAdminLoginService.getAdminPermissions(map);
        
		return  null;
	}
}
