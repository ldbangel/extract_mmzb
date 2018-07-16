package com.kejin.extract.domainservice.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.common.Excel2AccountBalanceUtil;
import com.kejin.extract.domainservice.service.ThreadService;
import com.kejin.extract.integration.custody.CustodyMemberService;
import com.kejin.extract.kejin.process.dao.DUserDao;
import com.kejin.extract.mmmoney.service.dao.AchievementManagerFromProdDao;
import com.mmzb.custody.shbk.service.request.EnterpriseInfoRequest;
import com.mmzb.custody.shbk.service.request.QueryUserInfoRequest;
import com.mmzb.custody.shbk.service.response.EnterpriseInfoResponse;
import com.mmzb.custody.shbk.service.response.UserInformationResponse;

/**
 * 多线程任务
 * @author liudongbo
 *
 */
@Service("threadService")
public class ThreadServiceImpl implements ThreadService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "custodyMemberService")
	private CustodyMemberService custodyMemberService;
	@Resource(name = "achievementManagerFromProdDao")
	private AchievementManagerFromProdDao achievementManagerFromProdDao;
	@Autowired
	private DUserDao dUserDao;
	
	@Override
	public BigDecimal exportMemberBalanceExcel() {
		List<UserInformationResponse> userResponseList = getMemberInfoFromCustody();
		long endTime = System.currentTimeMillis();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		BigDecimal allAmount = new BigDecimal(0);
		for(UserInformationResponse user : userResponseList){
			//筛选可用余额大于1000的
			if(user.getAvailableAmount()!=null){
				BigDecimal balance = new BigDecimal(user.getAvailableAmount());
				allAmount = allAmount.add(balance);
				if(balance.compareTo(new BigDecimal(1000))>=0){
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("balance", user.getAvailableAmount());
					map.put("platformUserNo", user.getPlatformUserNo());
					map.put("authName", user.getName());
					map.put("phoneNum", user.getMobile());
					result.add(map);
				}
			}
		}
		logger.info("余额总和为:" + allAmount);
		long endTime1 = System.currentTimeMillis();
		logger.info("循环时间为："+(endTime1-endTime));
		
		List<Map<String, Object>> managerMaps = dUserDao.selectByPlatUserNo(result);
		List<Map<String,String>> phoneNums = achievementManagerFromProdDao.getPhoneNumsByMemberID(managerMaps);
		long endTime2 = System.currentTimeMillis();
		logger.info("数据库查询时间为："+(endTime2-endTime1));
		
		for(Map<String,Object> map : result){
			for(Map<String, Object> manager : managerMaps){
				if(map.get("platformUserNo").equals(manager.get("platformUserNo"))){
					map.put("financialManager", manager.get("financialManager"));
					map.put("memberId", manager.get("memberId"));
				}
			}
		}
		for(Map<String,Object> map : result){
			for(Map<String, String> model : phoneNums){
				if(map.get("memberId").equals(model.get("memberId"))){
					map.put("phoneNum", model.get("phoneNum"));
				}
			}
		}
		long endTime3 = System.currentTimeMillis();
		logger.info("转换时间为："+(endTime3-endTime2));
		
		try {
			Excel2AccountBalanceUtil.excelUtil(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime4 = System.currentTimeMillis();
		logger.info("Excel导出时间为："+(endTime4-endTime3));
		return allAmount;
	}

	@Override
	public BigDecimal getAllBorrowersAmount() {
		List<EnterpriseInfoResponse> enterResponsList = getEnterInfoFromCustody();
		long startTime = System.currentTimeMillis();
		BigDecimal allBorrowersAmount = new BigDecimal(0);
		for(EnterpriseInfoResponse enter : enterResponsList){
			if(enter.getAvailableAmount() != null){
				BigDecimal balance = new BigDecimal(enter.getAvailableAmount());
				allBorrowersAmount = allBorrowersAmount.add(balance);
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info("计算总资产时间为："+(endTime-startTime));
		return allBorrowersAmount;
	}
	
	public List<UserInformationResponse> getMemberInfoFromCustody() {
		List<Map<String,String>> userList = achievementManagerFromProdDao.getValidMemberIdFromMemberInfo();
		// 将List分解成多个小list集合
		List<List<Map<String,String>>> userlists = subArrayList(userList);
		
		List<UserInformationResponse> userResponseList = new ArrayList<UserInformationResponse>();
		long startTime = System.currentTimeMillis();
		
		// 创建线程池数量
		ExecutorService userPool = Executors.newFixedThreadPool(userlists.size());
		
		List<Callable<List<UserInformationResponse>>> userTasks = new ArrayList<Callable<List<UserInformationResponse>>>();
		
		for(int i=0 ; i<userlists.size() ; i++){
			// 线程返回值
			Callable<List<UserInformationResponse>> task = new ThreadUserUtil(userlists.get(i),custodyMemberService);
			userTasks.add(task);
		}
		
		try{
			// 执行线程
			List<Future<List<UserInformationResponse>>> userFutures = userPool.invokeAll(userTasks);
			// 处理线程返回结果
			if (userFutures != null && userFutures.size() > 0) {
                for (Future<List<UserInformationResponse>> future : userFutures) {
                	userResponseList.addAll(future.get());
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			userPool.shutdown();
		}
		long endTime = System.currentTimeMillis();
		logger.info("查询数量为："+userResponseList.size());
		logger.info("接口查询时间为："+(endTime-startTime));
		
		return userResponseList;
	}
	
	public List<EnterpriseInfoResponse> getEnterInfoFromCustody(){
		List<Map<String,String>> enterpriseList = achievementManagerFromProdDao.getValidMemberIdFromEnterpriseInfo();
		// 将List分解成多个小list集合
		List<List<Map<String,String>>> enterlists = subArrayList(enterpriseList);
		
		List<EnterpriseInfoResponse> enterResponsList = new ArrayList<EnterpriseInfoResponse>();
		long startTime = System.currentTimeMillis();
		
		// 创建线程池数量
		ExecutorService enterPool = Executors.newFixedThreadPool(enterlists.size());
		
		List<Callable<List<EnterpriseInfoResponse>>> enterTasks = new ArrayList<Callable<List<EnterpriseInfoResponse>>>();
		
		for(int i=0 ; i<enterlists.size() ; i++){
			// 线程返回值
			Callable<List<EnterpriseInfoResponse>> task = new ThreadEnterUtil(enterlists.get(i),custodyMemberService);
			enterTasks.add(task);
		}
		
		try{
			 // 执行线程
			List<Future<List<EnterpriseInfoResponse>>> enterFutures = enterPool.invokeAll(enterTasks);
			// 处理线程返回结果
			if (enterFutures != null && enterFutures.size() > 0) {
                for (Future<List<EnterpriseInfoResponse>> future : enterFutures) {
                	enterResponsList.addAll(future.get());
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			enterPool.shutdown();
		}
		long endTime = System.currentTimeMillis();
		logger.info("查询数量为："+enterResponsList.size());
		logger.info("接口查询时间为："+(endTime-startTime));
		
		return enterResponsList;
	}
	
	public static List<List<Map<String,String>>> subArrayList(List<Map<String,String>> list){
		int size = 100; 
		List<List<Map<String,String>>> resultList = new ArrayList<List<Map<String,String>>>();
		int arrSize = list.size()%size==0?list.size()/size:list.size()/size+1; 
		for(int i=0 ; i<arrSize ; i++) {
			if(list.size()%size == 0){
				List<Map<String,String>> childList = list.subList(i*100, (i+1)*100);
				resultList.add(childList);
			}else{
				if(i != arrSize-1){
					List<Map<String,String>> childList = list.subList(i*100, (i+1)*100);
					resultList.add(childList);
				}else{
					List<Map<String,String>> childList = list.subList(i*100, i*100+list.size()%size-1);
					resultList.add(childList);
				}
			}
		}    
		return resultList;
	}
}

class ThreadUserUtil implements Callable<List<UserInformationResponse>>{
	private List<Map<String,String>> list;
	private CustodyMemberService custodyMemberService;
	
	public ThreadUserUtil(List<Map<String,String>> list,CustodyMemberService custodyMemberService){
		this.list = list;
		this.custodyMemberService = custodyMemberService;
	}

	@Override
	public List<UserInformationResponse> call() throws Exception {
		List<UserInformationResponse> responseList = new ArrayList<UserInformationResponse>();
		for(Map<String,String> user : list){
			QueryUserInfoRequest request = new QueryUserInfoRequest();
			request.setMemberId(user.get("memberId"));
			request.setUserRole(user.get("userRole"));
			UserInformationResponse response = custodyMemberService.queryUserInfomation(request);
			responseList.add(response);
		}
		return responseList;
	}
}

class ThreadEnterUtil implements Callable<List<EnterpriseInfoResponse>>{
	private List<Map<String,String>> list;
	private CustodyMemberService custodyMemberService;
	
	public ThreadEnterUtil(List<Map<String,String>> list,CustodyMemberService custodyMemberService){
		this.list = list;
		this.custodyMemberService = custodyMemberService;
	}

	@Override
	public List<EnterpriseInfoResponse> call() throws Exception {
		List<EnterpriseInfoResponse> responseList = new ArrayList<EnterpriseInfoResponse>();
		for(Map<String,String> user : list){
			EnterpriseInfoRequest request = new EnterpriseInfoRequest();
			request.setMemberId(user.get("memberId"));
			request.setUserRole(user.get("userRole"));
			EnterpriseInfoResponse response = custodyMemberService.queryEnterpriseInfo(request);
			responseList.add(response);
		}
		return responseList;
	}
}
