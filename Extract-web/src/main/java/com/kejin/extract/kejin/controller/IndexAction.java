package com.kejin.extract.kejin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexAction {
	
	@RequestMapping(value = "index.htm")
	public ModelAndView toLogin(HttpServletRequest req, HttpServletResponse resp, ModelMap model){
		
		return new ModelAndView("index.html");
	}

}
