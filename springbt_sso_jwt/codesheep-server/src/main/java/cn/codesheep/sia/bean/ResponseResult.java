package cn.codesheep.sia.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import cn.codesheep.sia.constant.ResultCodeConstant;
import cn.codesheep.utils.JsonUtil;

/**
 * @author jukz
 * @date 2019年3月22日
 * @param <T>
 */
public class ResponseResult<T> {

	/**
	 * 业务类
	 */
	private T t;
	
	/**
	 * 返回结果码
	 */
	private String resultCode;
	
	/**
	 * 返回描述
	 */
	private String resultDesc;
	
	/**
	 * 返回码枚举类
	 */
	@JsonIgnore
	private ResultCodeConstant resultCodeConstant;
	
	public T getResult() {
		return t;
	}
	
	public void setResult(T t) {
		this.t = t;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getResultDesc() {
		return resultDesc;
	}
	
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	
	public ResultCodeConstant getResultCodeConstant() {
		return resultCodeConstant;
	}
	
	public void setResultCodeConstant(ResultCodeConstant resultCodeConstant) {
		this.resultCodeConstant = resultCodeConstant;
		setResultCode(resultCodeConstant.getCode());
		setResultDesc(resultCodeConstant.getDescription());
	}
	
	@Override
	public String toString() {
		String json = null;
		try {
			json = JsonUtil.object2NonNullJsonStr(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
}
