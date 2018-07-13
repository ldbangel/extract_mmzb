package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class DayInvestSummaryInfoModel {
	
	private BigDecimal allInvestAmount;
	private BigDecimal largeInvestAmount;
	private BigDecimal percentage;
	
	public BigDecimal getAllInvestAmount() {
		return allInvestAmount;
	}
	public void setAllInvestAmount(BigDecimal allInvestAmount) {
		this.allInvestAmount = allInvestAmount;
	}
	public BigDecimal getLargeInvestAmount() {
		return largeInvestAmount;
	}
	public void setLargeInvestAmount(BigDecimal largeInvestAmount) {
		this.largeInvestAmount = largeInvestAmount;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

}
