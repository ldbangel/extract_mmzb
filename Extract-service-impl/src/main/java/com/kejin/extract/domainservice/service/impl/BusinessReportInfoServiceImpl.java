package com.kejin.extract.domainservice.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.common.Excel2AccountBalanceUtil;
import com.kejin.extract.domainservice.service.BusinessReportInfoService;
import com.kejin.extract.kejin.process.dao.DEmployeeDao;
import com.kejin.extract.kejin.service.dao.BusinessReportInfoDao;
import com.kejin.extract.mmmoney.service.dao.AchievementManagerFromProdDao;

@Service("businessReportInfoService")
public class BusinessReportInfoServiceImpl implements BusinessReportInfoService {
	@Autowired
	private BusinessReportInfoDao businessReportInfoDao;
	@Autowired
	private AchievementManagerFromProdDao achievementManagerFromProdDao;
	@Autowired
	private DEmployeeDao dEmployeeDao;
	
	@Override
	public String getFinancialManagerBusinessDayInfo(Date beginTime, Date endTime) {
		List<Map<String, Object>> listInfo = businessReportInfoDao
				.getBusinessManagerAchievementInfo(beginTime, endTime, beginTime);
		
		//补齐客户经理的个人和公司明细
		Set<String> set = new HashSet<String>();
		for(Map<String, Object> model : listInfo){
			if(model.get("manager") != null
					&& !"".equals(model.get("manager"))
					&& !"总计".equals(model.get("manager"))){
				String fcode = (String) model.get("fcode");
				set.add(fcode);
			}
		}
		List<Map<String, Object>> resultInfo = new ArrayList<Map<String,Object>>();
		for(String str : set){
			List<Map<String, Object>> tem = new ArrayList<Map<String,Object>>();
			for(Map<String, Object> info : listInfo){
				if(str.equals(info.get("fcode"))){
					tem.add(info);
				}
			}
			if(tem.size() == 2){
				Map<String, Object> map = tem.get(1);
				Map<String, Object> addMap = new HashMap<String, Object>();
				addMap.put("manager", " ");
				addMap.put("fcode", str);
				addMap.put("newInvest", 0.00);
				addMap.put("reInvest", 0.00);
				addMap.put("allAmount", 0.00);
				addMap.put("yearInvest", 0.00);
				addMap.put("reinvestRate", 0.00);
				addMap.put("newNum", 0);
				if("公司".equals(map.get("userSource"))){
					addMap.put("userSource", "个人");
					tem.add(2, addMap);
				}else{
					addMap.put("userSource", "公司");
					tem.add(1, addMap);
				}
			}
			resultInfo.addAll(tem);
		}
		resultInfo.add(listInfo.get(listInfo.size()-1));
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("managerBalanceDay", resultInfo);
		
		if((endTime.getTime()-beginTime.getTime())/(1000*24*3600) > 2){
			//图片标题日期
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String ymd1 = sdf.format(beginTime.getTime());
			ymd1 = ymd1.replaceFirst("-","年");
			ymd1 = ymd1.replaceFirst("-","月");
			ymd1 = ymd1+"日";
			String ymd2 = sdf.format(endTime.getTime());
			ymd2 = ymd2.replaceFirst("-","年");
			ymd2 = ymd2.replaceFirst("-","月");
			ymd2 = ymd2+"日";
			data.put("titleDate", ymd1+"-"+ymd2);
		}else{
			//图片标题日期
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String ymd = sdf.format(beginTime);
			ymd = ymd.replaceFirst("-","年");
			ymd = ymd.replaceFirst("-","月");
			ymd = ymd+"日";
			data.put("titleDate", ymd);
		}
		
		return JSON.toJSONString(data);
	}
	
	@Override
	public String getFinancialManagerBusinessMonthInfo(Date beginTime, Date endTime) {
		Calendar monthBegin = Calendar.getInstance();		
		if(beginTime!=null){
			monthBegin.setTime(beginTime);
		}else{
			monthBegin.setTime(new Date());
		}		
		
		monthBegin.set(Calendar.DAY_OF_MONTH,1);
		
		monthBegin.set(monthBegin.get(Calendar.YEAR),
				monthBegin.get(Calendar.MONTH),
				monthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.setTime(monthBegin.getTime());
		monthEnd.set(Calendar.MONTH, monthEnd.get(Calendar.MONTH) + 1);
		
		Calendar lastMonthBegin = Calendar.getInstance();
		lastMonthBegin.setTime(monthBegin.getTime());
		lastMonthBegin.set(Calendar.MONTH, lastMonthBegin.get(Calendar.MONTH) - 1);
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(new Date());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE)-1);
		Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String,Object>>>();
		//获取本月数据
		List<Map<String, Object>> listInfo1 = businessReportInfoDao
				.getBusinessManagerAchievementInfo(monthBegin.getTime(), monthEnd.getTime(), yesterday.getTime());
		
		/*Calendar lastMonthLastDay = Calendar.getInstance();
		lastMonthLastDay.setTime(monthBegin.getTime());
		lastMonthLastDay.set(Calendar.DATE, monthBegin.get(Calendar.DATE)-1);
		//获取上月数据
		List<Map<String, Object>> listInfo2 = businessReportInfoDao
				.getBusinessManagerAchievementInfo(lastMonthBegin.getTime(), monthBegin.getTime(), lastMonthLastDay.getTime());*/
		
		DecimalFormat format1 = new DecimalFormat("0.00%");
		
		//计算月增长
		for (int i = 0; i < listInfo1.size(); i++) {
			/*for (int j = 0; j < listInfo2.size(); j++) {
				if(listInfo1.get(i).get("manager").equals(listInfo2.get(j).get("manager"))){
					BigDecimal a = (BigDecimal) listInfo1.get(i).get("totalAssets");
					BigDecimal b = (BigDecimal) listInfo2.get(j).get("totalAssets");
					BigDecimal grow = a.subtract(b);
					listInfo1.get(i).put("grow", grow);
					break;
				}
			}*/
			/*BigDecimal allAmount = (BigDecimal) listInfo1.get(i).get("allAmount");
			BigDecimal newAmount = (BigDecimal) listInfo1.get(i).get("newInvest");
			BigDecimal recoveryAmount = (BigDecimal) listInfo1.get(i).get("recovery");
			BigDecimal reInvestAmount = allAmount.subtract(newAmount);
			if(recoveryAmount.compareTo(new BigDecimal(0)) != 0){
				if(reInvestAmount.divide(recoveryAmount, 6, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(1)) > 0){
					listInfo1.get(i).put("reInvestRate", format1.format(1));
				}else{
					listInfo1.get(i).put("reInvestRate", format1.format(reInvestAmount.divide(recoveryAmount, 6, BigDecimal.ROUND_HALF_UP)));
				}
			}else{
				listInfo1.get(i).put("reInvestRate", format1.format(0));
			}*/
			listInfo1.get(i).put("reinvestRate", format1.format(listInfo1.get(i).get("reinvestRate")));
		}
		
		/*//计算总计
		BigDecimal newInvestAll = new BigDecimal(0);
		BigDecimal reinvestAll = new BigDecimal(0);
		BigDecimal investAll = new BigDecimal(0);
		BigDecimal recoveryAll = new BigDecimal(0);
		BigDecimal monthGrowAll = new BigDecimal(0);
		
		for(Map<String, Object> info : listInfo1){
			newInvestAll = newInvestAll.add((BigDecimal)info.get("newInvest"));
			reinvestAll = reinvestAll.add((BigDecimal)info.get("reInvest"));
			investAll = investAll.add((BigDecimal)info.get("allAmount"));
			recoveryAll = recoveryAll.add((BigDecimal)info.get("recovery"));
			monthGrowAll = monthGrowAll.add((BigDecimal)info.get("grow"));
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("manager", "总计");
		m.put("newInvest", newInvestAll);
		m.put("reInvest", reinvestAll);
		m.put("allAmount", investAll);
		m.put("recovery", recoveryAll);
		m.put("grow", monthGrowAll);
		if(recoveryAll.compareTo(new BigDecimal(0)) != 0){
			if(reinvestAll.divide(recoveryAll, 6, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(1)) > 0){
				m.put("reInvestRate", format1.format(1));
			}else{
				m.put("reInvestRate", format1.format(reinvestAll.divide(recoveryAll, 6, BigDecimal.ROUND_HALF_UP)));
			}
		}else{
			m.put("reInvestRate", format1.format(0));
		}
		listInfo1.add(m);*/
		data.put("managerBalanceMonth", listInfo1);
		return JSON.toJSONString(data);
	}

	@Override
	public String getAllBusinessInfo(Date begin, Date end) {
		return null;
	}

	/**
	 * 获取实时账户余额
	 */
	@Override
	public String getAccountBalanceInfo() {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar oneDayAgo = Calendar.getInstance();
		oneDayAgo.setTime(today.getTime());
		oneDayAgo.set(Calendar.DATE, oneDayAgo.get(Calendar.DATE) - 1);
		
		Calendar twoDayAgo = Calendar.getInstance();
		twoDayAgo.setTime(today.getTime());
		twoDayAgo.set(Calendar.DATE, twoDayAgo.get(Calendar.DATE) - 2);
		
		Calendar threeDayAgo = Calendar.getInstance();
		threeDayAgo.setTime(today.getTime());
		threeDayAgo.set(Calendar.DATE, threeDayAgo.get(Calendar.DATE) - 3);
		List<Map<String,Object>> balanceList = achievementManagerFromProdDao
				.getAccountBalanceInfo(oneDayAgo.getTime(), twoDayAgo.getTime(), threeDayAgo.getTime());
		try {
			Excel2AccountBalanceUtil.excelUtil(balanceList,"");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
