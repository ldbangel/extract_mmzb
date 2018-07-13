package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class DayCashSummaryInfoModel {
	
	private BigDecimal allCashAmount;
	private BigDecimal largeCashAmount;
	private BigDecimal percentage;
	
	public BigDecimal getAllCashAmount() {
		return allCashAmount;
	}
	public void setAllCashAmount(BigDecimal allCashAmount) {
		this.allCashAmount = allCashAmount;
	}
	public BigDecimal getLargeCashAmount() {
		return largeCashAmount;
	}
	public void setLargeCashAmount(BigDecimal largeCashAmount) {
		this.largeCashAmount = largeCashAmount;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

}
