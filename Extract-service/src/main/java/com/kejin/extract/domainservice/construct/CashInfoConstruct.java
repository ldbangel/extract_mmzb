package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface CashInfoConstruct {

	public abstract int constructCash(Date recordBeginDatetime,
			Date recordEndDatetime);

}