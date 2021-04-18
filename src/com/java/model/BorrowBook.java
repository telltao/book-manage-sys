package com.java.model;


import java.sql.Date;

public class BorrowBook {

	private int id;//编号
	private String userName;//借阅用户名称
	private String bookPhone;//借阅用户手机号
	private String bookName;//借阅图书名称
	private Date borrowDate;//借阅日期
	private Date dueDate;//还书日期
	private String bookStatus;//图书状态：0借阅中，1已丢失 2已还书
	private String borrowStatus;//还书状态： 0正常，1污损 2缺页
	private Float penalty;//罚金
	private String status;//状态：0正常，1已删除
	private String remark;//备注

	//默认的构造方法
	public BorrowBook() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BorrowBook(int id, String userName, String bookPhone, String bookName,
					  Date borrowDate, Date dueDate, String bookStatus, String borrowStatus,
					  Float penalty, String status, String remark) {
		this.id = id;
		this.userName = userName;
		this.bookPhone = bookPhone;
		this.bookName = bookName;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.bookStatus = bookStatus;
		this.borrowStatus = borrowStatus;
		this.penalty = penalty;
		this.status = status;
		this.remark = remark;
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

	public String getBookPhone() {
		return bookPhone;
	}

	public void setBookPhone(String bookPhone) {
		this.bookPhone = bookPhone;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public Float getPenalty() {
		return penalty;
	}

	public void setPenalty(Float penalty) {
		this.penalty = penalty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
