package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class DayChargeDetailInfoModel {
	
	 private String memberId;
	 private String name;
	 private String phone;
	 private BigDecimal amount;
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

}
