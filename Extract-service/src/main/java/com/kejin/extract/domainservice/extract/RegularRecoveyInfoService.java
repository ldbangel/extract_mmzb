package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DRegularRecoveryModel;

public interface RegularRecoveyInfoService {

	public abstract List<DRegularRecoveryModel> readCreateFromRecovery(Date beginTime,
			Date endTime,int continueRead);
	
	public abstract List<DRegularRecoveryModel> readModifiedFromRecovery(Date beginTime,
			Date endTime,int continueRead);

}