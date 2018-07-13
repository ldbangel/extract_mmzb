package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DReInvestAndNewModel;

/**
 * @Author Leo
 */
public interface DReInvestAndNewDao {

    /**
     * 插入单笔记录
     */
    int insert(DReInvestAndNewModel dRegularRecoveryModel);

    /**
     * 插入多笔记录
     */
    int insertList(@Param("reInvestAndNewModels") List<DReInvestAndNewModel> reInvestAndNewModels);

    /**
     * 更新记录
     */
    int updateAmounts(DReInvestAndNewModel dReInvestAndNewModel);

    /**
     * 根据交易类型查找最新的时间
     */
    Date getLatestDate(String transactionType);

    /**
     * 查找某个时间段內所有有投資或者有回款记录的的memberId的集合。
     */
    List<String> findMemberIdsByRangeDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    /**
     * 查找某个memberId在某个时间段的所有投资和回款记录
     */
    List<DReInvestAndNewModel> selectByMemberIdAndEndDate(@Param("memberId") String memberId,
                                                          @Param("endDate") Date endDate,
                                                          @Param("startDate") Date startDate);
    
    /**
     * 2018-01-20修复数据时，插入一条数据，要把插入记录前面最靠近的记录以及后面的所有记录查出来，并修正数据
     */
    List<DReInvestAndNewModel> selectForFixData(@Param("memberId") String memberId,
            @Param("endDate") Date endDate,
            @Param("startDate") Date startDate);
                                                          
    
    
    /**
     * 删除BID_FAIL的定期宝数据
     */
    int deleteBidFailData(@Param("orderNo") String orderNo);
}
