package com.kejin.extract.entity.service.dynamic;

import java.math.BigDecimal;

public class RelationUnpaiInterest {
	

	private String subjectNo;
	private BigDecimal Amount;
	private String subjectName;
	private BigDecimal unpaiInterest;
	
	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}
	public BigDecimal getAmount() {
		return Amount;
	}
	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public BigDecimal getUnpaiInterest() {
		return unpaiInterest;
	}
	public void setUnpaiInterest(BigDecimal unpaiInterest) {
		this.unpaiInterest = unpaiInterest;
	}
	
	

}
