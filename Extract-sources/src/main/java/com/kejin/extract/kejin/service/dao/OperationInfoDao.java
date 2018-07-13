package com.kejin.extract.kejin.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.service.FundFlowInfoModel;
import com.kejin.extract.entity.service.IncreasedInfoModel;
import com.kejin.extract.entity.service.InvestInfoModel;
import com.kejin.extract.entity.service.TradeInfoModel;

public interface OperationInfoDao {

	//获取新增信息
	public IncreasedInfoModel getIncreasedInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	//获取资金流信息
	public FundFlowInfoModel getFundFlowInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	//获取交易信息
	public TradeInfoModel getTradeInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	//获取投资信息
	public InvestInfoModel getInvestInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	//获取简报信息
	public Map<String,Object> getSimpleReportInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("monthBeginTime") Date monthBeginTime, @Param("monthEndTime") Date monthEndTime);
	
	//获取前一天投资人数(分ios、Android、H5、PC)
	public List<Map<String,Object>> getInvestNumPlatformInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	//获取前一天首投人数和首投总金额(分ios、Android、H5、PC)
	public List<Map<String,Object>> getFirstInvestPlatformInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
}
