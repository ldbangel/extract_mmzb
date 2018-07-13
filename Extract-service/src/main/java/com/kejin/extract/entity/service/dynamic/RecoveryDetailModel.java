package com.kejin.extract.entity.service.dynamic;

import java.math.BigDecimal;
import java.util.Date;

public class RecoveryDetailModel {
	
	private String subjectNo;
	private String subjectName;
	private Date recoveryTime;
	private BigDecimal principal;
	private BigDecimal interest;
	private String status;
	private String type;
	
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
	public Date getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
