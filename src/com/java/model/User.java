package com.java.model;

/**
 * 用户实体
 * @author Huke
 *
 */
public class User {

	// 编号
	private int id;
	// 用户名
	private String userName;
	// 密码
	private String passWord;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
