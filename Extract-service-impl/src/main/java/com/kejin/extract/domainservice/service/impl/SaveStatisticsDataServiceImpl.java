package com.kejin.extract.domainservice.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kejin.extract.domainservice.service.OperationInfoService;
import com.kejin.extract.domainservice.service.SaveStatisticsDataService;
import com.kejin.extract.entity.kejinTest.DOperationCollectModel;
import com.kejin.extract.entity.service.FundFlowInfoModel;
import com.kejin.extract.entity.service.IncreasedInfoModel;
import com.kejin.extract.entity.service.InvestInfoModel;
import com.kejin.extract.entity.service.TradeInfoModel;
import com.kejin.extract.kejin.process.dao.DOperationCollectDao;
import com.kejin.extract.kejin.service.dao.OperationInfoDao;
import com.kejin.extract.mmmoney.service.dao.OperationInfoDao2;

@Service("saveStatisticsDataService")
public class SaveStatisticsDataServiceImpl implements SaveStatisticsDataService {
	@Resource(name = "increasedInfoService")
    private OperationInfoService increasedInfoService;
	@Resource(name = "fundFlowInfoService")
    private OperationInfoService fundFlowInfoService;
	@Resource(name = "investInfoService")
    private OperationInfoService investInfoService;
	@Resource(name = "tradeInfoService")
    private OperationInfoService tradeInfoService;
	@Resource(name = "simpleReportInfoService")
    private OperationInfoService simpleReportInfoService;
	@Resource(name="operationInfoDao")
	private OperationInfoDao operationInfoDao;
	@Resource(name="operationInfoDao2")
	private OperationInfoDao2 operationInfoDao2;
	@Resource(name="dOperationCollectDao")
	private DOperationCollectDao dOperationCollectDao;

	@Override
	public String saveYesterdayOperationInfo() {
		DOperationCollectModel operationModel = collectYesterdayData();
		
		dOperationCollectDao.insertOperationCollectRecord(operationModel);
		
		return null;
	}
	
	private DOperationCollectModel collectYesterdayData(){
		DOperationCollectModel operationModel = new DOperationCollectModel();
		Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(today.getTime());
		yesterday.set(Calendar.DATE, yesterday.get(Calendar.DATE) - 1);
		Date beginTime = yesterday.getTime();	
		
		//获取昨日用户数据
		String yesterdayIncreasedData = increasedInfoService.getInfoOfDay(beginTime, null);
		JSONObject increasedObject = JSON.parseObject(yesterdayIncreasedData);
		if(increasedObject.get("day") != null){
			JSONObject jsonObject =  (JSONObject) increasedObject.get("day");
			IncreasedInfoModel model = JSON.toJavaObject(jsonObject, IncreasedInfoModel.class);
			operationModel.setNewRegister(model.getRegisterNum());
			operationModel.setNewTieCard(model.getTieCardNum());
			operationModel.setNewInvest(model.getFirstInvestNum());
		}
		//获取昨日资金流数据
		String yesterdayFundflowData = fundFlowInfoService.getInfoOfDay(beginTime, null);
		JSONObject fundflowObject = JSON.parseObject(yesterdayFundflowData);
		if(fundflowObject.get("day") != null){
			JSONObject jsonObject =  (JSONObject) fundflowObject.get("day");
			FundFlowInfoModel model = JSON.toJavaObject(jsonObject, FundFlowInfoModel.class);
			operationModel.setChargeAmount(model.getChargeAmount());
			operationModel.setChargeNum(model.getChargeNum());
			operationModel.setCashAmount(model.getCashAmount());
			operationModel.setCashNum(model.getCashNum());
		}
		//获取昨日投资数据
		String yesterdayInvestData = investInfoService.getInfoOfDay(beginTime, null);
		JSONObject investObject = JSON.parseObject(yesterdayInvestData);
		if(investObject.get("day") != null){
			JSONObject jsonObject =  (JSONObject) investObject.get("day");
			InvestInfoModel model = JSON.toJavaObject(jsonObject, InvestInfoModel.class);
			operationModel.setOneMonthAmount(model.getOneMonthAmount());
			operationModel.setTwoMonthAmount(model.getTwoMonthAmount());
			operationModel.setThreeMonthAmount(model.getThreeMonthAmount());
			operationModel.setSixMonthAmount(model.getSixMonthAmount());
			operationModel.setTwelveMonthAmount(model.getTwelveMonthAmount());
			operationModel.setOtherRegularAmount(model.getOthersAmount());
			operationModel.setOneMonthNum(model.getOneMonthNum());
			operationModel.setTwoMonthNum(model.getTwoMonthNum());
			operationModel.setThreeMonthNum(model.getThreeMonthNum());
			operationModel.setSixMonthNum(model.getSixMonthNum());
			operationModel.setTwelveMonthNum(model.getTwelveMonthNum());
			operationModel.setOtherRegularNum(model.getOthersNum());
		}
		//获取昨日交易数据
		String yesterdayTradeData = tradeInfoService.getInfoOfDay(beginTime, null);
		JSONObject tradeObject = JSON.parseObject(yesterdayTradeData);
		if(tradeObject.get("day") != null){
			JSONObject jsonObject =  (JSONObject) tradeObject.get("day");
			TradeInfoModel model = JSON.toJavaObject(jsonObject, TradeInfoModel.class);
			operationModel.setTotalRegularAmount(model.getInvestRegularAmount());
			operationModel.setTotalRegularNum(model.getInvestRegularNum());
			operationModel.setRegularRecoveryAmount(model.getTransferRegularAmount());
			operationModel.setRegularRecoveryNum(model.getTransferRegularNum());
		}
		//获取昨日简报数据
		Map<String,Object> simpleInfo = this.getYesterdaySimpleData();
		if(simpleInfo!=null && simpleInfo.size()>0){
			operationModel.setRegularAsset((BigDecimal) simpleInfo.get("regularLoanBalance"));
			operationModel.setRegularDailyGrowth((BigDecimal) simpleInfo.get("regularDailyGrowth"));
			operationModel.setRegularDailyRail((BigDecimal) simpleInfo.get("regularDailyRail"));
			operationModel.setMonthRegularAmount((BigDecimal) simpleInfo.get("regularMonthAmount"));
			if(simpleInfo.get("regularNewAmount") != null){
				operationModel.setNewRegularAmount((BigDecimal) simpleInfo.get("regularNewAmount"));
			}else{
				operationModel.setNewRegularAmount(new BigDecimal(0));
			}
			if(simpleInfo.get("regularReinvestAmount") != null){
				operationModel.setReinvestRegularAmount((BigDecimal) simpleInfo.get("regularReinvestAmount"));
			}else{
				operationModel.setReinvestRegularAmount(new BigDecimal(0));
			}
			if(((BigDecimal) simpleInfo.get("chargeAmount")).compareTo(new BigDecimal(0)) == 0){
				operationModel.setChargeAmount(new BigDecimal(0));
			}else{
				operationModel.setChargeAmount((BigDecimal) simpleInfo.get("chargeAmount"));
			}
			if(((BigDecimal) simpleInfo.get("cashAmount")).compareTo(new BigDecimal(0)) == 0){
				operationModel.setCashAmount(new BigDecimal(0));
			}else{
				operationModel.setCashAmount((BigDecimal) simpleInfo.get("cashAmount"));
			}
			operationModel.setMonthRecoveryAmount((BigDecimal) simpleInfo.get("monthRecoveryAmount"));
			if(simpleInfo.get("monthWaitRecoveryAmount")!=null){
				operationModel.setMonthUnrecoveryAmount((BigDecimal) simpleInfo.get("monthWaitRecoveryAmount"));
			}else{
				operationModel.setMonthUnrecoveryAmount(new BigDecimal(0));
			}
			operationModel.setReinvestRate((BigDecimal) simpleInfo.get("reinvestRate"));
			operationModel.setActiveNum(((Number) simpleInfo.get("activeNum")).intValue());
			if(simpleInfo.get("tradeNum") != null){
				operationModel.setRegularInvestNum(((Number) simpleInfo.get("tradeNum")).intValue());
			}else{
				operationModel.setRegularInvestNum(0);
			}
		}
		
		return operationModel;
	}
	
	
	private Map<String,Object> getYesterdaySimpleData(){
		Calendar dayBegin = Calendar.getInstance();
		dayBegin.set(Calendar.DATE,dayBegin.get(Calendar.DATE)-1);
	
		dayBegin.set(dayBegin.get(Calendar.YEAR),
				dayBegin.get(Calendar.MONTH),
				dayBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar dayEnd = Calendar.getInstance();
		dayEnd.setTime(dayBegin.getTime());			
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
		
		//数据处理
		BigDecimal reinvestRate = ((BigDecimal) info.get("monthRegularReinvestAmount")).divide((BigDecimal) info.get("monthRegularRecoveryAmount"), 6, BigDecimal.ROUND_HALF_UP);
		info.put("reinvestRate", reinvestRate);
		return info;
	}
	
}
