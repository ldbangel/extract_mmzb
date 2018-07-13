package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DChargeModel;

public interface ChargeInfoService {

	public abstract List<DChargeModel> readFromPayment(Date beginTime,
			Date endTime,int continueRead);

	public abstract List<DChargeModel> readFromCounter(Date beginTime,
			Date endTime,int continueRead);

}