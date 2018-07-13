package com.kejin.extract.domainservice.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.common.TimeHandleService;
import com.kejin.extract.domainservice.service.DayDetailInfoService;
import com.kejin.extract.entity.service.DayCashDetailInfoModel;
import com.kejin.extract.entity.service.DayCashSummaryInfoModel;
import com.kejin.extract.entity.service.DayChargeDetailInfoModel;
import com.kejin.extract.entity.service.DayChargeSummaryInfoModel;
import com.kejin.extract.entity.service.DayInvestDetailInfoModel;
import com.kejin.extract.entity.service.DayInvestSummaryInfoModel;
import com.kejin.extract.entity.service.util.TimeInterval;
import com.kejin.extract.kejin.service.dao.DayDetailInfoDao;

@Service("dayDetailInfoService")
public class DayDetailInfoServiceImpl implements DayDetailInfoService   {
	@Autowired
	private DayDetailInfoDao dayDetailInfoDao;
	
    /**
     * 获取提现的日总数据
     */
	@Override
	public String getDayCashInfoSummary(Date begin,Date end){
		
		if(begin==null||end==null){
			TimeInterval interval = TimeHandleService.generateTodayTimeInterval();
			begin = interval.getBeginTime();
			end =  interval.getEndTime();
		}
		
		DayCashSummaryInfoModel  summary = dayDetailInfoDao.getDayCashInfoSummary(begin, end);
		
		String s = JSON.toJSONString(summary);
		
		return s;
	}
	

	/**
	 *获取日数据的详细记录
	 */
    @Override
	public String getDayCashInfoDetail(Date begin,Date end,int page){
    	
		if(begin==null||end==null){
			TimeInterval interval = TimeHandleService.generateTodayTimeInterval();
			begin = interval.getBeginTime();
			end =  interval.getEndTime();
		}
    	
		PageHelper.startPage(page, 50);
    	List<DayCashDetailInfoModel>  details = dayDetailInfoDao
    			.getDayCashInfoDetail(begin, end);
    	
    	String s = JSON.toJSONString(details);
    	
    	return s;
    }
    
    
    
    @Override
	public String getDayInvsetInfoSummay(Date begin,Date end){
    	
		if(begin==null||end==null){
			TimeInterval interval = TimeHandleService.generateTodayTimeInterval();
			begin = interval.getBeginTime();
			end =  interval.getEndTime();
		}
    	
    	DayInvestSummaryInfoModel  details = dayDetailInfoDao.getDayInvestInfoSummary(begin, end);
    	
    	String s = JSON.toJSONString(details);
    	
    	return s;
    	
    }
    
	@Override
	public String getDayInvestInfoDetail(Date begin,Date end,int page){
    	
		if(begin==null||end==null){
			TimeInterval interval = TimeHandleService.generateTodayTimeInterval();
			begin = interval.getBeginTime();
			end =  interval.getEndTime();
		}
    	
		PageHelper.startPage(page, 50);
    	List<DayInvestDetailInfoModel>  details = dayDetailInfoDao
    			.getDayInvestInfoDetail(begin, end);
    	
    	String s = JSON.toJSONString(details);
    	
    	return s;
    }
    
	
    @Override
	public String getDayChargeInfoSummay(Date begin,Date end){
    	
		if(begin==null||end==null){
			TimeInterval interval = TimeHandleService.generateTodayTimeInterval();
			begin = interval.getBeginTime();
			end =  interval.getEndTime();
		}
    	
    	DayChargeSummaryInfoModel  details = dayDetailInfoDao.getDayChargeInfoSummary(begin, end);
    	
    	String s = JSON.toJSONString(details);
    	
    	return s;
    	
    }
    
	@Override
	public String getDayChargeInfoDetail(Date begin,Date end,int page){
    	
		if(begin==null||end==null){
			TimeInterval interval = TimeHandleService.generateTodayTimeInterval();
			begin = interval.getBeginTime();
			end =  interval.getEndTime();
		}
    	
		PageHelper.startPage(page, 50);
    	List<DayChargeDetailInfoModel>  details = dayDetailInfoDao
    			.getDayChargeInfoDetail(begin, end);
    	
    	String s = JSON.toJSONString(details);
    	
    	return s;
    }
    
    



}
