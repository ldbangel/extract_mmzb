package com.kejin.extract.domainservice.dingding;


/**
 * 企业应用接入时的常量定义
 */
public class Env {

    /**
     * 企业应用接入秘钥相关
     */
    public static final String CORP_ID = "ding683a0a936a4cb9cc";
    public static final String CORP_SECRET = "h9IlK4JEtdpd9v0PiaZY57zfgodZXPHCVWAxGiP_VKpazbv0yycxDnDW4-hHsjRB";
    public static final String SSO_Secret = "";

    /**
     * DING API地址
     */
	public static final String OAPI_HOST = "https://oapi.dingtalk.com";
    /**
     * 企业应用后台地址，用户管理后台免登使用
     */
	public static final String OA_BACKGROUND_URL = "";


    /**
     * 企业通讯回调加密Token，注册事件回调接口时需要传递给钉钉服务器
     */
	public static final String TOKEN = "mmzb321";
	public static final String ENCODING_AES_KEY = "UnTCdrcuISJ33L4yeRSnovaRhVBimgKHGs0IIMGeein";
	
}
