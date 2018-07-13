package com.kejin.extract.entity.service.dynamic;

import java.math.BigDecimal;

public class CreditInfoModel {
	

	private String subjectNo;
	private String creditId;
	private String subjectName;
	private String status;
	private BigDecimal amount;
	private String term;
	
	/**
	 * 对应的每期利息
	 */
	private BigDecimal interest;
	
	private String bidOrderNo;
	
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public String getBidOrderNo() {
		return bidOrderNo;
	}
	public void setBidOrderNo(String bidOrderNo) {
		this.bidOrderNo = bidOrderNo;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}

}
