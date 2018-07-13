package com.kejin.extract.mmmoney.service.dao;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.service.dynamic.CreditInfoModel;
import com.kejin.extract.entity.service.dynamic.InvestBidDetailModel;
import com.kejin.extract.entity.service.dynamic.RecoveryDetailModel;
import com.kejin.extract.entity.service.dynamic.RelationBidModel;
import com.kejin.extract.entity.service.dynamic.RelationCreditAssignmentModel;
import com.kejin.extract.entity.service.dynamic.RelationUnpaiInterest;

public interface InvestDetailDao {
	
	public List<InvestBidDetailModel> querySubjectBid(@Param("memberId") String memberId,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<RecoveryDetailModel> queryRecoveryDetail(@Param("memberId") String memberId,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<RecoveryDetailModel> queryUnRecoveryDetail(@Param("memberId") String memberId,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	 
	public List<CreditInfoModel>  queryAllCredit(
			@Param("memberId") String memberId);
	
	public List<RelationBidModel>  queryRelationBid(
			@Param("memberId") String memberId,@Param("subjectSet") Set<String> subjectSet,@Param("bidSet") Set<String> bidSet);
	
	public List<RelationCreditAssignmentModel>  queryRelationCreditAssignment(
			@Param("memberId") String memberId,@Param("subjectSet") Set<String> subjectSet);

	public List<RelationUnpaiInterest>  queryRelationUnpaiInterest(
			@Param("memberId") String memberId);

}
