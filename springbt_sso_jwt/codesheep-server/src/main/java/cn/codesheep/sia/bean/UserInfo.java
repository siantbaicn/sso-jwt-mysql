package cn.codesheep.sia.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name ="user_info")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String userId;
	private String loginUser;// 登录用户（账号）
	private String loginPwd;// 密码（密文）//4A方提供加解密方式
	private String staffName;// （姓名）
	private String flag;// （有效标志）
	private String validLength;// （有效期）
	private String expireDate;// （失效时间）
	private String logLock;// （锁定状态）//0，禁用；1，启用；9删除
	private String departmentCode;// （所属部门）
	private String workCode;// （所属工作组，多个组用逗号隔开）
	private String note;// （备注）

	private String userGroup;
	private String userRole;
	/**
	 * 会话token
	 */
	@JsonIgnore
	private String token;

	private String email;
	private String phone;
	private String deptName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public static List<UserInfo> getUserInfoVerification(List<UserInfo> arry) {
		if (arry == null) {
			return null;
		}
		for (int i = 0; i < arry.size(); i++) {
			if (arry.get(i).getLoginUser() == null) {
				arry.get(i).setLoginUser("0");
			}
			if (arry.get(i).getLoginPwd() == null) {
				arry.get(i).setLoginPwd("0");
			}
			if (arry.get(i).getStaffName() == null) {
				arry.get(i).setStaffName("0");
			}
			if (arry.get(i).getFlag() == null) {
				arry.get(i).setFlag("0");
			}
			if (arry.get(i).getValidLength() == null) {
				arry.get(i).setValidLength("0");
			}else{
				arry.get(i).setValidLength("365");
			}
			if (arry.get(i).getExpireDate() == null) {
				arry.get(i).setExpireDate("0");
			}else{
				arry.get(i).setExpireDate(arry.get(i).getExpireDate()+" 00:00:00");
			}
			if (arry.get(i).getLogLock() == null) {
				arry.get(i).setLogLock("0");
			}
			if (arry.get(i).getDepartmentCode() == null) {
				arry.get(i).setDepartmentCode("0");
			}
			if (arry.get(i).getWorkCode() == null) {
				arry.get(i).setWorkCode("0");
			}
			if (arry.get(i).getNote() == null) {
				arry.get(i).setNote("0");
			}
		}
		return arry;
	}

	public UserInfo() {
	}

	public UserInfo(String staffName) {
		this.staffName = staffName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getValidLength() {
		return validLength;
	}

	public void setValidLength(String validLength) {
		this.validLength = validLength;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getLogLock() {
		return logLock;
	}

	public void setLogLock(String logLock) {
		this.logLock = logLock;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "UserInfoBean{" +
				"userId='" + userId + '\'' +
				", loginUser='" + loginUser + '\'' +
				", loginPwd='" + loginPwd + '\'' +
				", staffName='" + staffName + '\'' +
				", flag='" + flag + '\'' +
				", validLength='" + validLength + '\'' +
				", expireDate='" + expireDate + '\'' +
				", logLock='" + logLock + '\'' +
				", departmentCode='" + departmentCode + '\'' +
				", workCode='" + workCode + '\'' +
				", note='" + note + '\'' +
				", userGroup='" + userGroup + '\'' +
				", userRole='" + userRole + '\'' +
				", token='" + token + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", deptName='" + deptName + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
