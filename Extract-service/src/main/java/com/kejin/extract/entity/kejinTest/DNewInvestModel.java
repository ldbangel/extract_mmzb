package com.kejin.extract.entity.kejinTest;

import java.util.Date;

/**
 * @Author Leo
 */
public class DNewInvestModel {
    private Integer id;
    private String memberId;
    private String bidOrderNo;
    private Date operationDate;
    private String status;
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

    public String getBidOrderNo() {
        return bidOrderNo;
    }

    public void setBidOrderNo(String bidOrderNo) {
        this.bidOrderNo = bidOrderNo;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGMT_CREATE() {
        return GMT_CREATE;
    }

    public void setGMT_CREATE(Date GMT_CREATE) {
        this.GMT_CREATE = GMT_CREATE;
    }

    public Date getGMT_MODIFIED() {
        return GMT_MODIFIED;
    }

    public void setGMT_MODIFIED(Date GMT_MODIFIED) {
        this.GMT_MODIFIED = GMT_MODIFIED;
    }
}
