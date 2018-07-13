package com.kejin.extract.integration.custody;

import com.mmzb.custody.shbk.service.request.EnterpriseInfoRequest;
import com.mmzb.custody.shbk.service.request.EnterpriseRegisterRequest;
import com.mmzb.custody.shbk.service.request.QueryUserInfoRequest;
import com.mmzb.custody.shbk.service.request.UpdateEnterpriseInfoRequest;
import com.mmzb.custody.shbk.service.response.EnterpriseInfoResponse;
import com.mmzb.custody.shbk.service.response.GatewayResponse;
import com.mmzb.custody.shbk.service.response.UserInformationResponse;

public interface CustodyMemberService {

	/**
	 * 企业注册
	 * 
	 * @author yh
	 * @param request
	 * @return
	 * @createTime 2017年11月14日上午10:07:38
	 */
	public GatewayResponse doEnterpriseRegister(EnterpriseRegisterRequest request);

	/**
	 * 查询用户账户信息
	 * 
	 * @author yh
	 * @param request
	 * @return
	 * @throws BizException
	 * @createTime 2017年11月14日上午10:41:41
	 */
	public UserInformationResponse queryUserInfomation(QueryUserInfoRequest request);

	/**
	 * 查询企业用户信息
	 * 
	 * @author yh
	 * @param request
	 * @return
	 * @throws BizException
	 * @createTime 2017年11月14日下午8:13:34
	 */
	public EnterpriseInfoResponse queryEnterpriseInfo(EnterpriseInfoRequest request);

	/**
	 * 企业用户绑卡
	 * 
	 * @author yh
	 * @param request
	 * @return
	 * @throws BizException
	 * @createTime 2017年11月22日下午7:48:31
	 */
	//public GatewayResponse enterpriseBindBankCard(BindCardRequest request);

	/**
	 * 企业客户信息修改
	 * 
	 * @param request
	 * @return
	 */
	public GatewayResponse updateEnterpriseInfo(UpdateEnterpriseInfoRequest request);

}
