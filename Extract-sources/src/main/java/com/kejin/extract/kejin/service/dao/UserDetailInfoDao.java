package com.kejin.extract.kejin.service.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.service.CashInfoModel;
import com.kejin.extract.entity.service.ChargeInfoModel;
import com.kejin.extract.entity.service.UserBaseInfoModel;

public interface UserDetailInfoDao {
	
	public UserBaseInfoModel  queryUserBaseInfo(@Param("phone") String phone);
	
	
	public Map<String,BigDecimal> getAccumulateCash(@Param("memberId") String memberId);
	
	
	public Map<String,BigDecimal> getAccumulateCharge(@Param("memberId") String memberId);
	
	/**
	 * 充值明细
	 */
	public List<ChargeInfoModel> queryChargeInfo(@Param("memberId") String memberId,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	/**
	 * 提现明细
	 * @param memberId
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<CashInfoModel> queryCashInfo(@Param("memberId") String memberId,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/**
	 * 根据mobile修改financialManager字段值
	 */
	void updateFinancialManagerByPhone(@Param("phone") String mobile,
									   @Param("financialManager") String manager,
									   @Param("userSource") String userSource);

	/**
	 * 根据memberId修改financialManager字段值
	 */
	void updateFinancialManagerByMemberId(@Param("memberId")String memberId,
										  @Param("financialManager") String manager,
										  @Param("userSource") String userSource);

	/**
	 * 查看memberId是否存在
	 */
	boolean memberIdExists(@Param("memberId") String memberId);

	/**
	 * 根据memberId增加客户经理
	 */
	void addFinancialManagerByMemberId(@Param("memberId")String memberId,
									   @Param("financialManager") String manager,
									   @Param("userSource") String userSource);

	/**
	 * 根据phone增加客户经理
	 */
	void addFinancialManagerByPhone(@Param("phone") String mobile,
									@Param("financialManager") String manager,
									@Param("userSource") String userSource);

}
