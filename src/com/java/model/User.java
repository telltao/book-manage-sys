package com.java.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户实体
 *
 * @author Huke
 */
public class User {

	// 编号
	private Integer id;

	// 登录名
	private String loginName;
	// 用户名
	private String userName;
	// 手机号码
	private String phone;
	// 密码
	private String passWord;
	//状态：0 正常 1 禁用 2 已删除
	private String status;
	// 押金，默认每人押金为50元
	private Integer cashPledge;
	//创建时间
	private Date createTime;
	//押金状态： 0未交押金 1已交押金 2已退押金
	private String cashPledgeStauts;
	//累计罚款金额
	private Integer penalty;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String loginName, String passWord, String status) {
		super();
		this.loginName = loginName;
		this.passWord = passWord;
		this.status = status;
	}

	public User(Integer id, String loginName, String userName, String phone, String passWord, String status, Integer cashPledge, Date createTime, String cashPledgeStauts, Integer penalty) {
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.phone = phone;
		this.passWord = passWord;
		this.status = status;
		this.cashPledge = cashPledge;
		this.createTime = createTime;
		this.cashPledgeStauts = cashPledgeStauts;
		this.penalty = penalty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCashPledge() {
		return cashPledge;
	}

	public void setCashPledge(Integer cashPledge) {
		this.cashPledge = cashPledge;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCashPledgeStauts() {
		return cashPledgeStauts;
	}

	public void setCashPledgeStauts(String cashPledgeStauts) {
		this.cashPledgeStauts = cashPledgeStauts;
	}

	public Integer getPenalty() {
		return penalty;
	}

	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
