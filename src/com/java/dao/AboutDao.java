package com.java.dao;

import com.java.model.About;
import com.java.model.Book;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 关于图书管理系统
 *
 * @author Juan Wu
 */
public class AboutDao {


	//查询关于系统
	public ResultSet list(Connection con) throws Exception {
		String sql = "select author,content,version,email from t_about_sys where status = '0' ";
		//执行
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
