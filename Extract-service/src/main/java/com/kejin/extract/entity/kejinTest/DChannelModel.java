package com.kejin.extract.entity.kejinTest;

import java.util.Date;

/**
 * 渠道实体类
 *
 */
public class DChannelModel {

	private String memberId;
	private String name;
	private String phone;
	private String fCode;
	private Integer zone;
	private String description;
	private Date createTime;
	
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
	public String getfCode() {
		return fCode;
	}
	public void setfCode(String fCode) {
		this.fCode = fCode;
	}
	public Integer getZone() {
		return zone;
	}
	public void setZone(Integer zone) {
		this.zone = zone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
