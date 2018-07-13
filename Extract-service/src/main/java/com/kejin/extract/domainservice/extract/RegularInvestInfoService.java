package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DRegularInvestModel;

public interface RegularInvestInfoService {
	//读取新建数据
	public abstract List<DRegularInvestModel> readCreateFromBidOrder(Date beginTime,
			Date endTime,int continueRead);
	
	//读取更新数据
	public abstract List<DRegularInvestModel> readModifiedFromBidOrder(Date beginTime,
			Date endTime,int continueRead);

}