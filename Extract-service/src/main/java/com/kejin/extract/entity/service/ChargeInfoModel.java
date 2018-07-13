package com.kejin.extract.entity.service;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeInfoModel {
	
	private BigDecimal amount;
	
	private Date chargeDate;
	
	private String chargeMethod;
	
	private String status;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
