package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DCashModel;

public interface CashInfoService {

	public abstract List<DCashModel> readFromPayment(Date beginTime,Date endTime,int continueRead);

}