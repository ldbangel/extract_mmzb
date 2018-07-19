package com.kejin.extract.domainservice.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.service.ThreadService;
import com.kejin.extract.domainservice.service.TradeRealTimeDataService;
import com.kejin.extract.mmmoney.service.dao.TradeRealTimeDataDao;

/**
 * 获取交易实时数据service
 * 从备份数据库拿数据
 * @author liudongbo
 *
 */
@Service("tradeRealTimeDataService")
public class TradeRealTimeDataServiceImpl implements TradeRealTimeDataService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private TradeRealTimeDataDao tradeRealTimeDataDao;
	@Resource(name = "threadService")
	private ThreadService threadService;

	@Override
	public Map<String, Object> getTradeRealTimeData() {
		Calendar begin = Calendar.getInstance();
		begin.setTime(new Date());
		begin.set(begin.get(Calendar.YEAR),
				begin.get(Calendar.MONTH),
				begin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(begin.getTime());
		end.set(Calendar.DATE, end.get(Calendar.DATE) + 1);
		
		Calendar thisMonthBegin = Calendar.getInstance();
		thisMonthBegin.setTime(new Date());
		thisMonthBegin.set(Calendar.DAY_OF_MONTH,1);
		thisMonthBegin.set(thisMonthBegin.get(Calendar.YEAR),
				thisMonthBegin.get(Calendar.MONTH),
				thisMonthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar thisMonthEnd = Calendar.getInstance();
		thisMonthEnd.setTime(thisMonthBegin.getTime());
		thisMonthEnd.set(Calendar.MONTH, thisMonthEnd.get(Calendar.MONTH) + 1);
		thisMonthEnd.set(thisMonthEnd.get(Calendar.YEAR),
				thisMonthEnd.get(Calendar.MONTH),
				thisMonthEnd.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Map<String, Object> resultMap = tradeRealTimeDataDao.getTradeRealTimeData(begin.getTime(),
				end.getTime(), thisMonthBegin.getTime(), thisMonthEnd.getTime());
		//获取所有投资者的用户余额
		BigDecimal allInvestorAmount = threadService.exportMemberBalanceExcel();
		logger.info("用户专户余额为:"+allInvestorAmount);
		//BigDecimal allBorrowersAmount = threadService.getAllBorrowersAmount();
		
		//格式转换
		DecimalFormat format3 = new DecimalFormat("#");
		BigDecimal reInvestAmount = null;
		if(resultMap.get("regularInvestAmount") != null && resultMap.get("newInvestAmount") != null){
			reInvestAmount = ((BigDecimal) resultMap.get("regularInvestAmount")).subtract((BigDecimal) resultMap.get("newInvestAmount"));
		}else if(resultMap.get("regularInvestAmount") != null && resultMap.get("newInvestAmount") == null){
			reInvestAmount = (BigDecimal) resultMap.get("regularInvestAmount");
		}else if(resultMap.get("regularInvestAmount") == null && resultMap.get("newInvestAmount") != null){
			reInvestAmount = (BigDecimal) resultMap.get("newInvestAmount");
		}
		if(resultMap.get("regularInvestAmount") != null){
			resultMap.put("regularInvestAmount", format3.format(((BigDecimal) resultMap.get("regularInvestAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("regularInvestAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		if(resultMap.get("newInvestAmount") != null){
			resultMap.put("newInvestAmount", format3.format(((BigDecimal) resultMap.get("newInvestAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("newInvestAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		if(reInvestAmount != null){
			resultMap.put("reInvestAmount", format3.format(reInvestAmount.divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("reInvestAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		if(resultMap.get("creditInvestAmount") != null){
			resultMap.put("creditInvestAmount", format3.format(((BigDecimal) resultMap.get("creditInvestAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("creditInvestAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		if(resultMap.get("recoveryAllAmount") != null){
			resultMap.put("recoveryAllAmount", format3.format(((BigDecimal) resultMap.get("recoveryAllAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("recoveryAllAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		if(resultMap.get("bidSuccessAmount") != null){
			resultMap.put("bidSuccessAmount", format3.format(((BigDecimal) resultMap.get("bidSuccessAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("bidSuccessAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		if(resultMap.get("paySuccessAmount") != null){
			resultMap.put("paySuccessAmount", format3.format(((BigDecimal) resultMap.get("paySuccessAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("paySuccessAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		/*if(resultMap.get("balanceAllAmount") != null){
			resultMap.put("balanceAllAmount", format3.format(((BigDecimal) resultMap.get("balanceAllAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("balanceAllAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}*/
		resultMap.put("balanceAllInvestorAmount", format3.format(allInvestorAmount.divide(new BigDecimal(10000)))+"万");
		//resultMap.put("balanceAllBorrowersAmount", format3.format(allBorrowersAmount.divide(new BigDecimal(10000)))+"万");
		if(resultMap.get("regularInvestAmountOfMonth") != null){
			resultMap.put("regularInvestAmountOfMonth", format3.format(((BigDecimal) resultMap.get("regularInvestAmountOfMonth")).divide(new BigDecimal(10000)))+"万");
		}else{
			resultMap.put("regularInvestAmountOfMonth", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		
		//日期时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH");
		String ymd = sdf.format(new Date());
		ymd = ymd.replaceFirst("-","年");
		ymd = ymd.replaceFirst("-","月");
		ymd = ymd.replaceFirst("-","日");
		ymd = ymd+"时";
		resultMap.put("dateTime", ymd);
		return resultMap;
	}

}
