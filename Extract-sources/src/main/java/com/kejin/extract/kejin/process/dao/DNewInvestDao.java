package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DNewInvestModel;

/**
 * @Author Leo
 */
public interface DNewInvestDao {
    public List<DNewInvestModel> select(Map<String, Object> parameter);

    public List<DNewInvestModel> selectByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);

    public int insert(DNewInvestModel dNewInvestModel);

    public int update(DNewInvestModel dNewInvestModel);
}
