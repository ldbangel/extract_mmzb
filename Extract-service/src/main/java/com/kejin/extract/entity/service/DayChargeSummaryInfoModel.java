package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class DayChargeSummaryInfoModel {
	
	private BigDecimal allChargeAmount;
	private BigDecimal largeChargeAmount;
	private BigDecimal percentage;
	
	
	public BigDecimal getAllChargeAmount() {
		return allChargeAmount;
	}
	public void setAllChargeAmount(BigDecimal allChargeAmount) {
		this.allChargeAmount = allChargeAmount;
	}
	public BigDecimal getLargeChargeAmount() {
		return largeChargeAmount;
	}
	public void setLargeChargeAmount(BigDecimal largeChargeAmount) {
		this.largeChargeAmount = largeChargeAmount;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

}
