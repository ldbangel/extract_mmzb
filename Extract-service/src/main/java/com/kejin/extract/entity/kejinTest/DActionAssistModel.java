package com.kejin.extract.entity.kejinTest;

import java.util.Date;

/**
 * @author chenxiaojian
 *
 */

/**
 * 抽取妈妈钱包数据记录表
 */
public class DActionAssistModel {
	
	private Integer id ;		

	private Date recordBeginDatetime;	
	
	private Date recordEndDatetime;
	
	private String intervalTime;
	
	private String handleData;

	private Date beginDatetime;
	
	private Date endDatetime;
	
	private String output;
	
	private String reason;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRecordBeginDatetime() {
		return recordBeginDatetime;
	}

	public void setRecordBeginDatetime(Date recordBeginDatetime) {
		this.recordBeginDatetime = recordBeginDatetime;
	}

	public Date getRecordEndDatetime() {
		return recordEndDatetime;
	}

	public void setRecordEndDatetime(Date recordEndDatetime) {
		this.recordEndDatetime = recordEndDatetime;
	}

	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}
	
	public String getHandleData() {
		return handleData;
	}

	public void setHandleData(String handleData) {
		this.handleData = handleData;
	}


	public Date getBeginDatetime() {
		return beginDatetime;
	}

	public void setBeginDatetime(Date beginDatetime) {
		this.beginDatetime = beginDatetime;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	

}
