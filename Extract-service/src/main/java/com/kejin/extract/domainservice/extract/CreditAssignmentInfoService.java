package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DCreditAssigmentModel;

public interface CreditAssignmentInfoService {

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.extract.impl.ChargeInfoService#readFromPayment(java.util.Date, java.util.Date)
	 */
	public abstract List<DCreditAssigmentModel> readFromCreditAssignment(
			Date beginTime, Date endTime,int continueRead);

}