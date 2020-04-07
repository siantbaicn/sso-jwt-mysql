package cn.codesheep.sia.service;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.codesheep.sia.util.AES4AUtil;

@Service
public class Syn4AService {
	private static final Logger logger = LoggerFactory.getLogger(Syn4AService.class);

    //操作结果：0 成功、1 失败
    private static final String SUCCESS_COCE = "0";
    private static final String FAIL_COCE = "1";

    /**
     * 应用资源编号
     */
    @Value("${service.4A.resId}")
    private int service4AResId;

    /**
     * 应用资源名称
     */
    @Value("${service.4A.resName}")
    private String service4AResName;
    
    /**
     * 4A系统webserice服务的访问路径
     */
    @Value("${service.4A.synUrl}")
    private String service4ASynUrl;

    /**
     * 获取短信动态口令
     */
    @Value("${service.4A.dynamicAuthCodeService:sendLoginDynamicPwdService}")
    private String service4ADynamicAuthCodeService;

    /**
     * 短信认证登录鉴权服务
     */
    @Value("${service.4A.authCodeLoginService:authLoginCodeService}")
    private String service4AAuthCodeLoginService;
	
	/**
	 *
	 * 同步接口
	 *
	 *
	 * @param synUrl
	 * @param method
	 * @param params
	 * @return
	 * @throws AxisFault
	 */
	public static String SynService(String synUrl, String method, Object[] params) throws AxisFault {
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		EndpointReference targetEPR = new EndpointReference(synUrl);
		options.setTo(targetEPR);
		QName qName = new QName(method);
		Object[] values = serviceClient.invokeBlocking(qName, params, new Class[] { String.class });
		if (values != null && values.length > 0) {
			String result = values[0].toString();
			logger.info("4A同步帐号接口:调用应用侧接口返回数据为：" + result);
			return result;
		}
		return "";
	}

	/**
	 * 修改密码json字符串
	 *
	 * @param resId
	 * @param loginUser
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	public static String getModifyUserPwdJson(int resId, String loginUser, String newPassword)
			throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("res_id", resId);
		jsonParams.put("loginUser", AES4AUtil.encrypt(loginUser));
		jsonParams.put("oldPassword", "");
		jsonParams.put("newPassword", AES4AUtil.encrypt(newPassword));
		return jsonParams.toJSONString();
	}

	/**
	 *
	 * 删除用户json字符串
	 *
	 * @param resId
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	public static String getdelUserInfo(int resId, String loginUser) throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("res_id", resId);
		jsonParams.put("loginUser", AES4AUtil.encrypt(loginUser));
		return jsonParams.toJSONString();
	}

	public static String getAddUserInfo(int resId, String loginUser, String password, String staffName)
			throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("res_id", resId);
		jsonParams.put("loginUser", AES4AUtil.encrypt(loginUser));
		jsonParams.put("password", AES4AUtil.encrypt(password));
		jsonParams.put("staffName", AES4AUtil.encrypt(staffName));
		jsonParams.put("flag", "1");
		jsonParams.put("validLength", "365");
		jsonParams.put("expireDate", "2099-01-01 00:00:00");
		jsonParams.put("logLock", "1");
		jsonParams.put("departmentCode", "0");
		jsonParams.put("workCode", "0");
		jsonParams.put("note", "充值赋能4A新增用户");
		return jsonParams.toJSONString();
	}

	public static String getModifyUserInfo(int resId, String loginUser, String staffName) throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("res_id", resId);
		jsonParams.put("loginUser", AES4AUtil.encrypt(loginUser));
		jsonParams.put("password", "");
		jsonParams.put("staffName", AES4AUtil.encrypt(staffName));
		jsonParams.put("flag", "1");
		jsonParams.put("validLength", "365");
		jsonParams.put("expireDate", "2099-01-01 00:00:00");
		jsonParams.put("logLock", "1");
		jsonParams.put("departmentCode", "0");
		jsonParams.put("workCode", "0");
		jsonParams.put("note", "充值赋能4A修改用户");
		return jsonParams.toJSONString();
	}

	public static String getQueryUserInfo(String loginUser) throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("loginUser", AES4AUtil.encrypt(loginUser));
		return jsonParams.toJSONString();
	}

	public static String getSmsCodeInfo(int resId, String resName, String loginUser, String masterStaticPwd)
			throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("res_id", resId);
		jsonParams.put("res_name", resName);
		jsonParams.put("slave_name", AES4AUtil.encrypt(loginUser));
		jsonParams.put("master_static_pwd", AES4AUtil.encrypt(masterStaticPwd));
		jsonParams.put("auth_type", "1");
		return jsonParams.toJSONString();
	}

	public static String getAuthLoginCodeService(int resId, String resName, String loginUser,
			String masterStaticPwd, String authCode) throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("res_id", resId);
		jsonParams.put("res_name", resName);
		jsonParams.put("slave_name", AES4AUtil.encrypt(loginUser));
		jsonParams.put("master_static_pwd", AES4AUtil.encrypt(masterStaticPwd));
		jsonParams.put("auth_code", AES4AUtil.encrypt(authCode));
		jsonParams.put("auth_type", "1");
		return jsonParams.toJSONString();
	}

	public static String getResult() throws Exception {
		JSONObject jsonParams = new JSONObject();
		jsonParams.put("result", 0);
		jsonParams.put("resultDesc", "认证成功");
		return jsonParams.toJSONString();
	}
	
	/**
     * 获取短信动态口令
     * @param loginUser
     * @param loginPwd
     * @return
     */
    public Map<String, Object> sendLoginDynamicPwdService(String loginUser, String loginPwd) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        String smsCodeParams = Syn4AService.getSmsCodeInfo(service4AResId, service4AResName, loginUser, loginPwd);
        logger.info("获得短信验证码地址：{}，参数:{}", service4ASynUrl, smsCodeParams);
        String result = Syn4AService.SynService(service4ASynUrl, service4ADynamicAuthCodeService, new Object[]{smsCodeParams});
        logger.info("获得短信验证码结果:{}", result);
        String resultCode = JSONObject.parseObject(result).getString("result");
        if (SUCCESS_COCE.equals(resultCode)) {
            resultMap.put("isSuccess", true);
        } else {
            String resultDesc = JSONObject.parseObject(result).getString("resultDesc");
            resultMap.put("isSuccess", false);
            resultMap.put("msg", resultDesc);
        }
        return resultMap;
    }
    
    /**
     * 短信认证登录鉴权服务
     * @param loginUser
     * @param loginPwd
     * @param authCode
     * @return
     * @throws Exception
     */
    public Map<String, Object> authLoginCodeService(String loginUser, String loginPwd, String authCode) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        String authParams = Syn4AService.getAuthLoginCodeService(service4AResId, service4AResName, loginUser, loginPwd, authCode);
        logger.info("短信认证登录鉴权服务：{}，参数:{}", service4ASynUrl, authParams);
        String result = Syn4AService.SynService(service4ASynUrl, service4AAuthCodeLoginService, new Object[]{authParams});
        logger.info("短信认证登录鉴权服务结果:{}", result);
        String resultCode = JSONObject.parseObject(result).getString("result");
        if (SUCCESS_COCE.equals(resultCode)) {
            resultMap.put("isSuccess", true);
        } else {
            String resultDesc = JSONObject.parseObject(result).getString("resultDesc");
            resultMap.put("isSuccess", false);
            resultMap.put("msg", resultDesc);
        }
        return resultMap;
    }
}
