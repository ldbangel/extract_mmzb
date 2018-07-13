package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class FundFlowInfoModel {
	
	/**
	 * 充值金额
	 */
	private BigDecimal chargeAmount;
	

	/**
	 * 提现金额
	 */
	private BigDecimal cashAmount;
	
	/**
	 * 资金净流入
	 */
	private BigDecimal inflow;
		
	/**
	 * 充值人次
	 */
	private Integer chargeNum;
	
	/**
	 * 提现人次
	 */
	private Integer cashNum;
	
	
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getInflow() {
		return inflow;
	}

	public void setInflow(BigDecimal inflow) {
		this.inflow = inflow;
	}
	
	public Integer getChargeNum() {
		return chargeNum;
	}

	public void setChargeNum(Integer chargeNum) {
		this.chargeNum = chargeNum;
	}

	public Integer getCashNum() {
		return cashNum;
	}

	public void setCashNum(Integer cashNum) {
		this.cashNum = cashNum;
	}

	

}
