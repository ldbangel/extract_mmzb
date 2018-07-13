package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class BaseInvestInfoModel {

	/**
	 * 投资人次
	 */
	private Integer investNum;
	
	/**
	 * 投资金额
	 */
	private BigDecimal investAmount;
	
    /**
     * 投资的时间区域
     */
	private Integer investHour;
	

	public Integer getInvestNum() {
		return investNum;
	}

	public void setInvestNum(Integer investNum) {
		this.investNum = investNum;
	}

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	public Integer getInvestHour() {
		return investHour;
	}

	public void setInvestHour(Integer investHour) {
		this.investHour = investHour;
	}

	




}
