package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DCurrentInvetModel;

public interface CurrentInvestInfoService {

	public abstract List<DCurrentInvetModel> readFromBidOrder(Date beginTime,
			Date endTime,int continueRead);

}