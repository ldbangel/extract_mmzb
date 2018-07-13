package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DLoanModel {
	private Integer id;
	private String memberId;
	private Integer repaySchedId;
	private String loanProjNo;
	private BigDecimal unpaidPrincipal;
	private BigDecimal unpaidInterest;
	private BigDecimal paidPrincipal;
	private BigDecimal paidInterest;
	private Integer termNum;
	private String status;
	private Date planRepayDate;
	private Date actualRepayDate;
	private Date createTime;
	private Date repayTime;
	private Date GMT_CREATE;
	private Date GMT_MODIFIED;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Integer getRepaySchedId() {
		return repaySchedId;
	}
	public void setRepaySchedId(Integer repaySchedId) {
		this.repaySchedId = repaySchedId;
	}
	public String getLoanProjNo() {
		return loanProjNo;
	}
	public void setLoanProjNo(String loanProjNo) {
		this.loanProjNo = loanProjNo;
	}
	public BigDecimal getUnpaidPrincipal() {
		return unpaidPrincipal;
	}
	public void setUnpaidPrincipal(BigDecimal unpaidPrincipal) {
		this.unpaidPrincipal = unpaidPrincipal;
	}
	public BigDecimal getUnpaidInterest() {
		return unpaidInterest;
	}
	public void setUnpaidInterest(BigDecimal unpaidInterest) {
		this.unpaidInterest = unpaidInterest;
	}
	public BigDecimal getPaidPrincipal() {
		return paidPrincipal;
	}
	public void setPaidPrincipal(BigDecimal paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}
	public BigDecimal getPaidInterest() {
		return paidInterest;
	}
	public void setPaidInterest(BigDecimal paidInterest) {
		this.paidInterest = paidInterest;
	}
	public Integer getTermNum() {
		return termNum;
	}
	public void setTermNum(Integer termNum) {
		this.termNum = termNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPlanRepayDate() {
		return planRepayDate;
	}
	public void setPlanRepayDate(Date planRepayDate) {
		this.planRepayDate = planRepayDate;
	}
	public Date getActualRepayDate() {
		return actualRepayDate;
	}
	public void setActualRepayDate(Date actualRepayDate) {
		this.actualRepayDate = actualRepayDate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
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
