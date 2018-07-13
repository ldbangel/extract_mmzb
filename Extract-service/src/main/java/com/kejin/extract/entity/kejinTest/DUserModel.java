package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DUserModel {
	
	private Integer id ;
	
	private String memberId;
	
	private Integer memberType;
	
	private Date registerDatetime;
	
	private Date certDatetime;
	
	private Date tieCarDatetime;	
	
	private Date firstInvestDatetime;
	
	private BigDecimal firstInvestAmount;
	
	private Date firstInvestDatetimeOfCurrent;
	
	private BigDecimal firstInvestAmountOfCurrent;
	
	private String financialManager;
	
	private String friendCode;
	
	private String recommander;
	
	private String recommandFriendCode;
	
	private String name;
	
	private String certNum;
	
	private String nativeProvince;
	
	private String nativeCity;
	
	private String nativeArea;
	
	/**
	 * 只记录出生年份，通过前端计算出生日期
	 */
	private Date age;
		
	/**
	 * 0表示男性，1表示女性，3表示其他
	 */
	private Integer gender;
	
	private String phone;	
	
	private String phoneProvince;	
	
	private String phoneCity;	
	
	private String bankCardNum;	
	
	private String bankCardAcountName;
	
	private String bankCardProvince;
	
	private String bankCardCity;
	
	private String bankCardHeadOffice;
	
	private String bankCardBranchOffice;
	
	private Date GMT_CREATE;
	
	private Date GMT_MODIFIED;
	
	private String platformUserNo;
	private String userRole;
	private String isActivate;
	
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

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Date getRegisterDatetime() {
		return registerDatetime;
	}

	public void setRegisterDatetime(Date registerDatetime) {
		this.registerDatetime = registerDatetime;
	}

	public Date getCertDatetime() {
		return certDatetime;
	}

	public void setCertDatetime(Date certDatetime) {
		this.certDatetime = certDatetime;
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

	public Date getFirstInvestDatetimeOfCurrent() {
		return firstInvestDatetimeOfCurrent;
	}

	public void setFirstInvestDatetimeOfCurrent(Date firstInvestDatetimeOfCurrent) {
		this.firstInvestDatetimeOfCurrent = firstInvestDatetimeOfCurrent;
	}

	public BigDecimal getFirstInvestAmountOfCurrent() {
		return firstInvestAmountOfCurrent;
	}

	public void setFirstInvestAmountOfCurrent(BigDecimal firstInvestAmountOfCurrent) {
		this.firstInvestAmountOfCurrent = firstInvestAmountOfCurrent;
	}

	public String getFinancialManager() {
		return financialManager;
	}

	public void setFinancialManager(String financialManager) {
		this.financialManager = financialManager;
	}

	public String getFriendCode() {
		return friendCode;
	}

	public void setFriendCode(String friendCode) {
		this.friendCode = friendCode;
	}

	public String getRecommander() {
		return recommander;
	}

	public void setRecommander(String recommander) {
		this.recommander = recommander;
	}

	public String getRecommandFriendCode() {
		return recommandFriendCode;
	}

	public void setRecommandFriendCode(String recommandFriendCode) {
		this.recommandFriendCode = recommandFriendCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getNativeProvince() {
		return nativeProvince;
	}

	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	public String getNativeArea() {
		return nativeArea;
	}

	public void setNativeArea(String nativeArea) {
		this.nativeArea = nativeArea;
	}

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneProvince() {
		return phoneProvince;
	}

	public void setPhoneProvince(String phoneProvince) {
		this.phoneProvince = phoneProvince;
	}

	public String getPhoneCity() {
		return phoneCity;
	}

	public void setPhoneCity(String phoneCity) {
		this.phoneCity = phoneCity;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getBankCardAcountName() {
		return bankCardAcountName;
	}

	public void setBankCardAcountName(String bankCardAcountName) {
		this.bankCardAcountName = bankCardAcountName;
	}

	public String getBankCardProvince() {
		return bankCardProvince;
	}

	public void setBankCardProvince(String bankCardProvince) {
		this.bankCardProvince = bankCardProvince;
	}

	public String getBankCardCity() {
		return bankCardCity;
	}

	public void setBankCardCity(String bankCardCity) {
		this.bankCardCity = bankCardCity;
	}

	public String getBankCardHeadOffice() {
		return bankCardHeadOffice;
	}

	public void setBankCardHeadOffice(String bankCardHeadOffice) {
		this.bankCardHeadOffice = bankCardHeadOffice;
	}

	public String getBankCardBranchOffice() {
		return bankCardBranchOffice;
	}

	public void setBankCardBranchOffice(String bankCardBranchOffice) {
		this.bankCardBranchOffice = bankCardBranchOffice;
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

	public String getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(String isActivate) {
		this.isActivate = isActivate;
	}
}
