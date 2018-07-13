package com.kejin.extract.domainservice.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.OperationInfoService;
import com.kejin.extract.kejin.service.dao.OperationInfoDao;
import com.kejin.extract.mmmoney.service.dao.OperationInfoDao2;


@Service("simpleReportInfoService")
public class SimpleReportInfoServiceImpl implements OperationInfoService {
	
	@Autowired
	private OperationInfoDao operationInfoDao;
	@Autowired
	private OperationInfoDao2 operationInfoDao2;

	@Override
	public String getInfoOfDay(Date begin, Date end) {
		Calendar dayBegin = Calendar.getInstance();
		if(begin != null){
			dayBegin.setTime(begin);
		}else{
			dayBegin.setTime(new Date());
		}	
	
		dayBegin.set(dayBegin.get(Calendar.YEAR),
				dayBegin.get(Calendar.MONTH),
				dayBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar dayEnd = Calendar.getInstance();
		if(end != null){
			dayEnd.setTime(end);
			dayEnd.set(Calendar.DATE, dayEnd.get(Calendar.DATE)-1);
		}else{
			dayEnd.setTime(dayBegin.getTime());			
		}
		dayEnd.set(dayEnd.get(Calendar.YEAR),
				dayEnd.get(Calendar.MONTH),
				dayEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		Calendar thisMonthBegin = Calendar.getInstance();
		thisMonthBegin.setTime(dayEnd.getTime());
		thisMonthBegin.set(Calendar.DAY_OF_MONTH,1);
		
		thisMonthBegin.set(thisMonthBegin.get(Calendar.YEAR),
				thisMonthBegin.get(Calendar.MONTH),
				thisMonthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar thisMonthEnd = Calendar.getInstance();
		thisMonthEnd.setTime(thisMonthBegin.getTime());
		thisMonthEnd.set(Calendar.MONTH, thisMonthEnd.get(Calendar.MONTH) + 1);
		thisMonthEnd.set(Calendar.DATE, thisMonthEnd.get(Calendar.DATE)-1);
		thisMonthEnd.set(thisMonthEnd.get(Calendar.YEAR),
				thisMonthEnd.get(Calendar.MONTH),
				thisMonthEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		Map<String,Object> info = operationInfoDao.getSimpleReportInfo(dayBegin.getTime(), dayEnd.getTime(), thisMonthBegin.getTime(), thisMonthEnd.getTime());
		Map<String,Object> info2 = operationInfoDao2.getSimpleReportInfoFromProd(dayBegin.getTime(), dayEnd.getTime(), thisMonthBegin.getTime(), thisMonthEnd.getTime());
		for (String key : info2.keySet()) {
			info.put(key, info2.get(key));
		} 
		transferData(info);
		
		//图片标题日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ymd = sdf.format(begin);
		ymd = ymd.replaceFirst("-","年");
		ymd = ymd.replaceFirst("-","月");
		ymd = ymd+"日";
		info.put("titleDate", ymd);
		
	    Map<String,Map<String,Object>> data = new HashMap<String,Map<String,Object>>();
	    data.put("day", info);
		return JSON.toJSONString(data);
	}

	@Override
	public String getInfoOfWeek(Date originTime) {
		Calendar weekBegin = Calendar.getInstance();
		Date beginDate = null;
		if(originTime!=null){
			beginDate = originTime;
		}else{
			beginDate = new Date();
		}	
		
		weekBegin.setTime(beginDate);
		weekBegin.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		weekBegin.set(weekBegin.get(Calendar.YEAR),
				weekBegin.get(Calendar.MONTH),
				weekBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar weekEnd = Calendar.getInstance();
		weekEnd.setTime(weekBegin.getTime());
		weekEnd.set(Calendar.DATE, weekEnd.get(Calendar.DATE)+6);
		weekEnd.set(weekEnd.get(Calendar.YEAR),
				weekEnd.get(Calendar.MONTH),
				weekEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		
		Calendar thisMonthBegin = Calendar.getInstance();
		thisMonthBegin.setTime(originTime);
		thisMonthBegin.set(Calendar.DAY_OF_MONTH,1);
		
		thisMonthBegin.set(thisMonthBegin.get(Calendar.YEAR),
				thisMonthBegin.get(Calendar.MONTH),
				thisMonthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar thisMonthEnd = Calendar.getInstance();
		thisMonthEnd.setTime(thisMonthBegin.getTime());
		thisMonthEnd.set(Calendar.MONTH, thisMonthEnd.get(Calendar.MONTH) + 1);
		thisMonthEnd.set(Calendar.DATE, thisMonthEnd.get(Calendar.DATE)-1);
		thisMonthEnd.set(thisMonthEnd.get(Calendar.YEAR),
				thisMonthEnd.get(Calendar.MONTH),
				thisMonthEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		Map<String,Object> info = operationInfoDao.getSimpleReportInfo(weekBegin.getTime(), weekEnd.getTime(), thisMonthBegin.getTime(), thisMonthEnd.getTime());
		Map<String,Object> info2 = operationInfoDao2.getSimpleReportInfoFromProd(weekBegin.getTime(), weekEnd.getTime(), thisMonthBegin.getTime(), thisMonthEnd.getTime());
		for (String key : info2.keySet()) {
			info.put(key, info2.get(key));
		}
		
		transferData(info);
		
		//图片标题日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ymd1 = sdf.format(weekBegin.getTime());
		ymd1 = ymd1.replaceFirst("-","年");
		ymd1 = ymd1.replaceFirst("-","月");
		ymd1 = ymd1+"日";
		String ymd2 = sdf.format(weekEnd.getTime());
		ymd2 = ymd2.replaceFirst("-","年");
		ymd2 = ymd2.replaceFirst("-","月");
		ymd2 = ymd2+"日";
		info.put("titleDate", ymd1+"-"+ymd2);
		
	    Map<String,Map<String,Object>> data = new HashMap<String,Map<String,Object>>();
	    data.put("week", info);
		return JSON.toJSONString(data);
		
	}

	@Override
	public String getInfoOfMonth(Date originTime) {
		Calendar monthBegin = Calendar.getInstance();
		//Calendar monthBegin = new GregorianCalendar(2018, 02, 01, 00, 10, 00);
		monthBegin.set(Calendar.DAY_OF_MONTH,1);
		monthBegin.set(Calendar.MONTH, monthBegin.get(Calendar.MONTH)-1);
		monthBegin.set(monthBegin.get(Calendar.YEAR),
				monthBegin.get(Calendar.MONTH),
				monthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.setTime(monthBegin.getTime());
		monthEnd.set(Calendar.MONTH, monthBegin.get(Calendar.MONTH)+1);
		monthEnd.set(Calendar.DATE,monthEnd.get(Calendar.DATE)-1);
		monthEnd.set(monthEnd.get(Calendar.YEAR),
				monthEnd.get(Calendar.MONTH),
				monthEnd.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		Map<String,Object> info = operationInfoDao.getSimpleReportInfo(monthBegin.getTime(), monthEnd.getTime(), monthBegin.getTime(), monthEnd.getTime());
		Map<String,Object> info2 = operationInfoDao2.getSimpleReportInfoFromProd(monthBegin.getTime(), monthEnd.getTime(), monthBegin.getTime(), monthEnd.getTime());
		for (String key : info2.keySet()) {
			info.put(key, info2.get(key));
		}
		
		transferData(info);
		
		//图片标题日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String ymd = sdf.format(monthBegin.getTime());
		ymd = ymd.replaceFirst("-","年");
		ymd = ymd+"月";
		info.put("titleDate", ymd);
		
	    Map<String,Map<String,Object>> data = new HashMap<String,Map<String,Object>>();
	    data.put("month", info);
		return JSON.toJSONString(data);
	}

	@Override
	public String getInfoOfYear(Date originTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfoOfAll(Date originTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfoOfDayDetail(Date begin, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfoOfWeekDetail(Date begin, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfoOfMonthDetail(Date originTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 数据转换格式
	 * @param info
	 */
	private void transferData(Map<String,Object> info){
		long historyConvert = ((Long) info.get("newInvestNum"))-((Long) info.get("convertOfInvestNum"));
		BigDecimal netInflowFund = ((BigDecimal) info.get("chargeAmount")).subtract((BigDecimal) info.get("cashAmount"));
		BigDecimal reinvestRate = ((BigDecimal) info.get("monthRegularReinvestAmount")).divide((BigDecimal) info.get("monthRegularRecoveryAmount"), 6, BigDecimal.ROUND_HALF_UP);
		info.put("historyConvert", historyConvert);
		info.put("netInflowFund", netInflowFund);
		info.put("reinvestRate", reinvestRate);
		//数据处理
		DecimalFormat format1 = new DecimalFormat("0.00%");
		DecimalFormat format2 = new DecimalFormat("#.00");
		DecimalFormat format3 = new DecimalFormat("#.0");
		//定期资产规模
		info.put("regularLoanBalance", format2.format(((BigDecimal) info.get("regularLoanBalance")).divide(new BigDecimal(100000000)))+"亿");
		//定期资产规模日涨跌额
		info.put("regularDailyGrowth", format3.format(((BigDecimal) info.get("regularDailyGrowth")).divide(new BigDecimal(10000)))+"万");
		//定期资产规模日涨跌幅
		info.put("regularDailyRail", format1.format((BigDecimal) info.get("regularDailyRail")));
		
		//本日定期宝投资额
		info.put("regularInvestAmount", format3.format(((BigDecimal) info.get("regularInvestAmount")).divide(new BigDecimal(10000)))+"万");
		//新增
		if(info.get("regularNewAmount") != null){
			info.put("regularNewAmount", format3.format(((BigDecimal) info.get("regularNewAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			info.put("regularNewAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		//复投
		info.put("regularReinvestAmount", format3.format(((BigDecimal) info.get("regularReinvestAmount")).divide(new BigDecimal(10000)))+"万");
		//本月定期宝投资额
		info.put("regularMonthAmount", format3.format(((BigDecimal) info.get("regularMonthAmount")).divide(new BigDecimal(10000)))+"万");
		
		//本日资金流入
		info.put("netInflowFund", format3.format(netInflowFund.divide(new BigDecimal(10000)))+"万");
		//本日充值金额
		info.put("chargeAmount", format3.format(((BigDecimal) info.get("chargeAmount")).divide(new BigDecimal(10000)))+"万");
		//本日提现金额
		if(((BigDecimal) info.get("cashAmount")).compareTo(new BigDecimal(0)) == 0){
			info.put("cashAmount",0);
		}else{
			info.put("cashAmount", format3.format(((BigDecimal) info.get("cashAmount")).divide(new BigDecimal(10000)))+"万");
		}
		
		//本日回款金额
		info.put("recoveryAmount", format3.format(((BigDecimal) info.get("recoveryAmount")).divide(new BigDecimal(10000)))+"万");
		//本月已回款
		info.put("monthRecoveryAmount", format3.format(((BigDecimal) info.get("monthRecoveryAmount")).divide(new BigDecimal(10000)))+"万");
		//本月待回款
		if(info.get("monthWaitRecoveryAmount") != null){
			info.put("monthWaitRecoveryAmount", format3.format(((BigDecimal) info.get("monthWaitRecoveryAmount")).divide(new BigDecimal(10000)))+"万");
		}else{
			info.put("monthWaitRecoveryAmount", format3.format((new BigDecimal(0)).divide(new BigDecimal(10000)))+"万");
		}
		//本月复投率
		info.put("reinvestRate", format1.format(reinvestRate));
	}
}
