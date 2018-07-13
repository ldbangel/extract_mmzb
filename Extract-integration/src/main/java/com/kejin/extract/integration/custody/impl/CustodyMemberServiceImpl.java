package com.kejin.extract.integration.custody.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kejin.extract.integration.custody.CustodyMemberService;
import com.mmzb.custody.shbk.service.facade.RegisterFacade;
import com.mmzb.custody.shbk.service.request.EnterpriseInfoRequest;
import com.mmzb.custody.shbk.service.request.EnterpriseRegisterRequest;
import com.mmzb.custody.shbk.service.request.QueryUserInfoRequest;
import com.mmzb.custody.shbk.service.request.UpdateEnterpriseInfoRequest;
import com.mmzb.custody.shbk.service.response.EnterpriseInfoResponse;
import com.mmzb.custody.shbk.service.response.GatewayResponse;
import com.mmzb.custody.shbk.service.response.UserInformationResponse;

/**
 * 存管会员相关
 * 
 * @author yh
 *
 * @createTime 2017年11月13日下午5:08:59
 */
@Service("custodyMemberService")
public class CustodyMemberServiceImpl implements CustodyMemberService {

	private Logger logger = LoggerFactory.getLogger(CustodyMemberServiceImpl.class);

	@Resource(name = "registerFacade")
	private RegisterFacade registerFacade;

	/*@Resource(name = "bankcardFacade")
	private BankcardFacade bankcardFacade;*/

	@Override
	public GatewayResponse doEnterpriseRegister(EnterpriseRegisterRequest request) {

		logger.info("[SITE->CUSTODY]企业用户注册信息请求{}", request);

		GatewayResponse response = null;
		try {
			response = registerFacade.doEnterpriseRegister(request);

		} catch (Exception e) {
			logger.error("企业用户注册发生异常{}", e);
			//throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
		} finally {
			logger.info("[CUSTODY->SITE]企业用户注册信息应答{}", response);
		}
		return response;
	}

	@Override
	public UserInformationResponse queryUserInfomation(QueryUserInfoRequest request) {
		logger.info("[SITE->CUSTODY]企业用户信息查询请求,{}", request);

		UserInformationResponse response = null;
		try {
			response = registerFacade.queryUserInfomation(request);
		} catch (Exception e) {
			logger.error("企业用户查询信息发生异常,{}", e);
			//throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
		} finally {
			logger.info("[CUSTODY->SITE]企业用户查询信息应答:{}", response);
		}
		return response;
	}

	@Override
	public EnterpriseInfoResponse queryEnterpriseInfo(EnterpriseInfoRequest request){
		logger.info("[SITE->CUSTODY]企业用户信息查询请求,{}", request);
		EnterpriseInfoResponse response = null;
		try {
			response = registerFacade.queryEnterpriseInfo(request);
		} catch (Exception e) {
			logger.error("企业用户查询信息发生异常,{}", e);
			//throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
		} finally {
			logger.info("[CUSTODY->SITE]企业用户查询信息应答:{}", response);
		}
		return response;
	}

	@Override
	public GatewayResponse updateEnterpriseInfo(UpdateEnterpriseInfoRequest request){
		GatewayResponse response = null;
		try {

			response = registerFacade.updateEnterpriseInfo(request);
		} catch (Exception e) {
			logger.error("企业用户修改信息发生异常,{}", e);
			//throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
		} finally {
			logger.info("[CUSTODY->SITE]企业用户修改信息应答:{}", response);
		}

		return response;
	}

}
