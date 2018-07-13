package com.kejin.extract.entity.service;

import java.math.BigDecimal;
import java.util.Date;

public class CashInfoModel {
	
	private BigDecimal amount;
	
	private Date cashDate;
	
	private String status;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCashDate() {
		return cashDate;
	}

	public void setCashDate(Date cashDate) {
		this.cashDate = cashDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
