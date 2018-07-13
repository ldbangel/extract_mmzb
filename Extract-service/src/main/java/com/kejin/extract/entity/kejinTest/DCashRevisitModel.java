package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;


public class DCashRevisitModel {
	private Integer id;
	private String memberId;
	private String name;
	private String phone;
	private BigDecimal cashAmount;
	private String managerName;
	private String feedback;
	private Date orderTime;
	private Date GMT_CREATE;
	private Date GMT_MODIFIED;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public BigDecimal getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getGMT_CREATE() {
		return GMT_CREATE;
	}
	public void setGMT_CREATE(Date gMT_CREATE) {
		GMT_CREATE = gMT_CREATE;
	}
	public Date getGMT_MODIFIED() {
		return GMT_MODIFIED;
	}
	public void setGMT_MODIFIED(Date gMT_MODIFIED) {
		GMT_MODIFIED = gMT_MODIFIED;
	}
	
	
}
