package com.kejin.extract.mmmoney.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OperationInfoDao2 {

	//获取简报信息2
	public Map<String,Object> getSimpleReportInfoFromProd(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("thisMonthBeginTime") Date thisMonthBeginTime, @Param("thisMonthEndTime") Date thisMonthEndTime);
	
	//获取当日注册用户数(分iOS、Android、H5、PC)
	public List<Map<String,Object>> getRegisterPlatformInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
		
	//获取绑卡用户数(分IOS、Android、H5、PC)
	public List<Map<String,Object>> getTieCardPlatformInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	//获取前30天的复投率
	public List<Map<String,Object>> getRecoveryRateInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

}
