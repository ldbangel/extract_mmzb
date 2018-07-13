package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DBonusModel;

public interface BonusInfoService {

	public abstract List<DBonusModel> readFromDpm(Date beginTime,Date endTime,int continueRead);

}