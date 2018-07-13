package com.kejin.extract.entity.kejinTest;

import java.math.BigDecimal;
import java.util.Date;

import com.kejin.extract.common.enums.TransactionTypeEnum;

/**
 * @Author Leo
 * 新增和复投统计表
 */
public class DReInvestAndNewModel {
    /**
     * 主键，唯一的
     */
    private Integer id;
    /**
     * 交易编号，唯一的
     */
    private String orderNo;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 项目期限
     */
    private String subjectLife;
    /**
     * 交易类型
     * 定期投资：1
     * 活期转入：2
     * 债权投资：3
     * 定期回款：4
     * 活期转出：5
     * 债权出让：6
     */
    private String transactionType;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 回款余额
     */
    private BigDecimal recoveryTotal;
    /**
     * 回款金额
     */
    private BigDecimal recoveryAmount;
    /**
     * 复投金额
     */
    private BigDecimal reinvestAmount;
    /**
     * 新增金额
     */
    private BigDecimal newAmount;
    /**
     * 交易时间
     */
    private Date operationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSubjectLife() {
        return subjectLife;
    }

    public void setSubjectLife(String subjectLife) {
        this.subjectLife = subjectLife;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRecoveryTotal() {
        return recoveryTotal;
    }

    public void setRecoveryTotal(BigDecimal recoveryTotal) {
        this.recoveryTotal = recoveryTotal;
    }

    public BigDecimal getRecoveryAmount() {
        return recoveryAmount;
    }

    public void setRecoveryAmount(BigDecimal recoveryAmount) {
        this.recoveryAmount = recoveryAmount;
    }

    public BigDecimal getReinvestAmount() {
        return reinvestAmount;
    }

    public void setReinvestAmount(BigDecimal reinvestAmount) {
        this.reinvestAmount = reinvestAmount;
    }

    public BigDecimal getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * 构建定期投资记录
     */
    public static DReInvestAndNewModel buildFromRegularInvest(DRegularInvestModel model) {
        if (model == null) return null;
        DReInvestAndNewModel r = new DReInvestAndNewModel();
        r.setOrderNo(model.getBidOrderNo());
        r.setTransactionType(TransactionTypeEnum.REGULAR_INVEST.getCode());
        r.setMemberId(model.getMemberId());
        r.setAmount(model.getAmount());
        r.setSubjectLife(model.getSubjectLife());
        r.setOperationDate(model.getOperationDate());
        return r;
    }

    /**
     * 构建活期转入（投资）记录
     */
    public static DReInvestAndNewModel buildFromCurrentInvest(DCurrentInvetModel model) {
        if (model == null) return null;
        DReInvestAndNewModel r = new DReInvestAndNewModel();
        r.setOrderNo(model.getBidOrderNo());
        if ("CURRENT_O".equals(model.getSubjectType())) {
        	//活期回款
            r.setTransactionType(TransactionTypeEnum.CURRENT_RECOVERY.getCode());
            r.setRecoveryAmount(model.getAmount());
        } else if ("CURRENT_I".equals(model.getSubjectType())){
        	//活期投资
            r.setTransactionType(TransactionTypeEnum.CURRENT_INVEST.getCode());
        }
        r.setMemberId(model.getMemberId());
        r.setAmount(model.getAmount());
        r.setSubjectLife(model.getSubjectLife());
        r.setOperationDate(model.getOperationDate());
        return r;
    }
    
    /**
     * 构建债权转让的买方记录
     */
    public static DReInvestAndNewModel buildTransferRecordFromCreditAssigment(DCreditAssigmentModel model) {
        if (model == null) return null;
        DReInvestAndNewModel t = new DReInvestAndNewModel();
        t.setOrderNo(model.getBidOrderNo());
        t.setTransactionType(TransactionTypeEnum.CREDIT_INVEST.getCode());
        t.setAmount(model.getPayAmount());
        t.setMemberId(model.getTransferId());
        t.setSubjectLife(model.getSubjectLife());
        t.setOperationDate(model.getPayDate());
        return t;
    }

    /**
     * 构建定期回款记录
     */
    public static DReInvestAndNewModel buildFromRegularRecovery(DRegularRecoveryModel model) {
        if (model == null) return null;
        DReInvestAndNewModel r = new DReInvestAndNewModel();
        r.setOrderNo(model.getRecoveryOrderDetailNo());
        r.setTransactionType(TransactionTypeEnum.REGULAR_RECOVERY.getCode());
        r.setMemberId(model.getMemberId());
        r.setSubjectLife(model.getLoanTerm());
        r.setAmount(model.getAmount());
        r.setRecoveryAmount(model.getAmount());
        r.setOperationDate(model.getCreatTime());
        return r;
    }

    /**
     * 构建活期转出（利息回款）记录
     */
    public static DReInvestAndNewModel buildFromCurrentRecovery(DCurrentRecoveryModel model) {
        if (model == null) return null;
        DReInvestAndNewModel r = new DReInvestAndNewModel();
        r.setOrderNo(model.getRecoveryOrderDetailNo());
        r.setTransactionType(TransactionTypeEnum.CURRENT_RECOVERY.getCode());
        r.setMemberId(model.getMemberId());
        r.setAmount(model.getAmount());
        r.setRecoveryAmount(model.getAmount());
        r.setOperationDate(model.getCreateDate());
        return r;
    }

    /**
     * 构建债权转让的卖方记录
     */
    public static DReInvestAndNewModel buildAssignRecordFromCreditAssigment(DCreditAssigmentModel model){
        if (model == null) return null;
        DReInvestAndNewModel a = new DReInvestAndNewModel();
        a.setOrderNo(model.getBidOrderNo());
        a.setTransactionType(TransactionTypeEnum.CREDIT_TRANSFER.getCode());
        a.setRecoveryAmount(model.getPayAmount());
        a.setMemberId(model.getAssignmentId());
        a.setAmount(model.getPayAmount());
        a.setSubjectLife(model.getSubjectLife());
        a.setOperationDate(model.getPayDate());
        return a;
    }

}
