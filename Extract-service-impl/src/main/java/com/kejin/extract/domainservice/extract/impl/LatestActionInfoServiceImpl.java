package com.kejin.extract.domainservice.extract.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.extract.LatestActionInfoService;
import com.kejin.extract.entity.kejinTest.DLatestActionModel;
import com.kejin.extract.mmmoney.reader.dao.DLatestActionReaderDao;

@Service("latestActionInfoService")
public class LatestActionInfoServiceImpl implements LatestActionInfoService {
	
	@Autowired
	private DLatestActionReaderDao dLatestActionReaderDao;

	@Override
	public List<DLatestActionModel> readLatestInvestInfo(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		int offset = 0 + continueRead*pageSize;
		List<DLatestActionModel> investModels = dLatestActionReaderDao.readLatestInvest(beginTime, endTime, offset, pageSize);
		return investModels;
	}

	@Override
	public List<DLatestActionModel> readLatestRecoveryInfo(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		int offset = 0 + continueRead*pageSize;
		List<DLatestActionModel> recoveryModels = dLatestActionReaderDao.readLatestRecovery(beginTime, endTime, offset, pageSize);
		return recoveryModels;
	}

	@Override
	public List<DLatestActionModel> readLatestChargeInfo(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		int offset = 0 + continueRead*pageSize;
		List<DLatestActionModel> chargeModels = dLatestActionReaderDao.readLatestCharge(beginTime, endTime, offset, pageSize);
		return chargeModels;
	}

	@Override
	public List<DLatestActionModel> readLatestCashInfo(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		int offset = 0 + continueRead*pageSize;
		List<DLatestActionModel> cashModels = dLatestActionReaderDao.readLatestCash(beginTime, endTime, offset, pageSize);
		return cashModels;
	}

}
