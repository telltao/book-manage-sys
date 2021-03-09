package com.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	/**
	 * �������ݿ⹤����
	 */
	// mysql���ݿ��ַ
	private String dbUrl = "jdbc:mysql://localhost:3306/db_test";

	// �û���
	private String dbUserName = "root";

	// ����
	private String dbPassWord = "root";

	// ��������
	private String jdbcname = "com.mysql.jdbc.Driver";

	// ���ݿ����ӷ���
	public Connection getCon() throws Exception {
		// �������ݿ�����
		Class.forName(jdbcname);
		// ���ݿ�����������
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassWord);
		return con;
	}

	// ���ݿ�رշ���
	public void close(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

}
