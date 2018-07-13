package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DCurrentRecoveryModel {
	

	private Integer id;
	private String recoveryOrderDetailNo;
	private String memberId;
	private String name;
	private String phone;
	private BigDecimal amount;
	private Date createDate;
	private BigDecimal credit;
	private BigDecimal rewardRate;
	private Date GMT_CREATE;
	private Date GMT_MODIFIED;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecoveryOrderDetailNo() {
		return recoveryOrderDetailNo;
	}
	public void setRecoveryOrderDetailNo(String recoveryOrderDetailNo) {
		this.recoveryOrderDetailNo = recoveryOrderDetailNo;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public BigDecimal getRewardRate() {
		return rewardRate;
	}
	public void setRewardRate(BigDecimal rewardRate) {
		this.rewardRate = rewardRate;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



}
