package com.kejin.extract.entity.service;

import java.math.BigDecimal;
import java.util.Date;

public class UserBaseInfoModel {
	
	private String memberId;
	private String name;
	private String age;
	private String gender;
	private String nativeCity;
	private String certNum;
	private String phone;
	private String origin;
	private Date registerDatetime;
	//private Date certDatetime;
	private Date tieCarDatetime;
	private String managerName;
	private Date firstInvestDatetime;
	private BigDecimal firstInvestAmount;
	//private Date firstInvestDatetimeOfCurrent;
	//private BigDecimal firstInvestAmountOfCurrent;
	private String bankCardHeadOffice;
	private String platformUserNo;
	private String userRole;
	
	
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
	public String getNativeCity() {
		return nativeCity;
	}
	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
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
	public String getBankCardHeadOffice() {
		return bankCardHeadOffice;
	}
	public void setBankCardHeadOffice(String bankCardHeadOffice) {
		this.bankCardHeadOffice = bankCardHeadOffice;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPlatformUserNo() {
		return platformUserNo;
	}
	public void setPlatformUserNo(String platformUserNo) {
		this.platformUserNo = platformUserNo;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
}
