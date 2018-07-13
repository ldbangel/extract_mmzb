package com.kejin.extract.entity.service.util;

import java.math.BigDecimal;
import java.util.Date;

public class UserCredit  implements Comparable {
	
	public String subjectNo;
	
	public String subjectName;	
	
	public String term;
	
	
	public BigDecimal originInvestAmout = new BigDecimal(0);
	
	
	public BigDecimal inCredit= new BigDecimal(0);
	
	public BigDecimal outCredit= new BigDecimal(0);
		
	public Date investDate = null;
	
	public BigDecimal creditAmout= new BigDecimal(0);
	
	public BigDecimal recoveryInterest= new BigDecimal(0);
	
	public BigDecimal unpaidInterest= new BigDecimal(0);
	
	public String status;

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public BigDecimal getOriginInvestAmout() {
		return originInvestAmout;
	}

	public void setOriginInvestAmout(BigDecimal originInvestAmout) {
		this.originInvestAmout = originInvestAmout;
	}

	public BigDecimal getInCredit() {
		return inCredit;
	}

	public void setInCredit(BigDecimal inCredit) {
		this.inCredit = inCredit;
	}

	public BigDecimal getOutCredit() {
		return outCredit;
	}

	public void setOutCredit(BigDecimal outCredit) {
		this.outCredit = outCredit;
	}

	public BigDecimal getCreditAmout() {
		return creditAmout;
	}

	public void setCreditAmout(BigDecimal creditAmout) {
		this.creditAmout = creditAmout;
	}

	public Date getInvestDate() {
		return investDate;
	}

	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}

	public BigDecimal getRecoveryInterest() {
		return recoveryInterest;
	}

	public void setRecoveryInterest(BigDecimal recoveryInterest) {
		this.recoveryInterest = recoveryInterest;
	}

	public BigDecimal getUnpaidInterest() {
		return unpaidInterest;
	}

	public void setUnpaidInterest(BigDecimal unpaidInterest) {
		this.unpaidInterest = unpaidInterest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		
		UserCredit uc = (UserCredit)o;
		
		return this.getInvestDate().compareTo(uc.getInvestDate());
				
	}
	
	
 
}
