package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;

public class DOperationCollectModel {
	//时间
	private String settleDate;
	//总资产
	private BigDecimal totalAsset;
	//定期宝资产
	private BigDecimal regularAsset;
	//新增注册人数
	private Integer newRegister;
	//新增绑卡人数
	private Integer newTieCard;
	//新增投资人数
	private Integer newInvest;
	//充值金额
	private BigDecimal chargeAmount;
	//充值人数
	private Integer chargeNum;
	//提现金额
	private BigDecimal cashAmount;
	//提现人数
	private Integer cashNum;
	
	private BigDecimal oneMonthAmount;
	private BigDecimal twoMonthAmount;
	private BigDecimal threeMonthAmount;
	private BigDecimal sixMonthAmount;
	private BigDecimal twelveMonthAmount;
	private BigDecimal otherRegularAmount;
	private BigDecimal newRegularAmount;
	private Integer oneMonthNum;
	private Integer twoMonthNum;
	private Integer threeMonthNum;
	private Integer sixMonthNum;
	private Integer twelveMonthNum;
	private Integer otherRegularNum;
	
	//定期宝投资金额
	private BigDecimal totalRegularAmount;
	//定期宝投资人数
	private Integer totalRegularNum;
	//债权交易金额
	private BigDecimal creditAmount;
	//债权交易人数
	private Integer creditNum;
	//定期宝回款金额
	private BigDecimal regularRecoveryAmount;
	//定期回款人数
	private Integer regularRecoveryNum;
	//定期宝日涨跌额
	private BigDecimal regularDailyGrowth;
	//定期宝日涨跌幅
	private BigDecimal regularDailyRail;
	//本月定期宝投资额
	private BigDecimal monthRegularAmount;
	//本月已回款
	private BigDecimal monthRecoveryAmount;
	//本月待回款
	private BigDecimal monthUnrecoveryAmount;
	//定期宝投资人数
	private Integer regularInvestNum;
	
	//本日复投金额
	private BigDecimal reinvestRegularAmount;
	//本月定期宝回款复投率
	private BigDecimal reinvestRate;
	//活跃用户数
	private Integer activeNum;
	
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public BigDecimal getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(BigDecimal totalAsset) {
		this.totalAsset = totalAsset;
	}
	public BigDecimal getRegularAsset() {
		return regularAsset;
	}
	public void setRegularAsset(BigDecimal regularAsset) {
		this.regularAsset = regularAsset;
	}
	public Integer getNewRegister() {
		return newRegister;
	}
	public void setNewRegister(Integer newRegister) {
		this.newRegister = newRegister;
	}
	public Integer getNewTieCard() {
		return newTieCard;
	}
	public void setNewTieCard(Integer newTieCard) {
		this.newTieCard = newTieCard;
	}
	public Integer getNewInvest() {
		return newInvest;
	}
	public void setNewInvest(Integer newInvest) {
		this.newInvest = newInvest;
	}
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public Integer getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(Integer chargeNum) {
		this.chargeNum = chargeNum;
	}
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	public Integer getCashNum() {
		return cashNum;
	}
	public void setCashNum(Integer cashNum) {
		this.cashNum = cashNum;
	}
	public BigDecimal getOneMonthAmount() {
		return oneMonthAmount;
	}
	public void setOneMonthAmount(BigDecimal oneMonthAmount) {
		this.oneMonthAmount = oneMonthAmount;
	}
	public BigDecimal getTwoMonthAmount() {
		return twoMonthAmount;
	}
	public void setTwoMonthAmount(BigDecimal twoMonthAmount) {
		this.twoMonthAmount = twoMonthAmount;
	}
	public BigDecimal getThreeMonthAmount() {
		return threeMonthAmount;
	}
	public void setThreeMonthAmount(BigDecimal threeMonthAmount) {
		this.threeMonthAmount = threeMonthAmount;
	}
	public BigDecimal getSixMonthAmount() {
		return sixMonthAmount;
	}
	public void setSixMonthAmount(BigDecimal sixMonthAmount) {
		this.sixMonthAmount = sixMonthAmount;
	}
	public BigDecimal getTwelveMonthAmount() {
		return twelveMonthAmount;
	}
	public void setTwelveMonthAmount(BigDecimal twelveMonthAmount) {
		this.twelveMonthAmount = twelveMonthAmount;
	}
	public BigDecimal getOtherRegularAmount() {
		return otherRegularAmount;
	}
	public void setOtherRegularAmount(BigDecimal otherRegularAmount) {
		this.otherRegularAmount = otherRegularAmount;
	}
	public BigDecimal getTotalRegularAmount() {
		return totalRegularAmount;
	}
	public void setTotalRegularAmount(BigDecimal totalRegularAmount) {
		this.totalRegularAmount = totalRegularAmount;
	}
	public BigDecimal getNewRegularAmount() {
		return newRegularAmount;
	}
	public void setNewRegularAmount(BigDecimal newRegularAmount) {
		this.newRegularAmount = newRegularAmount;
	}
	public BigDecimal getReinvestRegularAmount() {
		return reinvestRegularAmount;
	}
	public void setReinvestRegularAmount(BigDecimal reinvestRegularAmount) {
		this.reinvestRegularAmount = reinvestRegularAmount;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public BigDecimal getRegularRecoveryAmount() {
		return regularRecoveryAmount;
	}
	public void setRegularRecoveryAmount(BigDecimal regularRecoveryAmount) {
		this.regularRecoveryAmount = regularRecoveryAmount;
	}
	public Integer getOneMonthNum() {
		return oneMonthNum;
	}
	public void setOneMonthNum(Integer oneMonthNum) {
		this.oneMonthNum = oneMonthNum;
	}
	public Integer getTwoMonthNum() {
		return twoMonthNum;
	}
	public void setTwoMonthNum(Integer twoMonthNum) {
		this.twoMonthNum = twoMonthNum;
	}
	public Integer getThreeMonthNum() {
		return threeMonthNum;
	}
	public void setThreeMonthNum(Integer threeMonthNum) {
		this.threeMonthNum = threeMonthNum;
	}
	public Integer getSixMonthNum() {
		return sixMonthNum;
	}
	public void setSixMonthNum(Integer sixMonthNum) {
		this.sixMonthNum = sixMonthNum;
	}
	public Integer getTwelveMonthNum() {
		return twelveMonthNum;
	}
	public void setTwelveMonthNum(Integer twelveMonthNum) {
		this.twelveMonthNum = twelveMonthNum;
	}
	public Integer getOtherRegularNum() {
		return otherRegularNum;
	}
	public void setOtherRegularNum(Integer otherRegularNum) {
		this.otherRegularNum = otherRegularNum;
	}
	public Integer getTotalRegularNum() {
		return totalRegularNum;
	}
	public void setTotalRegularNum(Integer totalRegularNum) {
		this.totalRegularNum = totalRegularNum;
	}
	public Integer getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(Integer creditNum) {
		this.creditNum = creditNum;
	}
	public Integer getRegularRecoveryNum() {
		return regularRecoveryNum;
	}
	public void setRegularRecoveryNum(Integer regularRecoveryNum) {
		this.regularRecoveryNum = regularRecoveryNum;
	}
	public BigDecimal getReinvestRate() {
		return reinvestRate;
	}
	public void setReinvestRate(BigDecimal reinvestRate) {
		this.reinvestRate = reinvestRate;
	}
	public Integer getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(Integer activeNum) {
		this.activeNum = activeNum;
	}
	public BigDecimal getRegularDailyGrowth() {
		return regularDailyGrowth;
	}
	public void setRegularDailyGrowth(BigDecimal regularDailyGrowth) {
		this.regularDailyGrowth = regularDailyGrowth;
	}
	public BigDecimal getRegularDailyRail() {
		return regularDailyRail;
	}
	public void setRegularDailyRail(BigDecimal regularDailyRail) {
		this.regularDailyRail = regularDailyRail;
	}
	public BigDecimal getMonthRegularAmount() {
		return monthRegularAmount;
	}
	public void setMonthRegularAmount(BigDecimal monthRegularAmount) {
		this.monthRegularAmount = monthRegularAmount;
	}
	public BigDecimal getMonthRecoveryAmount() {
		return monthRecoveryAmount;
	}
	public void setMonthRecoveryAmount(BigDecimal monthRecoveryAmount) {
		this.monthRecoveryAmount = monthRecoveryAmount;
	}
	public BigDecimal getMonthUnrecoveryAmount() {
		return monthUnrecoveryAmount;
	}
	public void setMonthUnrecoveryAmount(BigDecimal monthUnrecoveryAmount) {
		this.monthUnrecoveryAmount = monthUnrecoveryAmount;
	}
	public Integer getRegularInvestNum() {
		return regularInvestNum;
	}
	public void setRegularInvestNum(Integer regularInvestNum) {
		this.regularInvestNum = regularInvestNum;
	}
	
}
