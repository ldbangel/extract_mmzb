package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @Author Leo
 */
public interface DNewInvestReaderDao {
    public List<Map<String, Object>> readFromBidOrder(
            @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
}
