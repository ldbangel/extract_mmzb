package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DRegularInvestModel {
	
	
	private Integer id;
	private String bidOrderNo;
	private String memberId;
	private String subjectNo;
	private String subjectName;
	private String subjectLife;
	private String subjectType;
	private BigDecimal rate;
	private String repayType;
	private BigDecimal amount;
	private String operationType;
	private Date operationDate;
	private String operationStatus;
	private Date GMT_CREATE;
	private Date GMT_MODIFIED;
	private String platform;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBidOrderNo() {
		return bidOrderNo;
	}
	public void setBidOrderNo(String bidOrderNo) {
		this.bidOrderNo = bidOrderNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
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
	public String getSubjectLife() {
		return subjectLife;
	}
	public void setSubjectLife(String subjectLife) {
		this.subjectLife = subjectLife;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getRepayType() {
		return repayType;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
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
	public String getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
