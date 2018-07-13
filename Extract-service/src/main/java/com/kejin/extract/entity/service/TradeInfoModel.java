package com.kejin.extract.entity.service;

import java.math.BigDecimal;

public class TradeInfoModel {
	private BigDecimal investCurrentAmount;
	private BigDecimal transferCurrentAmount;
	private BigDecimal investRegularAmount;
	private BigDecimal transferRegularAmount;
	private Integer investCurrentNum;
	private Integer transferCurrentNum;
	private Integer investRegularNum;
	private Integer transferRegularNum;
	
	public BigDecimal getInvestCurrentAmount() {
		return investCurrentAmount;
	}

	public void setInvestCurrentAmount(BigDecimal investCurrentAmount) {
		this.investCurrentAmount = investCurrentAmount;
	}

	public BigDecimal getTransferCurrentAmount() {
		return transferCurrentAmount;
	}

	public void setTransferCurrentAmount(BigDecimal transferCurrentAmount) {
		this.transferCurrentAmount = transferCurrentAmount;
	}

	public BigDecimal getInvestRegularAmount() {
		return investRegularAmount;
	}

	public void setInvestRegularAmount(BigDecimal investRegularAmount) {
		this.investRegularAmount = investRegularAmount;
	}

	public BigDecimal getTransferRegularAmount() {
		return transferRegularAmount;
	}

	public void setTransferRegularAmount(BigDecimal transferRegularAmount) {
		this.transferRegularAmount = transferRegularAmount;
	}

	public Integer getInvestCurrentNum() {
		return investCurrentNum;
	}

	public void setInvestCurrentNum(Integer investCurrentNum) {
		this.investCurrentNum = investCurrentNum;
	}

	public Integer getTransferCurrentNum() {
		return transferCurrentNum;
	}

	public void setTransferCurrentNum(Integer transferCurrentNum) {
		this.transferCurrentNum = transferCurrentNum;
	}

	public Integer getInvestRegularNum() {
		return investRegularNum;
	}

	public void setInvestRegularNum(Integer investRegularNum) {
		this.investRegularNum = investRegularNum;
	}

	public Integer getTransferRegularNum() {
		return transferRegularNum;
	}

	public void setTransferRegularNum(Integer transferRegularNum) {
		this.transferRegularNum = transferRegularNum;
	}
}
