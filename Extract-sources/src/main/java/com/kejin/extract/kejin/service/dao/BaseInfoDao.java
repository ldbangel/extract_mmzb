package com.kejin.extract.kejin.service.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.service.BaseInvestInfoModel;
import com.kejin.extract.entity.service.BaseUserInfoModel;

public interface BaseInfoDao {
	
	public List<BaseUserInfoModel> getBaseUserInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	
	public List<BaseInvestInfoModel> getBaseInvestInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

}
