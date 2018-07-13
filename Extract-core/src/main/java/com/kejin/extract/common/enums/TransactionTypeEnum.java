package com.kejin.extract.common.enums;

/**
 * @Author Leo
 * 交易类型
 */
public enum TransactionTypeEnum {
    REGULAR_INVEST("1","定期投资"),
    CURRENT_INVEST("2","活期投资"),
    CREDIT_INVEST("3","债权投资"),
    REGULAR_RECOVERY("4","定期回款"),
    CURRENT_RECOVERY("5","活期回款"),
    CREDIT_TRANSFER("6","债权转出");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    TransactionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 判断code是否代表投资
     */
    public static boolean isInvest(String code){
        if ("1".equals(code)||"2".equals(code)||"3".equals(code)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断code是否代表回款
     */
    public static boolean isRecovery(String code){
        return !isInvest(code);
    }
}
