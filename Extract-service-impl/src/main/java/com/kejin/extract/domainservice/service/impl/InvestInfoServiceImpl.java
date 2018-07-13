package com.kejin.extract.domainservice.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.OperationInfoService;
import com.kejin.extract.entity.service.InvestInfoModel;
import com.kejin.extract.kejin.service.dao.OperationInfoDao;


@Service("investInfoService")
public class InvestInfoServiceImpl extends AbtractInfoService implements OperationInfoService  {
	@Autowired
	private OperationInfoDao operationInfoDao;

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.OperationInfoService#getIncreasedInfoOfDay(java.util.Date, java.util.Date)
	 */
	@Override
	public String getInfoOfDay(Date begin,Date end){		
		try{
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			InvestInfoModel info = (InvestInfoModel)getDayIntervalInfoData(method,begin,end);
		    Map<String,InvestInfoModel> data = new HashMap<String,InvestInfoModel>();
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
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			InvestInfoModel info = (InvestInfoModel)getWeekInfoData(method,originTime);
		    Map<String,InvestInfoModel> data = new HashMap<String,InvestInfoModel>();
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
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			InvestInfoModel info = (InvestInfoModel)getMonthInfoData(method,originTime);
		    Map<String,InvestInfoModel> data = new HashMap<String,InvestInfoModel>();
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
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			InvestInfoModel info = (InvestInfoModel)getYearInfoData(method,originTime);
		    Map<String,InvestInfoModel> data = new HashMap<String,InvestInfoModel>();
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
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			InvestInfoModel info = (InvestInfoModel)getAllInfoData(method);
		    Map<String,InvestInfoModel> data = new HashMap<String,InvestInfoModel>();
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
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo", Date.class, Date.class);
			Map<String,InvestInfoModel> info =getDayDetail(method,begin,end);
		    Map<String,Map<String,InvestInfoModel>> data = new HashMap<String,Map<String,InvestInfoModel>>();
		    data.put("dayDetail", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}
	
	//获取定期宝前四周每周的数据
	@Override
	public String getInfoOfWeekDetail(Date begin, Date end) {
		try{
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			Map<String,InvestInfoModel> info = getWeekDetail(method, begin, end);
			Map<String,Map<String,InvestInfoModel>> data = new HashMap<String,Map<String,InvestInfoModel>>();
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
			Method method = operationInfoDao.getClass().getMethod("getInvestInfo",Date.class,Date.class);
			Map<String,InvestInfoModel> info =getMonthDetail(method,originTime);
		    Map<String,Map<String,InvestInfoModel>> data = new HashMap<String,Map<String,InvestInfoModel>>();
		    data.put("monthDetail", info);
			return JSON.toJSONString(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;	
	}

	
}
