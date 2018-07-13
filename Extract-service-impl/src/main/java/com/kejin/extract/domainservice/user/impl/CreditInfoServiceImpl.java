package com.kejin.extract.domainservice.user.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kejin.extract.domainservice.user.CreditInfoService;
import com.kejin.extract.entity.service.dynamic.CreditInfoModel;
import com.kejin.extract.entity.service.dynamic.RelationBidModel;
import com.kejin.extract.entity.service.dynamic.RelationCreditAssignmentModel;
import com.kejin.extract.entity.service.dynamic.RelationUnpaiInterest;
import com.kejin.extract.entity.service.util.UserCredit;
import com.kejin.extract.kejin.service.dao.UserDetailInfoDao;
import com.kejin.extract.mmmoney.service.dao.InvestDetailDao;
import com.kejin.extract.mmmoney.service.dao.UserBaseInfoDetailDao;

@Service("creditInfoService")
public class CreditInfoServiceImpl implements CreditInfoService  {
	
	@Autowired
	private UserDetailInfoDao userDetailInfoDao;
	@Autowired
	private UserBaseInfoDetailDao userBaseInfoDetailDao;
	@Autowired
	private InvestDetailDao investDetailDao;

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.user.service.impl.CreditInfoService#generateCreditInfo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String generateCreditInfo(String memberId){
		List<CreditInfoModel>  infos = investDetailDao.queryAllCredit(memberId);
		
		if(infos != null && infos.size() > 0){
			Map<String,UserCredit> userCredits = new HashMap<String,UserCredit>();
			
			Set<String> subjectNos = new HashSet<String>();
			Set<String> inCredits = new HashSet<String>();
			Set<String> creditIds = new HashSet<String>();
			
			//归纳债权信息
			Map<String,String> caRelationSubjectNO = new HashMap<String,String>();
			
			for(CreditInfoModel c : infos){
				String subjectNo = c.getSubjectNo();
				String inCredit  = c.getBidOrderNo();
				String creditId = c.getCreditId();
				
				UserCredit uc = new UserCredit();
				
				if(subjectNos.contains(subjectNo)){
					uc = userCredits.get(subjectNo);
				}else{
					uc = new UserCredit();
					uc.setSubjectNo(c.getSubjectNo());
					uc.setSubjectName(c.getSubjectName());
					uc.setStatus(c.getStatus());
					
					subjectNos.add(subjectNo);
					uc.setTerm(c.getTerm());
					
					userCredits.put(subjectNo, uc);				
				}
				
				if(!creditIds.contains(creditId)){
					creditIds.add(creditId);
					uc.setCreditAmout(uc.getCreditAmout().add(c.getAmount()));
				}
				
				if(c.getInterest()!=null){
					uc.setRecoveryInterest(uc.getRecoveryInterest().add(c.getInterest()));
				}
				
				if(inCredit!=null){
					inCredits.add(inCredit);
					caRelationSubjectNO.put(inCredit, subjectNo);
				}
			}
			
			//获取首次投资的信息
			List<RelationBidModel>  relationBids = investDetailDao.queryRelationBid(memberId, subjectNos,inCredits);
			
			for(RelationBidModel r: relationBids){
				
				if(r.getSubjectType().equalsIgnoreCase("SUBJECT")){
					String subjectNo = r.getSubjectNo();
					
					UserCredit uc = userCredits.get(subjectNo);
					uc.setOriginInvestAmout(uc.getOriginInvestAmout().add(r.getAmount()));
					
					if(uc.getInvestDate()==null){
						uc.setInvestDate(r.getCreateTime());
					}
				}else if(r.getSubjectType().equalsIgnoreCase("CREDIT")){
					
					String inCredit = r.getBidNo();
					String subjectNo = caRelationSubjectNO.get(inCredit);
					
					if(subjectNo!=null){
						UserCredit uc = userCredits.get(subjectNo);					
						uc.setInCredit(uc.getInCredit().add(r.getAmount()));
						if(uc.getInvestDate()==null){
							uc.setInvestDate(r.getCreateTime());
						}
					}
				}
				
			}
			
			//查找对应的债权转让	
			List<RelationCreditAssignmentModel>  relationCAs = investDetailDao.queryRelationCreditAssignment(memberId, subjectNos);
			
			for(RelationCreditAssignmentModel r : relationCAs){
				String subjectNo = r.getSubjectNo();
				UserCredit uc = userCredits.get(subjectNo);
				
				uc.setOutCredit(uc.getOutCredit().add(r.getAssignmentCreditAmount()));
				
			}
			
			//查找对应的未还利息
			List<RelationUnpaiInterest>  relationUnpaiInterests = investDetailDao.queryRelationUnpaiInterest(memberId);
			
			for(RelationUnpaiInterest r : relationUnpaiInterests){
				String subjectNo = r.getSubjectNo();
				UserCredit uc = userCredits.get(subjectNo);			
				uc.setUnpaidInterest(uc.getUnpaidInterest().add(r.getUnpaiInterest()));
			}
			
			//排序
			List<UserCredit> userCreditList = new ArrayList<UserCredit>();
			for(String c : subjectNos){
				
				UserCredit  uc =userCredits.get(c);
				
				userCreditList.add(uc);
			}
			
			Collections.sort(userCreditList);
			
			return 	JSON.toJSONStringWithDateFormat(userCreditList,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
		}
		return null;
	}
	
}
