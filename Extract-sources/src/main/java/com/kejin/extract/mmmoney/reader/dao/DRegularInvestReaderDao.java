package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.service.BaseInvestInfoModel;

public interface DRegularInvestReaderDao {
	
	public List<Map<String, Object>> readCreateFromBidOrder(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<Map<String, Object>> readModifiedFromBidOrder(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<BaseInvestInfoModel> getBaseInvestInfo(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

}
