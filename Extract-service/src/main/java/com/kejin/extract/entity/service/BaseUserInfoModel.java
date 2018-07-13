package com.kejin.extract.entity.service;

public class BaseUserInfoModel {


	/**
	 * 注册人数
	 */
	private Integer registerNum;
	
    /**
     * 注册的时间区域
     */
	private Integer registerHour;
	
	public Integer getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(Integer registerNum) {
		this.registerNum = registerNum;
	}

	public Integer getRegisterHour() {
		return registerHour;
	}

	public void setRegisterHour(Integer registerHour) {
		this.registerHour = registerHour;
	}

}
