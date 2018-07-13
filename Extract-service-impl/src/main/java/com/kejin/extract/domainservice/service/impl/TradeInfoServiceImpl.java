package com.kejin.extract.domainservice.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.OperationInfoService;
import com.kejin.extract.entity.service.TradeInfoModel;
import com.kejin.extract.kejin.service.dao.OperationInfoDao;


@Service("tradeInfoService")
public class TradeInfoServiceImpl extends AbtractInfoService implements OperationInfoService  {
	
	@Autowired
	private OperationInfoDao operationInfoDao;
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfDay(java.util.Date, java.util.Date)
	 */
	@Override
	public String getInfoOfDay(Date begin,Date end){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			TradeInfoModel info = (TradeInfoModel)getDayIntervalInfoData(method,begin,end);
		    Map<String,TradeInfoModel> data = new HashMap<String,TradeInfoModel>();
		    data.put("day", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfWeek(java.util.Date)
	 */
	@Override
	public String getInfoOfWeek(Date originTime){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			TradeInfoModel info = (TradeInfoModel)getWeekInfoData(method,originTime);
		    Map<String,TradeInfoModel> data = new HashMap<String,TradeInfoModel>();
		    data.put("week", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfMonth(java.util.Date)
	 */
	@Override
	public String getInfoOfMonth(Date originTime){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			TradeInfoModel info = (TradeInfoModel)getMonthInfoData(method,originTime);
		    Map<String,TradeInfoModel> data = new HashMap<String,TradeInfoModel>();
		    data.put("month", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfYear(java.util.Date)
	 */
	@Override
	public String getInfoOfYear(Date originTime){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			TradeInfoModel info = (TradeInfoModel)getYearInfoData(method,originTime);
		    Map<String,TradeInfoModel> data = new HashMap<String,TradeInfoModel>();
		    data.put("year", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfAll(java.util.Date)
	 */
	@Override
	public String getInfoOfAll(Date originTime){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			TradeInfoModel info = (TradeInfoModel)getAllInfoData(method);
		    Map<String,TradeInfoModel> data = new HashMap<String,TradeInfoModel>();
		    data.put("all", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfDayDetail(java.util.Date, java.util.Date)
	 */
	@Override
	public String getInfoOfDayDetail(Date begin,Date end){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			Map<String,TradeInfoModel> info =getDayDetail(method,begin,end);
		    Map<String,Map<String,TradeInfoModel>> data = new HashMap<String,Map<String,TradeInfoModel>>();
		    data.put("dayDetail", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	
	@Override
	//定期宝前四周每周的数据
	public String getInfoOfWeekDetail(Date begin, Date end) {
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			Map<String,TradeInfoModel> info = getWeekDetail(method, begin, end);
			Map<String,Map<String,TradeInfoModel>> data = new HashMap<String,Map<String,TradeInfoModel>>();
			data.put("weekDetail", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfMonthDetail(java.util.Date)
	 */
	@Override
	public String getInfoOfMonthDetail(Date originTime){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getTradeInfo",Date.class,Date.class);
			Map<String,TradeInfoModel> info =getMonthDetail(method,originTime);
		    Map<String,Map<String,TradeInfoModel>> data = new HashMap<String,Map<String,TradeInfoModel>>();
		    data.put("monthDetail", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}

	
}
