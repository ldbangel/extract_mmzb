package com.kejin.extract.domainservice.dingding;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 通讯录回调相关事件
 * <p>
 * https://open-doc.dingtalk.com/docs/doc.htm?treeId=371&articleId=104975&docType=1
 */
public class EventChangeHelper {

    /**
     * 注册事件回调接口
     */
    public static String registerEventChange(String accessToken, List<String> callBackTag, String token, String aesKey, String url) throws OApiException {
        String signUpUrl = Env.OAPI_HOST + "/call_back/register_call_back?" +
                "access_token=" + accessToken;
        JSONObject args = new JSONObject();
        args.put("call_back_tag", callBackTag);
        args.put("token", token);
        args.put("aes_key", aesKey);
        args.put("url", url);

        JSONObject response = HttpHelper.httpPost(signUpUrl, args);
        if (response.containsKey("errcode")) {
            return response.getString("errcode");
        } else {
            return null;
        }
    }

    //查询事件回调接口
    public static String getEventChange(String accessToken) throws OApiException {
        String url = Env.OAPI_HOST + "/call_back/get_call_back?" +
                "access_token=" + accessToken;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toString();
    }

    //更新事件回调接口
    public static String updateEventChange(String accessToken, List<String> callBackTag, String token, String aesKey, String url) throws OApiException {
        String signUpUrl = Env.OAPI_HOST + "/call_back/update_call_back?" +
                "access_token=" + accessToken;
        JSONObject args = new JSONObject();
        args.put("call_back_tag", callBackTag);
        args.put("token", token);
        args.put("aes_key", aesKey);
        args.put("url", url);

        JSONObject response = HttpHelper.httpPost(signUpUrl, args);
        if (response.containsKey("errcode")) {
            return response.getString("errcode");
        } else {
            return null;
        }
    }

    //删除事件回调接口
    public static String deleteEventChange(String accessToken) throws OApiException {
        String url = Env.OAPI_HOST + "/call_back/delete_call_back?" +
                "access_token=" + accessToken;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toString();
    }


    public static String getFailedResult(String accessToken) throws OApiException {
        String url = Env.OAPI_HOST + "/call_back/get_call_back_failed_result?" +
                "access_token=" + accessToken;
        JSONObject response = HttpHelper.httpGet(url);
        return response.toString();
    }
    
    public static String encryptCallBackInfo(String msgSignature, String timeStamp, String nonce, String encrypt){
    	/**对encrypt进行解密**/
    	DingTalkEncryptor dingTalkEncryptor = null;
    	String plainText = null;
    	try {
    	    dingTalkEncryptor = new DingTalkEncryptor(Env.TOKEN, Env.ENCODING_AES_KEY, Env.CORP_ID);
    	    plainText = dingTalkEncryptor.getDecryptMsg(msgSignature, timeStamp, nonce, encrypt);
    	} catch (DingTalkEncryptException e) {
    	    System.out.println(e.getMessage());
    	    e.printStackTrace();
    	}
    	 
    	/**对从encrypt解密出来的明文进行处理**/
    	JSONObject plainTextJson = JSONObject.parseObject(plainText);       
    	String eventType = plainTextJson.getString("EventType");
    	switch (eventType){
    	case "org_user_add"://do something
    	    break;
    	case "org_user_modify"://do something
    	    break;
    	case "org_user_leave":// do something
    	    break;
    	case "check_url"://do something
    	default : //do something
    	    break;
    	}
    	 
    	/**对返回信息进行加密**/
    	long timeStampLong = Long.parseLong(timeStamp);
    	Map<String,String> jsonMap = null;
    	try {
    	    jsonMap = dingTalkEncryptor.getEncryptedMap("success", timeStampLong, nonce);
    	} catch (DingTalkEncryptException e) {
    	    System.out.println(e.getMessage());
    	    e.printStackTrace();
    	}
    	JSONObject json = new JSONObject();
    	json.putAll(jsonMap);   
    	return json.toString();
    }


}
