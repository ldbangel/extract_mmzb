package com.kejin.extract.entity.service.dynamic;

import java.math.BigDecimal;


public class RelationCreditAssignmentModel {
	
    public String id;
	
	public String subjectNo;
	
	public BigDecimal assignmentCreditAmount;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public BigDecimal getAssignmentCreditAmount() {
		return assignmentCreditAmount;
	}

	public void setAssignmentCreditAmount(BigDecimal assignmentCreditAmount) {
		this.assignmentCreditAmount = assignmentCreditAmount;
	}

}
