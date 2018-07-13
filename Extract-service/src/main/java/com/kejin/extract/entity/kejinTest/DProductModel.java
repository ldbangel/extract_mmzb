package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DProductModel {
	
	private Integer id ;
	private String memberId;
	private String name ;
	private String companyName;
	private String phone;
	private String mail;
	private String loanProjectNo;
	private String projectNo;
	private String projectName_loan;
	private String projectName_invest;
	private BigDecimal rate;
	private String loanTerm;
	private String repayType;
	private BigDecimal applyAmount;
	private BigDecimal biddedAmount;
	private BigDecimal bidableAmount;
	private String projectManagerA;
	private String projectManagerB;
	
	private String status;
	
	private Date lineDatetime;

	private Date carryInterestDatetime;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLoanProjectNo() {
		return loanProjectNo;
	}

	public void setLoanProjectNo(String loanProjectNo) {
		this.loanProjectNo = loanProjectNo;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName_loan() {
		return projectName_loan;
	}

	public void setProjectName_loan(String projectName_loan) {
		this.projectName_loan = projectName_loan;
	}

	public String getProjectName_invest() {
		return projectName_invest;
	}

	public void setProjectName_invest(String projectName_invest) {
		this.projectName_invest = projectName_invest;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public BigDecimal getBiddedAmount() {
		return biddedAmount;
	}

	public void setBiddedAmount(BigDecimal biddedAmount) {
		this.biddedAmount = biddedAmount;
	}

	public BigDecimal getBidableAmount() {
		return bidableAmount;
	}

	public void setBidableAmount(BigDecimal bidableAmount) {
		this.bidableAmount = bidableAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLineDatetime() {
		return lineDatetime;
	}

	public void setLineDatetime(Date lineDatetime) {
		this.lineDatetime = lineDatetime;
	}

	public Date getCarryInterestDatetime() {
		return carryInterestDatetime;
	}

	public void setCarryInterestDatetime(Date carryInterestDatetime) {
		this.carryInterestDatetime = carryInterestDatetime;
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

	public String getProjectManagerA() {
		return projectManagerA;
	}

	public void setProjectManagerA(String projectManagerA) {
		this.projectManagerA = projectManagerA;
	}

	public String getProjectManagerB() {
		return projectManagerB;
	}

	public void setProjectManagerB(String projectManagerB) {
		this.projectManagerB = projectManagerB;
	}

}
