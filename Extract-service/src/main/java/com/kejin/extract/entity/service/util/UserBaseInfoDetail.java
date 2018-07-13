package com.kejin.extract.entity.service.util;

import java.math.BigDecimal;
import java.util.Date;

public class UserBaseInfoDetail {
	
	private String name;	
	private String age;	
	private String gender;	
	private String province;	
	private String phone ;	
	private String referee;	
	private String cardBank;	
	private String managerName;
	private Date registerDatetime;
	
	//private Date certDatetime;
	private Date tieCarDatetime;
	private Date firstInvestDatetime;
	private BigDecimal firstInvestAmount;
	//private Date firstInvestDatetimeOfCurrent;
	//private BigDecimal firstInvestAmountOfCurrent;
	
	
	private BigDecimal credit;
	private String unpaidInterest;
	//private BigDecimal currentAmount;
	private BigDecimal cashAmount;
	//private BigDecimal bounuceAmount;
	private BigDecimal allAmount;
	
	
	private BigDecimal accunmulateInvest; 
	private BigDecimal accunmulateRecoveryPrincipal;
	private BigDecimal accunmulateRecoveryInterest;
	private BigDecimal accunmulateCurrentIn;
	private BigDecimal accunmulateCurrentOut;
	//private BigDecimal accunmulateCurrentInterest;
	private BigDecimal accunmulateCash;
	private BigDecimal accunmulateCharge;
	
	private int investNums;
	private BigDecimal blockedFund;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
	public String getCardBank() {
		return cardBank;
	}
	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}
	public Date getRegisterDatetime() {
		return registerDatetime;
	}
	public void setRegisterDatetime(Date registerDatetime) {
		this.registerDatetime = registerDatetime;
	}
	public Date getTieCarDatetime() {
		return tieCarDatetime;
	}
	public void setTieCarDatetime(Date tieCarDatetime) {
		this.tieCarDatetime = tieCarDatetime;
	}
	public Date getFirstInvestDatetime() {
		return firstInvestDatetime;
	}
	public void setFirstInvestDatetime(Date firstInvestDatetime) {
		this.firstInvestDatetime = firstInvestDatetime;
	}
	public BigDecimal getFirstInvestAmount() {
		return firstInvestAmount;
	}
	public void setFirstInvestAmount(BigDecimal firstInvestAmount) {
		this.firstInvestAmount = firstInvestAmount;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public String getUnpaidInterest() {
		return unpaidInterest;
	}
	public void setUnpaidInterest(String unpaidInterest) {
		this.unpaidInterest = unpaidInterest;
	}
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	public BigDecimal getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	public BigDecimal getAccunmulateInvest() {
		return accunmulateInvest;
	}
	public void setAccunmulateInvest(BigDecimal accunmulateInvest) {
		this.accunmulateInvest = accunmulateInvest;
	}
	public BigDecimal getAccunmulateRecoveryPrincipal() {
		return accunmulateRecoveryPrincipal;
	}
	public void setAccunmulateRecoveryPrincipal(
			BigDecimal accunmulateRecoveryPrincipal) {
		this.accunmulateRecoveryPrincipal = accunmulateRecoveryPrincipal;
	}
	public BigDecimal getAccunmulateRecoveryInterest() {
		return accunmulateRecoveryInterest;
	}
	public void setAccunmulateRecoveryInterest(
			BigDecimal accunmulateRecoveryInterest) {
		this.accunmulateRecoveryInterest = accunmulateRecoveryInterest;
	}
	public BigDecimal getAccunmulateCurrentIn() {
		return accunmulateCurrentIn;
	}
	public void setAccunmulateCurrentIn(BigDecimal accunmulateCurrentIn) {
		this.accunmulateCurrentIn = accunmulateCurrentIn;
	}
	public BigDecimal getAccunmulateCurrentOut() {
		return accunmulateCurrentOut;
	}
	public void setAccunmulateCurrentOut(BigDecimal accunmulateCurrentOut) {
		this.accunmulateCurrentOut = accunmulateCurrentOut;
	}
	public BigDecimal getAccunmulateCash() {
		return accunmulateCash;
	}
	public void setAccunmulateCash(BigDecimal accunmulateCash) {
		this.accunmulateCash = accunmulateCash;
	}
	public BigDecimal getAccunmulateCharge() {
		return accunmulateCharge;
	}
	public void setAccunmulateCharge(BigDecimal accunmulateCharge) {
		this.accunmulateCharge = accunmulateCharge;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public BigDecimal getBlockedFund() {
		return blockedFund;
	}
	public void setBlockedFund(BigDecimal blockedFund) {
		this.blockedFund = blockedFund;
	}
	public int getInvestNums() {
		return investNums;
	}
	public void setInvestNums(int investNums) {
		this.investNums = investNums;
	}
}
