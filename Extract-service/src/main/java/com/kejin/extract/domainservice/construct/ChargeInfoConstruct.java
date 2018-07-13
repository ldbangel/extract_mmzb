package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface ChargeInfoConstruct {

	public abstract int constructCharge(Date recordBeginDatetime,
			Date recordEndDatetime);

}