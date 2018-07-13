package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DCreditAssigmentModel {
	
	

	private Integer id;	
	private String bidOrderNo;
	private String assignmentId;
	private String assignmentName;
	private String transferId;
	private String transferName;
	private BigDecimal assignAmount;
	private BigDecimal payAmount;
	private BigDecimal payCredit;
	private BigDecimal payPremium;
	private Date payDate;
	private String subjectNo;
	private String subjectName;
	private String subjectLife;
	private BigDecimal rate;
	private String repayType;
	private BigDecimal assignAllAmount;
	private BigDecimal minAmount;
	private BigDecimal discount;
	private Date assignBeginDate;
	private Date assignEndDate;
	private Date GMT_CREATE;
	private Date GMT_MODIFIED;
	private BigDecimal payFee;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}
	public String getAssignmentName() {
		return assignmentName;
	}
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	public String getTransferName() {
		return transferName;
	}
	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}
	public BigDecimal getAssignAmount() {
		return assignAmount;
	}
	public void setAssignAmount(BigDecimal assignAmount) {
		this.assignAmount = assignAmount;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getPayCredit() {
		return payCredit;
	}
	public void setPayCredit(BigDecimal payCredit) {
		this.payCredit = payCredit;
	}
	public BigDecimal getPayPremium() {
		return payPremium;
	}
	public void setPayPremium(BigDecimal payPremium) {
		this.payPremium = payPremium;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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
	public BigDecimal getAssignAllAmount() {
		return assignAllAmount;
	}
	public void setAssignAllAmount(BigDecimal assignAllAmount) {
		this.assignAllAmount = assignAllAmount;
	}
	public BigDecimal getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Date getAssignBeginDate() {
		return assignBeginDate;
	}
	public void setAssignBeginDate(Date assignBeginDate) {
		this.assignBeginDate = assignBeginDate;
	}
	public Date getAssignEndDate() {
		return assignEndDate;
	}
	public void setAssignEndDate(Date assignEndDate) {
		this.assignEndDate = assignEndDate;
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
	public String getBidOrderNo() {
		return bidOrderNo;
	}
	public void setBidOrderNo(String bidOrderNo) {
		this.bidOrderNo = bidOrderNo;
	}
	public BigDecimal getPayFee() {
		return payFee;
	}
	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}

}
