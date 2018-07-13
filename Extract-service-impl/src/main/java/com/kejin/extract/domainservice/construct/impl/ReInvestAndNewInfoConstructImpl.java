package com.kejin.extract.domainservice.construct.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kejin.extract.common.enums.TransactionTypeEnum;
import com.kejin.extract.domainservice.construct.ReInvestAndNewInfoConstruct;
import com.kejin.extract.entity.kejinTest.DCreditAssigmentModel;
import com.kejin.extract.entity.kejinTest.DCurrentInvetModel;
import com.kejin.extract.entity.kejinTest.DCurrentRecoveryModel;
import com.kejin.extract.entity.kejinTest.DReInvestAndNewModel;
import com.kejin.extract.entity.kejinTest.DRegularInvestModel;
import com.kejin.extract.entity.kejinTest.DRegularRecoveryModel;
import com.kejin.extract.kejin.process.dao.DCreditAssignmentDao;
import com.kejin.extract.kejin.process.dao.DReInvestAndNewDao;
import com.kejin.extract.kejin.process.dao.DRegularInvestDao;
import com.kejin.extract.kejin.process.dao.DRegularRecoveryDao;

/**
 * @Author Leo
 * ReInvestAndNewModel数据的获取和插入
 */
@Service("reInvestAndNewInfoConstruct")
public class ReInvestAndNewInfoConstructImpl implements ReInvestAndNewInfoConstruct {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
    private DCreditAssignmentDao dCreditAssignmentDao;
	@Autowired
    private DRegularRecoveryDao dRegularRecoveryDao;
	@Autowired
    private DRegularInvestDao dRegularInvestDao;
	@Autowired
    private DReInvestAndNewDao dReInvestAndNewDao;

    @Override
    @Transactional
    public int constructReInvestAndNewRecord(Date recordBeginDatetime, Date recordEndDatetime) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
       /* //活期回款
        List<DCurrentRecoveryModel> currentRecoveryList = dCurrentRecoveryDao.selectByRangeTime(recordBeginDatetime, recordEndDatetime);
        list.addAll(buildFromCurrentRecoveryList(currentRecoveryList));
        //活期投资
        List<DCurrentInvetModel> currentInvetList = dCurrentInvestDao.selectByRangeTime(recordBeginDatetime, recordEndDatetime);
        list.addAll(buildFromCurrentInvestList(currentInvetList));*/
        //债权转入转出
        List<DCreditAssigmentModel> creditAssigmentList = dCreditAssignmentDao.selectByRangeTime(recordBeginDatetime, recordEndDatetime);
        list.addAll(buildFromCreditAssigmentList(creditAssigmentList));
        //定期回款
        List<DRegularRecoveryModel> regularRecoveryList = dRegularRecoveryDao.selectByRangeTime(recordBeginDatetime, recordEndDatetime);
        list.addAll(buildFromRegularRecoveryList(regularRecoveryList));
        //定期投资
        List<DRegularInvestModel> regularInvestList = dRegularInvestDao.selectByRangeTime(recordBeginDatetime, recordEndDatetime);
        list.addAll(buildFromRegularInvestList(regularInvestList));

        int returnValue = 0;
        if (list.size() > 0) {
            returnValue = dReInvestAndNewDao.insertList(list);
            updateAmountsByRangeDate(recordBeginDatetime, recordEndDatetime);
        }
        
        
        return returnValue;
    }

    private List<DReInvestAndNewModel> buildFromCurrentRecoveryList(List<DCurrentRecoveryModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DCurrentRecoveryModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildFromCurrentRecovery(model));
            }
        }
        return list;
    }

    private List<DReInvestAndNewModel> buildFromCurrentInvestList(List<DCurrentInvetModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DCurrentInvetModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildFromCurrentInvest(model));
            }
        }
        return list;
    }

    private List<DReInvestAndNewModel> buildFromCreditAssigmentList(List<DCreditAssigmentModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DCreditAssigmentModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildAssignRecordFromCreditAssigment(model));
                list.add(DReInvestAndNewModel.buildTransferRecordFromCreditAssigment(model));
            }
        }
        return list;
    }

    private List<DReInvestAndNewModel> buildFromRegularRecoveryList(List<DRegularRecoveryModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DRegularRecoveryModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildFromRegularRecovery(model));
            }
        }
        return list;
    }

    private List<DReInvestAndNewModel> buildFromRegularInvestList(List<DRegularInvestModel> currentRecoveryList) {
        List<DReInvestAndNewModel> list = new ArrayList<DReInvestAndNewModel>();
        if (currentRecoveryList != null && currentRecoveryList.size() > 0) {
            for (DRegularInvestModel model : currentRecoveryList) {
                list.add(DReInvestAndNewModel.buildFromRegularInvest(model));
            }
        }
        return list;
    }

    private void updateAmountsByRangeDate(Date startDate, Date endDate) {
        List<String> memberIds = dReInvestAndNewDao.findMemberIdsByRangeDate(startDate, endDate);
        if (memberIds != null && memberIds.size() > 0) {
            for (String memberId : memberIds) {
                List<DReInvestAndNewModel> models = dReInvestAndNewDao.selectByMemberIdAndEndDate(memberId, endDate, startDate);
                if (models != null && models.size() > 0) {
                    for (int i = 0; i < models.size(); i++) {
                        DReInvestAndNewModel m = models.get(i);
                        if (m.getRecoveryTotal() == null && m.getReinvestAmount() == null && m.getNewAmount() == null) {
                            //需要进行更新
                            if (i == 0) {
                                //第一笔记录
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
    }
}
