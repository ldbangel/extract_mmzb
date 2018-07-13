package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface CurrentRecoveryInfoConstruct {

	public abstract int constructRegularRecovery(Date recordBeginDatetime,
			Date recordEndDatetime);

}