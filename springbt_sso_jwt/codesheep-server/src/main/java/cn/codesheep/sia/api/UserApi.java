package cn.codesheep.sia.api;

import java.util.Map;

import org.apache.http.protocol.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.codesheep.sia.bean.ResponseResult;
import cn.codesheep.sia.bean.UserInfo;
import cn.codesheep.sia.constant.ResultCodeConstant;
import cn.codesheep.sia.repository.UserRepository;
import cn.codesheep.sia.service.Syn4AService;
import cn.codesheep.sia.util.AES4AUtil;

public class UserApi {
	private final Logger logger = LoggerFactory.getLogger(UserApi.class);

	@Value("${isSendLog}")
	String isSendLog;
	@Value("${LOGIN_URL_4A}")
	String loginURL;
	@Value("${LOGOUT_URL_4A}")
	String logoutURL;
	@Value("${server.uri}")
	String localURI;
	@Autowired
	HttpService httpUtils;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private Syn4AService syn4AService;
	
	@RequestMapping(value = "/4a/sendAuthCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseResult<Object> sendAuthCode(@RequestParam("userName") String userName, @RequestParam("password") String password) {
		ResponseResult<Object> result = new ResponseResult<>();
		try {
			UserInfo user = new UserInfo();
			user.setLoginUser(userName);
			String userNameAES = AES4AUtil.encrypt(userName);
			String pwdAES = AES4AUtil.encrypt(password);
			user.setLoginPwd(pwdAES);
			UserInfo userinfo = userRepository.findByLoginUserAndLoginPwd(userNameAES,pwdAES);
			if (userinfo != null) {
				Map<String, Object> resultMap = syn4AService.sendLoginDynamicPwdService(userNameAES, pwdAES);
				if ((boolean) resultMap.get("isSuccess")) {
					logger.info("{}发送登录验证码成功", userName);
					result.setResultCode(ResultCodeConstant.c_20000.getCode());
					result.setResult("验证码发送成功");
				} else {
					logger.info("{}发送登录验证码失败", userName);
					result.setResultCode(ResultCodeConstant.c_50006.getCode());
					result.setResult(resultMap.get("msg"));
				}
			} else {
				logger.info("{}用户名或密码错误", userName);
				result.setResultCode(ResultCodeConstant.c_50006.getCode());
				result.setResult("用户名或密码错误");
			}
		} catch (Exception e) {
			result.setResultCodeConstant(ResultCodeConstant.c_50006);
			logger.error(String.format("处理用户%s发送登录验证码:s%", userName, e.getMessage()), e);
		}
		return result;
	}
}
