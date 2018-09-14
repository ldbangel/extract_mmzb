package com.kejin.extract.domainservice.mongodb;

import java.util.List;

public class RelationshipModel {
	private String memberId;
	private String name;
	private String phone;
	private String fcode;
	private List<RelationshipModel> inviteList;
	
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
	public String getFcode() {
		return fcode;
	}
	public void setFcode(String fcode) {
		this.fcode = fcode;
	}
	public List<RelationshipModel> getInviteList() {
		return inviteList;
	}
	public void setInviteList(List<RelationshipModel> inviteList) {
		this.inviteList = inviteList;
	}
	
	
}
