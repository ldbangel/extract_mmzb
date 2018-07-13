package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DCurrentRecoveryModel;

public interface CurrentRecoveryInfoService {

	public abstract List<DCurrentRecoveryModel> readFromRecovry(Date beginTime,
			Date endTime, int continueRead);

}