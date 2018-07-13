package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DLoanModel;

public interface LoanInfoService {

	public abstract List<DLoanModel> readFromLoanCreate(Date beginTime,Date endTime,int continueRead);
	
	public abstract List<DLoanModel> readFromLoanModified(Date beginTime,Date endTime,int continueRead);

}