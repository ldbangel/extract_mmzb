package com.kejin.extract.domainservice.construct.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kejin.extract.domainservice.construct.MemberInfoConstruct;
import com.kejin.extract.domainservice.extract.MemberInfoService;
import com.kejin.extract.entity.kejinTest.DUserModel;
import com.kejin.extract.kejin.process.dao.DUserDao;

@Service("memberInfoConstruct")
public class MemberInfoConstructImpl implements  MemberInfoConstruct  {
	@Autowired
	private DUserDao dUserDao;
	@Resource(name="memberInfoService")
	private MemberInfoService memberInfoService;
	
	@Override
	@Transactional
	public int constructUser(Date recordBeginDatetime ,Date recordEndDatetime){
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {
			
			HashMap<String, DUserModel> memToUser = new HashMap<String, DUserModel>();
			// 处理业务逻辑
			// 数据的第一步必须从memberId读起,数据库最早有的是memberId
			List<DUserModel> users = memberInfoService
					.readFromMemberInfo(recordBeginDatetime, recordEndDatetime, continueRead);
			memberInfoService.combineUsers(memToUser, users);

			/*users = memberInfoService
					.readFromLoan(recordBeginDatetime,recordEndDatetime,continueRead);
			memberInfoService.combineUsers(memToUser, users);*/

			users = memberInfoService
					.readFromMemberBank(recordBeginDatetime,recordEndDatetime,continueRead);
			memberInfoService.combineUsers(memToUser, users);

			users = memberInfoService
					.readFromMemberIdentity(recordBeginDatetime, recordEndDatetime,continueRead);
			memberInfoService.combineUsers(memToUser, users);

			users = memberInfoService
					.readFromInvestBid(recordBeginDatetime,recordEndDatetime,continueRead);
			memberInfoService.combineUsers(memToUser, users);
			
			users = memberInfoService
					.readFromVerify(recordBeginDatetime,recordEndDatetime,continueRead);
			memberInfoService.combineUsers(memToUser, users);
			
			if(memToUser.size()>0){
				continueRead++;
			}else{
				continueRead =-1;
			}
			
			//HashMap<String, DUserModel> userModelMap = new HashMap<String, DUserModel>();

			Set<String> ids = memToUser.keySet();

			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("memberIds", ids);
            
			StringBuffer mIds = new StringBuffer();
//			List<String> mIds = new ArrayList<String>();
			
			if(ids.size()>0){
				List<DUserModel> models = dUserDao.select(parameter);
			
				for (DUserModel md : models) {
//					mIds.add(md.getMemberId());
//					userModelMap.put(md.getMemberId(), md);
					mIds.append(md.getMemberId()+"_");
				}
			}	
			String mids = mIds.toString();

			/**
			 * d_user表update和insert规则
			 * 1:时间段内增加的人员对应的memberId不在d_user表中，则直接插入
			 * 2:时间段内增加的人员的memberId在d_user表中
			 * 	当相应memberId数据库里的user_role不为null,
			 * 	并且要插入的数据user_role也不为null,
			 * 	并且d_user库里面的user_role不等于要插入的数据user_role的时候才insert,
			 * 	否则全为update
			 */
			for (String id : ids) {
				DUserModel user = memToUser.get(id);
				if (mids.contains(id)) {
					List<DUserModel> userModels = dUserDao.selectByMemberId(id);
					StringBuffer userRoles = new StringBuffer();
					
					if(userModels != null && userModels.size()>0){
						for(DUserModel model : userModels){
							if(model.getUserRole()!=null){
								userRoles.append(model.getUserRole()+"_");
							}
						}
					}
				
					if(user.getRecommandFriendCode() != null
							&& user.getPhone() == null
							&& user.getBankCardNum() == null
							&& user.getFirstInvestAmount() == null){
						dUserDao.updateRecommandFcode(user);
					}else{
						if(userRoles != null && !"".equals(userRoles.toString())
								&& user.getUserRole() != null  && !"".equals(user.getUserRole())
								&& !userRoles.toString().contains(user.getUserRole())){
							dUserDao.insert(user);
						}else{
							dUserDao.update(user);
						}
					}
					
				} else {
					dUserDao.insert(user);
				}
			}
			handlerCount = handlerCount + ids.size();
		}
		return handlerCount;
	}


}
