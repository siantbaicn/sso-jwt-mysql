package cn.codesheep.sia.constant;

/**
 * 返回码常量类
 * @author jukz
 * @date 2019年3月25日
 */
public enum ResultCodeConstant {

	c_20000("20000", "成功"),
	c_20001("20001", "4A登录成功"),
	c_20002("20002", "通过4A退出登录成功"),
	c_20003("20003", "退出登录成功"),
	c_50006("50006", "处理异常"),
	c_50008("50008", "非法token"),
	c_50012("50012", "其他客户端登录"),
	c_50014("50014", "token过期"),
	c_50015("50015", "4A登录开关已开启，请通过4A登录"),
	c_50016("50016", "4A登录开关已关闭，请通过赋能平台登录"),
	c_50017("50017", "4A登录失败"),
	c_40000("40000", "状态不允许删除");
	
	String code;
	String description;
	
	private ResultCodeConstant(String value, String description) {
		this.code = value;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}

}
