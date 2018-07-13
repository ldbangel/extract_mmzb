package com.kejin.extract.kejin.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.BusinessReportInfoService;
import com.kejin.extract.domainservice.service.PanelInfoService;
import com.kejin.extract.domainservice.service.TradeRealTimeDataService;
import com.kejin.extract.entity.service.BaseInvestInfoModel;

@Controller
public class PanelInfoAction {
	@Resource(name="panelInfoService")
	private PanelInfoService panelInfoService;
	@Resource(name="tradeRealTimeDataService")
	private TradeRealTimeDataService tradeRealTimeDataService;
	@Resource(name="businessReportInfoService")
	private BusinessReportInfoService businessReportInfoService;
	
	@RequestMapping(value = "getPanelInvestInfo.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getPanelInvestInfo(){
		Map<String, Object> map = new HashMap<String, Object>();
		//获取实时定期宝投资数据
		List<BaseInvestInfoModel> investInfos = panelInfoService.getInvestTradeTimeData();
		//获取实时平台交易数据
		Map<String, Object> platformRealTimeData = tradeRealTimeDataService.getTradeRealTimeData();
		
		
		map.put("investInfos", investInfos);
		map.put("platformRealTimeData", platformRealTimeData);
		
		return "getPanelInvestInfo("+JSON.toJSONString(map)+")";
	}
	
	@RequestMapping(value = "getPanelAchievementsInfo.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getPanelAchievementsInfo(){
		Calendar begin = Calendar.getInstance();
    	begin.setTime(new Date());
    	begin.set(begin.get(Calendar.YEAR),
    			begin.get(Calendar.MONTH),
    			begin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	
    	Calendar end = Calendar.getInstance();
    	end.setTime(begin.getTime());
    	end.set(Calendar.DATE, end.get(Calendar.DATE)+1);
    	
    	
    	String achievementInfos = businessReportInfoService.getFinancialManagerBusinessDayInfo(begin.getTime(), end.getTime());
    	
		return "getPanelAchievementsInfo("+achievementInfos+")";
	}
}
