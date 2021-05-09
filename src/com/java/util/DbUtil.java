package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 连接数据库工具类
 */
public class DbUtil {

	
	// mysql数据库地址 并开启ssl认证
	private String dbUrl = "jdbc:mysql://localhost:3306/db_test?useSSL=false&serverTimezone=CTT&useUnicode=true&characterEncoding=utf8";

	// 用户名
	private String dbUserName = "root";

	// 密码
	private String dbPassWord = "root";

	// 驱动名称
	private String jdbcname = "com.mysql.cj.jdbc.Driver";

	//数据库低于5.7版本的驱动名称 兼容使用
	//private String jdbcname = "com.mysql.jdbc.Driver";

	// 数据库连接方法
	public Connection getCon() throws Exception {
		// 加载数据库驱动
		Class.forName(jdbcname);
		// 数据库驱动管理类
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
		return con;
	}

	// 数据库关闭方法
	public void close(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

}
