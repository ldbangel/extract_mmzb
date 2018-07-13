package com.kejin.extract.domainservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.kejin.extract.common.enums.TransactionTypeEnum;
import com.kejin.extract.common.utils.JsonUtil;
import com.kejin.extract.domainservice.construct.BonusInfoConstruct;
import com.kejin.extract.domainservice.construct.CashInfoConstruct;
import com.kejin.extract.domainservice.construct.ChargeInfoConstruct;
import com.kejin.extract.domainservice.construct.CreditAssignmentInfoConstruct;
import com.kejin.extract.domainservice.construct.CurrentInvestInfoConstruct;
import com.kejin.extract.domainservice.construct.CurrentRecoveryInfoConstruct;
import com.kejin.extract.domainservice.construct.MemberInfoConstruct;
import com.kejin.extract.domainservice.construct.NewInvestInfoConstruct;
import com.kejin.extract.domainservice.construct.ProductInfoConstruct;
import com.kejin.extract.domainservice.construct.ReInvestAndNewInfoConstruct;
import com.kejin.extract.domainservice.construct.RegularInvestInfoConstruct;
import com.kejin.extract.domainservice.construct.RegularRecoveryInfoConstruct;
import com.kejin.extract.domainservice.service.FixDataService;
import com.kejin.extract.entity.kejinTest.DActionAssistModel;
import com.kejin.extract.entity.kejinTest.DCreditAssigmentModel;
import com.kejin.extract.entity.kejinTest.DReInvestAndNewModel;
import com.kejin.extract.entity.kejinTest.DRegularInvestModel;
import com.kejin.extract.entity.kejinTest.DRegularRecoveryModel;
import com.kejin.extract.entity.kejinTest.DUserModel;
import com.kejin.extract.kejin.process.dao.DActionAssistDao;
import com.kejin.extract.kejin.process.dao.DCreditAssignmentDao;
import com.kejin.extract.kejin.process.dao.DReInvestAndNewDao;
import com.kejin.extract.kejin.process.dao.DRegularInvestDao;
import com.kejin.extract.kejin.process.dao.DRegularRecoveryDao;
import com.kejin.extract.kejin.process.dao.DUserDao;
import com.kejin.extract.mmmoney.reader.dao.DCreditAssignmentReaderDao;
import com.kejin.extract.mmmoney.reader.dao.DRegularInvestReaderDao;
import com.kejin.extract.mmmoney.reader.dao.DUserReaderDao;

@Service("fixDataService")
public class FixDataServiceImpl implements FixDataService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DActionAssistDao dActionAssistDao;
	@Autowired
	private DRegularInvestDao dRegularInvestDao;
	@Autowired
	private DRegularRecoveryDao dRegularRecoveryDao;
	@Autowired
	private DRegularInvestReaderDao dRegularInvestReaderDao;
	@Autowired
	private DReInvestAndNewDao dReInvestAndNewDao;
	@Autowired
	private DCreditAssignmentReaderDao dCreditAssignmentReaderDao;
	@Autowired
	private DCreditAssignmentDao dCreditAssignmentDao;
	@Autowired
	private DUserReaderDao dUserReaderDao;
	@Autowired
	private DUserDao dUserDao;
	
	
	@Resource(name="bonusInfoConstruct")
	private BonusInfoConstruct bonusInfoConstruct;
	@Resource(name="cashInfoConstruct")
	private CashInfoConstruct cashInfoConstruct;
	@Resource(name="chargeInfoConstruct")
	private ChargeInfoConstruct chargeInfoConstruct;
	@Resource(name="creditAssignmentInfoConstruct")
	private CreditAssignmentInfoConstruct creditAssignmentInfoConstruct;
	@Resource(name="currentInvestInfoConstruct")
	private CurrentInvestInfoConstruct currentInvestInfoConstruct;
	@Resource(name="currentRecoveryInfoConstruct")
	private CurrentRecoveryInfoConstruct currentRecoveryInfoConstruct;
	@Resource(name="memberInfoConstruct")
	private MemberInfoConstruct memberInfoConstruct;
	@Resource(name="newInvestInfoConstruct")
	private NewInvestInfoConstruct newInvestInfoConstruct;
	@Resource(name="productInfoConstruct")
	private ProductInfoConstruct productInfoConstruct;
	@Resource(name="regularInvestInfoConstruct")
	private RegularInvestInfoConstruct regularInvestInfoConstruct;
	@Resource(name="regularRecoveryInfoConstruct")
	private RegularRecoveryInfoConstruct regularRecoveryInfoConstruct;
	@Resource(name="reInvestAndNewInfoConstruct")
	private ReInvestAndNewInfoConstruct reInvestAndNewInfoConstruct;
	
	@Override
	public String actionAssistOutputFail(Date fixBeginTime) {
		HashMap<String,Object> filterParameter = new HashMap<String,Object>();
		filterParameter.put("beginTime", fixBeginTime);
		
		List<DActionAssistModel> actions = dActionAssistDao.selectOutputFail(filterParameter);
		for(DActionAssistModel assist : actions ){			
			try{
				if("d_cash".equals(assist.getHandleData())){
					cashInfoConstruct.constructCash(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_credit_assignment".equals(assist.getHandleData())){
					creditAssignmentInfoConstruct.constructCreditAssignment(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_current_invest".equals(assist.getHandleData())){
					currentInvestInfoConstruct.constructCurrentInvest(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_bonus".equals(assist.getHandleData())){
					bonusInfoConstruct.constructBonus(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_current_recovery".equals(assist.getHandleData())){
					currentRecoveryInfoConstruct.constructRegularRecovery(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_regular_invest".equals(assist.getHandleData())){
					regularInvestInfoConstruct.constructRegularInvest(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_new_invest".equals(assist.getHandleData())){
					newInvestInfoConstruct.constructNewInvest(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_product".equals(assist.getHandleData())){
					productInfoConstruct.constructProduct(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_charge".equals(assist.getHandleData())){
					chargeInfoConstruct.constructCharge(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_regular_recovery".equals(assist.getHandleData())){
					regularRecoveryInfoConstruct.constructRegularRecovery(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_user".equals(assist.getHandleData())){
					memberInfoConstruct.constructUser(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}else if("d_reinvest_and_new".equals(assist.getHandleData())){
					reInvestAndNewInfoConstruct.constructReInvestAndNewRecord(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
				}
				assist.setEndDatetime(new Date());
				assist.setOutput("success");
			}catch(Exception ex){
				ex.printStackTrace();
				assist.setEndDatetime(new Date());
				assist.setOutput("fail");
				assist.setReason("may reason"+ex.getMessage());
			}
			dActionAssistDao.update(assist);	
		}
		return null;
	}

	@Override
	/**
	 * 关于bid_fail数据的修复规则
	 * 1、先查时间段内d_regular_invest表中bid_fail的数据
	 * 2、遍历数据集，在d_reinvest_and_new表中一条一条删除数据
	 * 3、然后找bid_fail数据的memberId和operationDate之前的最后一条(若有)数据以及之后的所有数据
	 * 4、若第一条数据的operationDate小于删除的数据的operationDate,并且删除时间点后面也有数据,则根据第一条数据逐一计算后面的数据
	 * 5、若在4条件基础上,但是删除数据时间点后面没有数据(也就是说删除的数据是该memberId的最后一条数据),则不用处理
	 * 6、若第一条数据的operationDate大于删除的数据的operationDate,说明删除数据其实是第一条数据,删除之后数据集中第一条数据作为第一条数据，后面数据依此计算就好
	 */
	public int fixReinvestAndNewBidFail(Date beginTime, Date endTime) {
		List<DRegularInvestModel> list = dRegularInvestDao.selectBidFailByRangeTime(beginTime, endTime);
		int count = 0;
		for(DRegularInvestModel model : list){
			int result = dReInvestAndNewDao.deleteBidFailData(model.getBidOrderNo());
			if(result > 0){
				count++;
				List<DReInvestAndNewModel> models = dReInvestAndNewDao
						.selectByMemberIdAndEndDate(model.getMemberId(), new Date(), model.getOperationDate());
				if (models != null && models.size() > 0) {
					if(models.get(0).getOperationDate().getTime() < model.getOperationDate().getTime()){
						//取出时间段前的最后一条数据，时间小于删除数据的时间，并且删除后面还有数据，说明需要更新后面的数据
						if(models.size()>=2){
							for (int i = 1; i < models.size(); i++) {
								DReInvestAndNewModel m = models.get(i);
								//并非第一笔记录
								DReInvestAndNewModel mBefore = models.get(i - 1);
								if (TransactionTypeEnum.isInvest(m.getTransactionType())) {
									//记录为投资
									if (mBefore.getRecoveryTotal() == null || mBefore.getRecoveryTotal().compareTo(m.getAmount()) <= 0) {
										m.setRecoveryTotal(new BigDecimal(0));
										m.setReinvestAmount(mBefore.getRecoveryTotal());
										if (mBefore.getRecoveryTotal()==null){
											m.setNewAmount(m.getAmount());
										} else {
											m.setNewAmount(m.getAmount().subtract(mBefore.getRecoveryTotal()));
										}
									} else {
										m.setRecoveryTotal(mBefore.getRecoveryTotal().subtract(m.getAmount()));
										m.setReinvestAmount(m.getAmount());
										m.setNewAmount(null);
									}
								} else {
									//记录为回款
									if (mBefore.getRecoveryTotal()==null){
										m.setRecoveryTotal(m.getAmount());
									} else {
										m.setRecoveryTotal(mBefore.getRecoveryTotal().add(m.getAmount()));
									}
								}
								
								dReInvestAndNewDao.updateAmounts(m);
							}
						}
					}else{
						for (int i = 0; i < models.size(); i++) {
							DReInvestAndNewModel m = models.get(i);
							if (i == 0) {
								m.setNewAmount(null);
								m.setRecoveryTotal(null);
								m.setReinvestAmount(null);
								//记为第一笔记录
								if (TransactionTypeEnum.isInvest(m.getTransactionType())) {
									m.setNewAmount(m.getAmount());
								} else {
									m.setRecoveryTotal(m.getAmount());
								}
							} else {
								//并非第一笔记录
								DReInvestAndNewModel mBefore = models.get(i - 1);
								if (TransactionTypeEnum.isInvest(m.getTransactionType())) {
									//记录为投资
									if (mBefore.getRecoveryTotal() == null || mBefore.getRecoveryTotal().compareTo(m.getAmount()) <= 0) {
										m.setRecoveryTotal(new BigDecimal(0));
										m.setReinvestAmount(mBefore.getRecoveryTotal());
										if (mBefore.getRecoveryTotal()==null){
											m.setNewAmount(m.getAmount());
										} else {
											m.setNewAmount(m.getAmount().subtract(mBefore.getRecoveryTotal()));
										}
									} else {
										m.setRecoveryTotal(mBefore.getRecoveryTotal().subtract(m.getAmount()));
										m.setReinvestAmount(m.getAmount());
										m.setNewAmount(null);
									}
								} else {
									//记录为回款
									if (mBefore.getRecoveryTotal()==null){
										m.setRecoveryTotal(m.getAmount());
									} else {
										m.setRecoveryTotal(mBefore.getRecoveryTotal().add(m.getAmount()));
									}
								}
							}
							dReInvestAndNewDao.updateAmounts(m);
						}
					}
				}
			}
		}
		return count;
	}

	@Override
	/**
	 * 2018-01-20
	 * 修复d_bid_order插入到d_regular_invest部分遗漏数据(2018/01/16 00:00:00--2018/01/19 00:00:00)
	 * 插入的数据同时插入到reinvest_and_new表中，并且修复该记录以及后面的数据
	 * 修复规则：
	 * 1、在t_bid_order表中查询该事件段内所有定期投资的数据
	 * 2、根据查询出的结果，拿到bidOrderNo去d_regular_invest表中查询，若存在则不处理，不存在则插入
	 * 3、将插入d_regular_invest表的数据取出来并且插入到d_reinvest_and_new表中
	 * 4、将插入的数据逐条去修改该memberId和该记录后面的所有数据
	 */
	public int fixRegularInvest(Date beginTime, Date endTime) {
		int continueRead = 1;
	    
	    Set<String> targetBidOrders = new HashSet<String>();
	    
	    //第一部分
	    while(continueRead >= 0){
	    	int pageSize = 100;
	    	
	    	PageHelper.startPage(continueRead, pageSize);
	    	List<Map<String, Object>> bidInfos = dRegularInvestReaderDao
	    			.readCreateFromBidOrder(beginTime, endTime);
	    	
	    	if(bidInfos!=null && bidInfos.size() > 0){
	    		List<DRegularInvestModel> investInfos = new ArrayList<DRegularInvestModel>();
	    		//数据转换
	    		for(Map<String,Object> m : bidInfos){
	    			DRegularInvestModel bid = new DRegularInvestModel();
	    			
	    			bid.setBidOrderNo(m.get("BID_ORDER_NO") != null ? (String) m.get("BID_ORDER_NO") : null);
	    			bid.setMemberId(m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null);
	    			bid.setSubjectNo(m.get("SUBJECT_NO") != null ? (String) m.get("SUBJECT_NO") : null);
	    			bid.setSubjectName(m.get("SUBJECT_NAME") != null ? (String) m.get("SUBJECT_NAME") : null);
	    			bid.setSubjectLife(m.get("LOAN_TERM") != null ? (String) m.get("LOAN_TERM") : null);
	    			bid.setSubjectType(m.get("SUBJECT_TYPE") != null ? (String) m.get("SUBJECT_TYPE") : null);
	    			bid.setRate(m.get("REWARD_RATE") != null ? (BigDecimal) m.get("REWARD_RATE") : null);
	    			bid.setRepayType(m.get("REPAY_TYPE") != null ? (String) m.get("REPAY_TYPE") : null);
	    			bid.setAmount(m.get("AMOUNT") != null ? (BigDecimal) m.get("AMOUNT") : null);
	    			bid.setOperationType(m.get("SUBMIT_TYPE") != null ? (String) m.get("SUBMIT_TYPE") : null);
	    			bid.setOperationDate(m.get("CREATE_TIME") != null ? (Date) m.get("CREATE_TIME") : null);				
	    			bid.setOperationStatus(m.get("STATUS") != null ? (String) m.get("STATUS") : null);
	    			if(m.get("EXTENSION") != null){
						String platform = JsonUtil.getVauleFromJson("plantform", (String)m.get("EXTENSION"));
						bid.setPlatform(platform);
					}else{
						bid.setPlatform("");
					}
	    			investInfos.add(bid);
	    		}
	    		
	    		//数据插入d_regular_invest,已存在的数据不做处理，不存在的数据插入
	    		Set<String> bidOrderNos = new HashSet<String>();
	    		for (DRegularInvestModel invest : investInfos) {
	    			bidOrderNos.add(invest.getBidOrderNo());
	    		}
	    		Map<String, Object> parameter = new HashMap<String, Object>();
	    		parameter.put("bidOrderNos", bidOrderNos);
	    		List<DRegularInvestModel> oldInvests = dRegularInvestDao.select(parameter);
	    		bidOrderNos.clear();
	    		for (DRegularInvestModel oldInvest : oldInvests) {
	    			bidOrderNos.add(oldInvest.getBidOrderNo());
	    		}
	    		List<DRegularInvestModel> regularInvestList = new ArrayList<DRegularInvestModel>();
	    		for (DRegularInvestModel invest : investInfos) {
	    			if (bidOrderNos.contains(invest.getBidOrderNo()) == false) {
	    				regularInvestList.add(invest);
	    				targetBidOrders.add(invest.getBidOrderNo());
	    			}
	    		}
	    		if(regularInvestList!=null && regularInvestList.size()>0){
	    			dRegularInvestDao.insertList(regularInvestList);
	    		}
	    		continueRead ++ ;
	    	}else{
	    		continueRead = -1;
	    	}
	    }
	    
	    //第三部分
	    List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
	    Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("bidOrderNos", targetBidOrders);
		if(targetBidOrders!=null && targetBidOrders.size() >0){
			List<DRegularInvestModel> regularInvestList = dRegularInvestDao.select(parameter);
			list.addAll(buildFromRegularInvestList(regularInvestList));
		}
		fixReinvestAndNew(list);
		
		return 0;
	}
	
	
	/**
	 * 修复d_regular_recovery表中存在但不在d_reinvest_and_new表中的数据
	 * 1、先查询出d_regular_recovery表中存在但不在d_reinvest_and_new表中的数据
	 * 2、将查出来的所有数据插入到d_reinvest_and_new表中
	 * 3、逐条修复该记录memberId，此记录及该记录时间节点以后所有的数据
	 */
	@Override
	public int fixRegularRecovery(Date beginTime, Date endTime) {
		List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
		List<DRegularRecoveryModel> regularRecoveryList = dRegularRecoveryDao.selectNotInReinvestAndNew();
		list.addAll(buildFromRegularRecoveryList(regularRecoveryList));
		fixReinvestAndNew(list);
		return 0;
	}
	
	/**
	 * 1、先插入数据到d_reinvest_and_new表中
	 * 2、逐条修复该记录memberId，此记录及该记录时间节点以后所有的数据
	 */
	@Override
	public int fixReinvestAndNew(List<DReInvestAndNewModel> list) {
		for(DReInvestAndNewModel model : list){
			dReInvestAndNewDao.insert(model);
			
			List<DReInvestAndNewModel> models = dReInvestAndNewDao.selectForFixData(model.getMemberId(), new Date(), model.getOperationDate());
			if (models != null && models.size() > 0) {
				if(models.get(0).getOperationDate().getTime() == model.getOperationDate().getTime()){
					DReInvestAndNewModel m = models.get(0);
					m.setNewAmount(null);
					m.setRecoveryTotal(null);
					m.setReinvestAmount(null);
					//记为第一笔记录
					if (TransactionTypeEnum.isInvest(m.getTransactionType())) {
						m.setNewAmount(m.getAmount());
					} else {
						m.setRecoveryTotal(m.getAmount());
					}
					dReInvestAndNewDao.updateAmounts(m);
				}
				for (int i = 1; i < models.size(); i++) {
					DReInvestAndNewModel m = models.get(i);
					//并非第一笔记录
					DReInvestAndNewModel mBefore = models.get(i - 1);
					if (TransactionTypeEnum.isInvest(m.getTransactionType())) {
						//记录为投资
						if (mBefore.getRecoveryTotal() == null || mBefore.getRecoveryTotal().compareTo(m.getAmount()) <= 0) {
							m.setRecoveryTotal(new BigDecimal(0));
							m.setReinvestAmount(mBefore.getRecoveryTotal());
							if (mBefore.getRecoveryTotal()==null){
								m.setNewAmount(m.getAmount());
							} else {
								m.setNewAmount(m.getAmount().subtract(mBefore.getRecoveryTotal()));
							}
						} else {
							m.setRecoveryTotal(mBefore.getRecoveryTotal().subtract(m.getAmount()));
							m.setReinvestAmount(m.getAmount());
							m.setNewAmount(null);
						}
					} else {
						//记录为回款
						if (mBefore.getRecoveryTotal()==null){
							m.setRecoveryTotal(m.getAmount());
						} else {
							m.setRecoveryTotal(mBefore.getRecoveryTotal().add(m.getAmount()));
						}
					}
					
					dReInvestAndNewDao.updateAmounts(m);
				}
			}
		}
		return 0;
	}
	
	
	//将债权转让费补充起来
	@Override
	public int fixCreditPayfee() {
		List<Map<String, Object>> list = dCreditAssignmentReaderDao.readPayfeeFromBidOrder();
		List<DCreditAssigmentModel> creditModels = new ArrayList<DCreditAssigmentModel>();
		for(Map<String, Object> m : list){
			DCreditAssigmentModel model = new DCreditAssigmentModel();
			model.setBidOrderNo(m.get("BID_ORDER_NO") != null ? (String) m.get("BID_ORDER_NO") : null);
			model.setPayFee(m.get("PAY_FEE") != null ? (BigDecimal) m.get("PAY_FEE") : null);
			creditModels.add(model);
		}
		for(DCreditAssigmentModel creditModel : creditModels){
			dCreditAssignmentDao.update(creditModel);
		}
		return 0;
	}
	
	/**
	 * 存管上线同步平台账户和激活状态
	 */
	@Override
	@Transactional
	public void syncPlatformUserNo() {
		int continueRead = 0;
		int handlerCount = 0;
		
		while (continueRead >= 0) {
			logger.info("current page is : " + continueRead);
			
			PageHelper.startPage(continueRead, 100);
			List<Map<String, Object>> userInfos = dUserReaderDao.syncPlatformUserNo();
			
			if(userInfos.size() > 0){
				List<DUserModel> userModels = new ArrayList<DUserModel>();
				for(Map<String, Object> m : userInfos){
					DUserModel user = new DUserModel();
					user.setMemberId(m.get("memberId")!=null?(String)m.get("memberId"):null);
					user.setPlatformUserNo(m.get("platformUserNo")!=null?(String)m.get("platformUserNo"):null);
					user.setIsActivate(m.get("isImportUserActivate")!=null?(String)m.get("isImportUserActivate"):null);
					user.setUserRole(m.get("userRole")!=null?(String)m.get("userRole"):null);
					userModels.add(user);
				}
				logger.info("insert into d_user num is :"+ userModels.size());
				dUserDao.syncPlatformUserNo(userModels);
				
				handlerCount = handlerCount + userInfos.size();
				continueRead++;
			} else {
				continueRead = -1;
			}
		}
	}
	
	
	//转换regularRecovery数据成DRegularRecoveryModel数据
	private List<DReInvestAndNewModel> buildFromRegularRecoveryList(List<DRegularRecoveryModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DRegularRecoveryModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildFromRegularRecovery(model));
            }
        }
        return list;
    }

	//转换regularInvest数据成DRegularRecoveryModel数据
    private List<DReInvestAndNewModel> buildFromRegularInvestList(List<DRegularInvestModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DRegularInvestModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildFromRegularInvest(model));
            }
        }
        return list;
    }

}
