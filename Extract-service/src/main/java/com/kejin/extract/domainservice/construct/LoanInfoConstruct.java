package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface LoanInfoConstruct {

	public abstract int constructLoan(Date recordBeginDatetime,
			Date recordEndDatetime);

}