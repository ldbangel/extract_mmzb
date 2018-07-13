package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DRegularRecoveryModel {
	
	private Integer id;
	private String recoveryOrderDetailNo;
	private String memberId;
	private BigDecimal amount;
	private BigDecimal principal;
	private BigDecimal interest;
	private String repayTerm;
	private String totalTerm;
	private String subjectNo;
	private String loanTerm;
	private Date creatTime;
	private Date GMT_CREATE;
	private Date GMT_MODIFIED;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecoveryOrderDetailNo() {
		return recoveryOrderDetailNo;
	}
	public void setRecoveryOrderDetailNo(String recoveryOrderDetailNo) {
		this.recoveryOrderDetailNo = recoveryOrderDetailNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public String getRepayTerm() {
		return repayTerm;
	}
	public void setRepayTerm(String repayTerm) {
		this.repayTerm = repayTerm;
	}
	public String getTotalTerm() {
		return totalTerm;
	}
	public void setTotalTerm(String totalTerm) {
		this.totalTerm = totalTerm;
	}
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getGMT_CREATE() {
		return GMT_CREATE;
	}
	public void setGMT_CREATE(Date gMT_CREATE) {
		GMT_CREATE = gMT_CREATE;
	}
	public Date getGMT_MODIFIED() {
		return GMT_MODIFIED;
	}
	public void setGMT_MODIFIED(Date gMT_MODIFIED) {
		GMT_MODIFIED = gMT_MODIFIED;
	}



}
