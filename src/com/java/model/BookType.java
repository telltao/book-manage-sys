package com.java.model;

import java.util.Date;

/**
 * 图书类别实体
 *
 * @author
 */
public class BookType {

    //编号
    private int id;
    //图书类别名称
    private String bookTypeName;
    //图书类别描述
    private String bookTypeDesc;
    //图书类别状态：0正常，1已删除
    private String status;
    //图书类别创建时间
    private Date createTime;

    public BookType() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BookType(String bookTypeName, String bookTypeDesc) {
        super();
        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
    }

    //生成构造方法使用三个字段名,alt+shift+s
    public BookType(int id, String bookTypeName, String bookTypeDesc, String status) {
        super();
        this.id = id;
        this.bookTypeName = bookTypeName;
        this.bookTypeDesc = bookTypeDesc;
        this.status = status;
    }

    public int getId() {
        return id;
    }
	public void setId(int id) {
		this.id = id;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public String getBookTypeDesc() {
		return bookTypeDesc;
    }

    public void setBookTypeDesc(String bookTypeDesc) {
        this.bookTypeDesc = bookTypeDesc;
    }

    //打印直接输出“bookTypeName”，默认的不太好
    //@Override
    //public String toString() {
        //return bookTypeName;
    //}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String toString() {
    	return this.bookTypeName;
    }
}
