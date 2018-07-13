package com.kejin.extract.mmmoney.service.dao;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TradeRealTimeDataDao {
	//获取平台实时交易数据
	public Map<String,Object> getTradeRealTimeData(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("monthBeginTime") Date monthBeginTime, @Param("monthEndTime") Date monthEndTime);
}
