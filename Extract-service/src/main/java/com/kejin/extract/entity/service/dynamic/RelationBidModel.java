package com.kejin.extract.entity.service.dynamic;

import java.math.BigDecimal;
import java.util.Date;

public class RelationBidModel {
	
	private String bidNo;
	
	private String subjectNo;
	
	private BigDecimal amount;
	
	private String subjectType;
	
	private Date createTime;
	
	public String getBidNo() {
		return bidNo;
	}

	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getSubjectType() {
		return subjectType;
	}
	
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
