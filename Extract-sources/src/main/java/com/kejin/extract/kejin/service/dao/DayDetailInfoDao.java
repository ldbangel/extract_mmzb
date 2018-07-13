package com.kejin.extract.kejin.service.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.service.DayCashDetailInfoModel;
import com.kejin.extract.entity.service.DayCashSummaryInfoModel;
import com.kejin.extract.entity.service.DayChargeDetailInfoModel;
import com.kejin.extract.entity.service.DayChargeSummaryInfoModel;
import com.kejin.extract.entity.service.DayInvestDetailInfoModel;
import com.kejin.extract.entity.service.DayInvestSummaryInfoModel;



public interface DayDetailInfoDao {
	
	public DayCashSummaryInfoModel getDayCashInfoSummary(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<DayCashDetailInfoModel> getDayCashInfoDetail(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public DayInvestSummaryInfoModel  getDayInvestInfoSummary(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<DayInvestDetailInfoModel> getDayInvestInfoDetail(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public DayChargeSummaryInfoModel  getDayChargeInfoSummary(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<DayChargeDetailInfoModel> getDayChargeInfoDetail(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
}
