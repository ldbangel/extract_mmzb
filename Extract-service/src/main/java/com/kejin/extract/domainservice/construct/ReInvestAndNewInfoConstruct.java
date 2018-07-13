package com.kejin.extract.domainservice.construct;

import java.util.Date;

/**
 * @Author Leo
 * 构造RestInvestAndNewModel数据模型，并插入到数据库中
 */
public interface ReInvestAndNewInfoConstruct {

    int constructReInvestAndNewRecord(Date recordBeginDatetime, Date recordEndDatetime);

}
