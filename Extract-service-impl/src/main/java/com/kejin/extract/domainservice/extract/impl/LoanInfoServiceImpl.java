package com.kejin.extract.domainservice.extract.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.extract.LoanInfoService;
import com.kejin.extract.entity.kejinTest.DLoanModel;
import com.kejin.extract.mmmoney.reader.dao.DLoanReaderDao;

@Service("loanInfoService")
public class LoanInfoServiceImpl implements LoanInfoService {
	
	@Autowired
	private DLoanReaderDao dLoanReaderDao;

	@Override
	public List<DLoanModel> readFromLoanCreate(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		
		PageHelper.startPage(continueRead, pageSize);
		
		List<DLoanModel> listModel = dLoanReaderDao
				.readFromLoanCreate(beginTime, endTime);
		return listModel;
	}

	@Override
	public List<DLoanModel> readFromLoanModified(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		
		PageHelper.startPage(continueRead, pageSize);
		
		List<DLoanModel> listModel = dLoanReaderDao
				.readFromLoanModified(beginTime, endTime);
		return listModel;
	}

}
