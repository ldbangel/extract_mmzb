package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DProductModel;

public interface ProductInfoService {

	public abstract List<DProductModel> readFromInvest(Date beginTime,
			Date endTime,int continueRead);

}