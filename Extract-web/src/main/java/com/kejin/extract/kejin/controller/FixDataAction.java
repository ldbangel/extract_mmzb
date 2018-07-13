package com.kejin.extract.kejin.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kejin.extract.domainservice.construct.ChannelInfoConstruct;
import com.kejin.extract.domainservice.construct.ChargeInfoConstruct;
import com.kejin.extract.domainservice.construct.MemberBalanceInfoConstruct;
import com.kejin.extract.domainservice.construct.MemberInfoConstruct;
import com.kejin.extract.domainservice.construct.ReInvestAndNewInfoConstruct;
import com.kejin.extract.domainservice.service.FixDataService;

@Controller
public class FixDataAction {
	@Resource(name = "fixDataService")
    private FixDataService fixDataService;
	@Resource(name = "memberBalanceInfoConstruct")
	private MemberBalanceInfoConstruct memberBalanceInfoConstruct;
	@Resource(name = "channelInfoConstruct")
	private ChannelInfoConstruct channelInfoConstruct;
	@Resource(name = "memberInfoConstruct")
	private MemberInfoConstruct memberInfoConstruct;
	@Resource(name = "reInvestAndNewInfoConstruct")
	private ReInvestAndNewInfoConstruct reInvestAndNewInfoConstruct;
	@Resource(name="chargeInfoConstruct")
	private ChargeInfoConstruct chargeInfoConstruct;
	/**
	 * 修复d_action_assist表中同步失败的数据
	 * 
	 */
	@RequestMapping(value = "fixActionAssistOutputFail.htm",produces = "text/html; charset=utf-8")
	public String fixActionAssistOutputFail(HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {		
		Calendar cal = new GregorianCalendar(2017, 11, 11, 00, 00, 00);
		fixDataService.actionAssistOutputFail(cal.getTime());
		return null;
	}
	
	/**
	 * 统计当前时间节点的个人资产，并将值写入d_member_balance表中
	 * 
	 */
	@RequestMapping(value = "countMemberBalance1.htm",produces = "text/html; charset=utf-8")
	public String CountMemberBalance(){
		memberBalanceInfoConstruct.memberBalanceConstruct();
		return null;
	}
	
	/**
	 * 手动修复d_reinvest_and_new中bid_fail的数据 
	 * 注意：修复时注意起始时间修改
	 */
	@RequestMapping(value = "fixRegularBidFailData.htm",produces = "text/html; charset=utf-8")
	public String FixRegularBidFailData(){
		Calendar begin = new GregorianCalendar(2018, 0, 16, 22, 16, 00);
		Calendar end = new GregorianCalendar(2018, 0, 20, 23, 25, 00);
		fixDataService.fixReinvestAndNewBidFail(begin.getTime(), end.getTime());
		return null;
	}
	
	/**
	 * 修复regular_invest数据
	 */
	@RequestMapping(value = "fixRegularInvestLoseData.htm",produces = "text/html; charset=utf-8")
	public String FixRegularInvestLoseData(){
		Calendar begin = new GregorianCalendar(2018, 0, 18, 00, 00, 00);
		Calendar end = new GregorianCalendar(2018, 0, 19, 00, 00, 00);
		fixDataService.fixRegularInvest(begin.getTime(), end.getTime());
		return null;
	}
	
	/**
	 * 修复regular_recovery数据
	 */
	@RequestMapping(value = "fixRegularRecoveryData.htm",produces = "text/html; charset=utf-8")
	public String FixRegularRecoveryData(){
		fixDataService.fixRegularRecovery(new Date(), new Date());
		return null;
	}
	
	/**
	 * 修复regular_recovery数据
	 */
	@RequestMapping(value = "insertChannelData.htm",produces = "text/html; charset=utf-8")
	public String insertChannelData(){
		Calendar begin = new GregorianCalendar(2015, 7, 20, 00, 00, 00);
		Calendar end = new GregorianCalendar(2018, 0, 19, 00, 00, 00);
		channelInfoConstruct.constructChannel(begin.getTime(), new Date());
		return null;
	}
	
	/**
	 * 添加债权转让费
	 */
	@RequestMapping(value = "fixCreditPayfee.htm",produces = "text/html; charset=utf-8")
	public String fixCreditPayfee(){
		fixDataService.fixCreditPayfee();
		return null;
	}
	
	/**
	 * 存管上线同步用户平台账户和激活状态
	 */
	@RequestMapping(value = "syncPlatformUserNo.htm",produces = "text/html; charset=utf-8")
	public String syncPlatformUserNo(){
		fixDataService.syncPlatformUserNo();
		return null;
	}
	
	@RequestMapping(value = "testReinvestAndNewData.htm",produces = "text/html; charset=utf-8")
	public String testReinvestAndNewData(){
		Calendar begin = new GregorianCalendar(2018, 4, 29, 00, 00, 00);
		Calendar end = new GregorianCalendar(2018, 4, 30, 00, 00, 00);
		reInvestAndNewInfoConstruct.constructReInvestAndNewRecord(begin.getTime(), end.getTime());
		return null;
	}
	
	@RequestMapping(value = "testReadFromMember.htm",produces = "text/html; charset=utf-8")
	public String testReadFromMember(){
		Calendar begin = new GregorianCalendar(2018, 5, 5, 00, 00, 00);
		Calendar end = new GregorianCalendar(2018, 5, 6, 00, 00, 00);
		memberInfoConstruct.constructUser(begin.getTime(), end.getTime());
		return null;
	}
	
	@RequestMapping(value = "testChargeData.htm",produces = "text/html; charset=utf-8")
	public String testChargeData(){
		Calendar begin = new GregorianCalendar(2018, 5, 13, 19, 00, 00);
		Calendar end = new GregorianCalendar(2018, 5, 13, 20, 00, 00);
		chargeInfoConstruct.constructCharge(begin.getTime(), end.getTime());
		return null;
	}
	
}
