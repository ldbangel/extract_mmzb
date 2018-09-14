package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface LatestActionInfoConstruct {

	public abstract int constructLatestAction(Date recordBeginDatetime,Date recordEndDatetime);

}