package com.java.model;

public class About {

	private int id;//编号
	private String content;//关于系统描述
	private String author;//作者
	private String version;//版本
	private String email;//联系邮箱

	//默认的构造方法
	public About() {
		super();
		// TODO Auto-generated constructor stub
	}

	public About(int id, String content, String author, String version, String email) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.version = version;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
