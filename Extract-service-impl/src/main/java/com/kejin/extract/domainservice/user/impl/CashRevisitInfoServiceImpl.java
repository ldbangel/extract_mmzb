package com.kejin.extract.domainservice.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.user.CashRevisitInfoService;
import com.kejin.extract.entity.kejinTest.DCashRevisitModel;
import com.kejin.extract.kejin.process.dao.DCashRevisitDao;

@Service("cashRevisitInfoService")
public class CashRevisitInfoServiceImpl implements CashRevisitInfoService {
	@Autowired
	private DCashRevisitDao dCashRevisitDao;

	@Override
	public int saveRevisitNote(List<DCashRevisitModel> modelList) {
		int result = dCashRevisitDao.insertList(modelList);
		return result;
	}

}
