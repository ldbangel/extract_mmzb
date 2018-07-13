package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;

public class DMemberBalanceModel {
	private Integer id;
	//日期
	private String settleDate;
	//会员ID
	private String memberId;
	//用户类型   0:普通用户     1:融资用户
	private Integer memType;
	//手机号码
	private String phoneNum;
	//姓名
	private String authName;
	//推荐人ID
	private String rMemberId;
	//推荐人手机号
	private String rPhoneNum;
	//推荐人姓名
	private String rAuthName;
	//推荐码
	private String rFriendCode;
	//现金账户
	//private BigDecimal accountType;
	//现金账户余额
	private BigDecimal balance;
	//奖金账户
	//private BigDecimal rAccountType;
	//奖金账户余额
	//private BigDecimal rBalance;
	//定期债权
	private BigDecimal creditNumbers;
	//冻结资金
	private BigDecimal blockedFund;
	//活期余额
	//private BigDecimal currentAmount;
	//个人总资产
	private BigDecimal totalAmount;
	//创建时间
	private Date GMT_CREATE;
	//平台账户
	private String platformUserNo;
	//用户角色
	private String userRole;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Integer getMemType() {
		return memType;
	}
	public void setMemType(Integer memType) {
		this.memType = memType;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getrMemberId() {
		return rMemberId;
	}
	public void setrMemberId(String rMemberId) {
		this.rMemberId = rMemberId;
	}
	public String getrPhoneNum() {
		return rPhoneNum;
	}
	public void setrPhoneNum(String rPhoneNum) {
		this.rPhoneNum = rPhoneNum;
	}
	public String getrAuthName() {
		return rAuthName;
	}
	public void setrAuthName(String rAuthName) {
		this.rAuthName = rAuthName;
	}
	public String getrFriendCode() {
		return rFriendCode;
	}
	public void setrFriendCode(String rFriendCode) {
		this.rFriendCode = rFriendCode;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getCreditNumbers() {
		return creditNumbers;
	}
	public void setCreditNumbers(BigDecimal creditNumbers) {
		this.creditNumbers = creditNumbers;
	}
	public BigDecimal getBlockedFund() {
		return blockedFund;
	}
	public void setBlockedFund(BigDecimal blockedFund) {
		this.blockedFund = blockedFund;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getGMT_CREATE() {
		return GMT_CREATE;
	}
	public void setGMT_CREATE(Date gMT_CREATE) {
		GMT_CREATE = gMT_CREATE;
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
