package com.kejin.extract.domainservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.dingding.AuthHelper;
import com.kejin.extract.domainservice.dingding.Department;
import com.kejin.extract.domainservice.dingding.DepartmentHelper;
import com.kejin.extract.domainservice.dingding.UserHelper;
import com.kejin.extract.domainservice.service.DingDingInfoService;
import com.kejin.extract.entity.kejinTest.DEmployeeModel;
import com.kejin.extract.entity.kejinTest.DUserModel;
import com.kejin.extract.entity.service.User;
import com.kejin.extract.kejin.process.dao.DEmployeeDao;
import com.kejin.extract.kejin.process.dao.DUserDao;

@Service("dingDingInfoService")
public class DingDingServiceInfoImpl implements DingDingInfoService {
	@Autowired
	private DEmployeeDao dEmployeeDao;
	@Autowired
	private DUserDao dUserDao;

	@Override
	public void SyncDingDing2Kejin() {
		List<User> dingUserList = getDingUsers();
		List<DEmployeeModel> employeeList = dEmployeeDao.selectActiveUser();
		/**
		 * 钉钉通讯录同步规则
		 * 1、钉钉通讯录有的而员工表里面没有的，这部分人员应该属于新增员工
		 * 2、钉钉通讯录没有而员工表里面有的，这部分人员应该属于离职人员
		 * 
		 * 所以两者都拥有的这部分人员是不需要处理的
		 */
		StringBuffer sb = new StringBuffer();
		//找出钉钉和kejin数据库共同拥有的人员名单
		if(dingUserList!= null && employeeList!= null){
			for(User user : dingUserList){
				if(user.getMobile() != null && user.getName() != null){
					for(DEmployeeModel model : employeeList){
						if(user.getMobile().equals(model.getPhone()) 
								&& user.getName().equals(model.getName())){
							sb.append(user.getMobile()+",");
						}
					}
				}
			}
		}
		
		List<User> newUsers = new ArrayList<User>();
		List<DEmployeeModel> updateUsers = new ArrayList<DEmployeeModel>();
		if(sb != null){
			for(User user : dingUserList){
				if(!sb.toString().contains(user.getMobile())){
					newUsers.add(user);
				}
			}
			for(DEmployeeModel model : employeeList){
				if(!sb.toString().contains(model.getPhone())){
					updateUsers.add(model);
				}
			}
		}
		
		if(newUsers != null && newUsers.size() > 0){
			List<DEmployeeModel> insertEmployees = new ArrayList<DEmployeeModel>();
			//List<DEmployeeModel> insertEmployeesNoRegister = new ArrayList<DEmployeeModel>();
			//获取新增人员中原来在employee表中，状态为status=0的人员名单
			List<DEmployeeModel> historyEmployees = dEmployeeDao.selectHistoryEmployee(newUsers);
			
			//获取新增人员并且在钱包注册过的人员
			List<User> newUsersExceptHistory = new ArrayList<User>();
			StringBuffer historyPhones = new StringBuffer();
			for(DEmployeeModel employee : historyEmployees){
				historyPhones.append(employee.getPhone()).append(",");
			}
			for(User user : newUsers){
				if(!historyPhones.toString().contains(user.getMobile())){
					newUsersExceptHistory.add(user);
				}
			}
			List<DUserModel> userListWithMemberId = dUserDao.selectByPhone(newUsersExceptHistory);
			
			//获取新增人员但未在钱包注册的人员--暂时不作处理
			List<User> newUsersNoRegister = new ArrayList<User>();
			StringBuffer withMemberIdPhones = new StringBuffer();
			for(DUserModel user : userListWithMemberId){
				withMemberIdPhones.append(user.getPhone()).append(",");
			}
			for(User user : newUsersExceptHistory){
				if(!withMemberIdPhones.toString().contains(user.getMobile())){
					newUsersNoRegister.add(user);
				}
			}
			
			if(userListWithMemberId != null && userListWithMemberId.size() > 0){
				for(DUserModel model : userListWithMemberId){
					for(User user : newUsers){
						if(model.getPhone().equals(user.getMobile())){
							DEmployeeModel employee = new DEmployeeModel();
							employee.setName(user.getName());
							employee.setPhone(model.getPhone());
							employee.setfCode(model.getFriendCode());
							employee.setMemberId(model.getMemberId());
							employee.setDepartment("");
							if("深圳".equals(user.getWorkPlace()) || user.getWorkPlace()==null){
								employee.setZone(0);
							}else if("广州".equals(user.getWorkPlace())){
								employee.setZone(1);
							}else if("西安".equals(user.getWorkPlace())){
								employee.setZone(2);
							}else{
								employee.setZone(0);
							}
							if(user.getPosition()!=null){
								employee.setDuty(user.getPosition());
								if("高级理财经理".equals(user.getPosition())
										|| "理财经理".equals(user.getPosition())
										|| "财富管理总经理".equals(user.getPosition())){
									employee.setPersonType("业务人员");
								}else{
									employee.setPersonType("非业务人员");
								}
							}else{
								employee.setDuty("");
								employee.setPersonType("非业务人员");
							}
							insertEmployees.add(employee);
						}
					}
				}
			}
			if(historyEmployees!=null && historyEmployees.size() > 0){
				dEmployeeDao.updateStatus2Active(historyEmployees);
			}
			if(insertEmployees!=null && insertEmployees.size() > 0){
				dEmployeeDao.insertEmployee(insertEmployees);
			}
		}
		
		if(updateUsers != null && updateUsers.size() > 0){
			dEmployeeDao.updateStatus(updateUsers);
		}
	}
	
	private static List<User> getDingUsers(){
		String accessToken = AuthHelper.getAccessToken();
		List<Department> departmentList = DepartmentHelper.listDepartments(accessToken);
		List<User> userList = new ArrayList<User>();
		StringBuffer sb = new StringBuffer();
    	for(Department depart : departmentList){
    		List<User> users = UserHelper.getDepartmentUsersDetails(accessToken, depart.getId().toString());
    		if(users != null && users.size() > 0){
    			for(User user : users){
    				if(userList != null && userList.size() > 0 && sb.toString().contains(user.getUserid())){
    					continue;
    				}else{
    					userList.add(user);
    					sb.append(user.getUserid()+",");
    				}
    			}
    		}
    	}
    	return userList;
	}
	
	
	public static void main(String[] args) {
		List<User> users = getDingUsers();
		System.out.println("this is all members:" + users.size());
	}
	
}
