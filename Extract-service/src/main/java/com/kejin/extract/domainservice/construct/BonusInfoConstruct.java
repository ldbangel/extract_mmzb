package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface BonusInfoConstruct {

	public abstract int constructBonus(Date recordBeginDatetime,
			Date recordEndDatetime);

}