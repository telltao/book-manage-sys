package com.java.dao;

import com.java.model.User;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

	/**
	 * 登录验证
	 *
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */

	public User login(Connection con, User user) throws Exception {
		// 登录正确，返回用户的所有实体信息
		User resultUser = null;
		String sql = "select * from t_user where userName=? and passWord=?  and status != '2'";
		// 获取PreparedStatement接口
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 设置未知量的值
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		// 返回ResultSet结果集
		ResultSet rs = pstmt.executeQuery();

		// rs.next()指向表中第一行数据 若第一行有效，则返回true，并继续指向第二行
		if (rs.next()) {
			// 对用户进行实例化,取其中的set方法;
			resultUser = new User();
			// 取第一行id这个属性的数据，将结果返回给User实体的信息
			resultUser.setId(rs.getInt("id"));
			// 取第一行UserName这个属性的数据，将结果返回给User实体的信息
			resultUser.setUserName(rs.getString("userName"));
			// 取第一行PassWord这个属性的数据，将结果返回给User实体的信息
			resultUser.setPassWord(rs.getString("passWord"));
		}
		return resultUser;
	}

	//用户添加方法
	public int addBook(Connection con, User user) throws Exception {

		String sql = "INSERT INTO t_user(userName, passWord, status, createTime) VALUES (?,?,'0',sysdate())";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		return pstmt.executeUpdate();
	}

	//用户查询方法
	public ResultSet list(Connection con, User user) throws Exception {
		//动态结合，用StringBuffer比较好
		StringBuffer sb = new StringBuffer("select * from t_user  where 1=1  and status != '2'");
		//sql语句查询
		if (StringUtil.isNotEmpty(user.getUserName())) {
			sb.append(" and userName like '%" + user.getUserName() + "%'");
		}
		//sql语句查询
		if (StringUtil.isNotEmpty(user.getStatus())) {
			sb.append(" and status = '" + user.getStatus() + "' ");
		}
		//执行
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	//用户删除方法
	public int deleteUser(Connection con, String id) throws Exception {

		String sql = "update  t_user  set status = '2' where id =? ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	//图书修改方法
	public int updateUser(Connection con, User user) throws Exception {
		String sql = "update t_user set userName=?,passWord=?,status=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		pstmt.setString(3, user.getStatus());
		pstmt.setInt(4, user.getId());
		return pstmt.executeUpdate();
	}
}
