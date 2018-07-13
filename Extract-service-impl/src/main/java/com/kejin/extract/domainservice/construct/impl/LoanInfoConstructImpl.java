package com.kejin.extract.domainservice.construct.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.construct.LoanInfoConstruct;
import com.kejin.extract.domainservice.extract.LoanInfoService;
import com.kejin.extract.entity.kejinTest.DLoanModel;
import com.kejin.extract.kejin.process.dao.DLoanDao;

@Service("loanInfoConstruct")
public class LoanInfoConstructImpl implements LoanInfoConstruct {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DLoanDao dLoanDao;
	@Resource(name="loanInfoService")
	private LoanInfoService loanInfoService;

	@Override
	public int constructLoan(Date recordBeginDatetime, Date recordEndDatetime) {
		int continueRead1 = 1;
		int continueRead2 = 1;
		int handlerCount = 0;
		while(continueRead1 >= 0){
			List<DLoanModel> listLoans1 = loanInfoService
					.readFromLoanCreate(recordBeginDatetime, recordEndDatetime, continueRead1);
			
			if(listLoans1 != null && listLoans1.size() > 0){
				int result = dLoanDao.insert(listLoans1);
				logger.info(new Date()+"----insert into d_loan count : " + result);
				handlerCount =handlerCount + listLoans1.size();
				continueRead1++;
			}else{
				continueRead1 = -1;
			}
		}
		while(continueRead2 >= 0){
			List<DLoanModel> listLoans2 = loanInfoService
					.readFromLoanModified(recordBeginDatetime, recordEndDatetime, continueRead2);
			
			if(listLoans2 != null && listLoans2.size() > 0){
				int result = dLoanDao.update(listLoans2);
				logger.info(new Date()+"----update d_loan count : " + result);
				handlerCount =handlerCount + listLoans2.size();
				continueRead2++;
			}else{
				continueRead2 = -1;
			}
		}
		return handlerCount;
	}

}
