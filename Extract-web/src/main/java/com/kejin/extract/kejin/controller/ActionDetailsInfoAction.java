package com.kejin.extract.kejin.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.ActionDetailsInfoService;

/**
 * 获取提现汇总信息(用于前端画图)
 *
 */
@Controller
public class ActionDetailsInfoAction {
	
	@Resource(name = "actionDetailsInfoService")
	private ActionDetailsInfoService actionDetailsInfoService;
	
	@RequestMapping(value = "getCashCollectDetailsInfo.htm",produces = "text/html; charset=utf-8")
	public @ResponseBody String getCashCollectInfo(HttpServletRequest request){
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		
		Date beginTime = yesterday.getTime();	
		Date endTime = today.getTime();
    	
    	List<Map<String,Object>> list = actionDetailsInfoService.getCashDetails(beginTime, endTime);
    	BigDecimal allCash = new BigDecimal(0);
    	BigDecimal regularCash = new BigDecimal(0);
    	BigDecimal creditCash = new BigDecimal(0);
    	BigDecimal chargeCash = new BigDecimal(0);
    	for(Map<String,Object> map : list){
    		if("定期提现".equals(map.get("cashOrigin"))){
    			regularCash = regularCash.add((BigDecimal)map.get("allAmount"));
    			allCash = allCash.add((BigDecimal)map.get("allAmount"));
    		}else if("债权提现".equals(map.get("cashOrigin"))){
    			creditCash = creditCash.add((BigDecimal)map.get("allAmount"));
    			allCash = allCash.add((BigDecimal)map.get("allAmount"));
    		}else if("余额提现".equals(map.get("cashOrigin"))){
    			chargeCash = chargeCash.add((BigDecimal)map.get("allAmount"));
    			allCash = allCash.add((BigDecimal)map.get("allAmount"));
    		}
    	}
    	DecimalFormat format1 = new DecimalFormat("0.00%");
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("allCash", allCash);
    	map.put("regularCash", regularCash);
    	map.put("creditCash", creditCash);
    	map.put("chargeCash", chargeCash);
    	if(allCash.compareTo(new BigDecimal(0)) > 0){
    		map.put("regularCashRate", format1.format(regularCash.divide(allCash, 6, BigDecimal.ROUND_HALF_UP)));
    		map.put("creditCashRate", format1.format(creditCash.divide(allCash, 6, BigDecimal.ROUND_HALF_UP)));
    		map.put("chargeCashRate", format1.format(chargeCash.divide(allCash, 6, BigDecimal.ROUND_HALF_UP)));
    	}else{
    		map.put("regularCashRate", format1.format(0));
    		map.put("creditCashRate", format1.format(0));
    		map.put("chargeCashRate", format1.format(0));
    	}
    	Map<String,Object> data = new HashMap<String, Object>();
    	data.put("day", map);
    	
    	//图片标题日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(beginTime);
		ymd = ymd.replaceFirst("-","年");
		ymd = ymd.replaceFirst("-","月");
		ymd = ymd+"日";
		data.put("titleDate", ymd);
		return "getCashCollectOfDay("+JSON.toJSONString(data)+")";
	}
}
