package com.java.dao;

import com.java.model.Book;
import com.java.model.BorrowBook;
import com.java.model.User;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 图书Dao类
 *
 * @author Huke
 */
public class BorrowBookDao {


	/**
	 * CREATE TABLE `t_borrow_book` (
	 * `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
	 * `userId` varchar(500) NOT NULL COMMENT '借阅用户id',
	 * `userName` varchar(50) NOT NULL COMMENT '借阅用户名称',
	 * `bookPhone` varchar(11) DEFAULT NULL COMMENT '借阅图书手机号',
	 * `bookName` varchar(50) DEFAULT NULL COMMENT '借阅图书名称',
	 * `borrowDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借阅日期',
	 * `dueDate` datetime DEFAULT NULL COMMENT '还书日期',
	 * `bookStatus` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '图书状态：0借阅中，1已丢失 2已还书',
	 * `borrowStatus` varchar(20) DEFAULT '0' COMMENT '还书状态： 0正常，1污损 2缺页',
	 * `penalty` decimal(10,2) DEFAULT '0.00' COMMENT '罚金',
	 * `status` varchar(2) DEFAULT NULL COMMENT '状态：0正常，1已删除',
	 * PRIMARY KEY (`id`)
	 * ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
	 */

	//图书添加方法
	public int addBorrowBook(Connection con, BorrowBook borrowBook) throws Exception {
		String sql = "INSERT INTO t_borrow_book(userName, bookPhone, bookName, borrowDate," +
				" dueDate, bookStatus, borrowStatus, penalty, status,remark) VALUES (?,?,?,?,?,?,?,?,'0',?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, borrowBook.getUserName());
		pstmt.setString(2, borrowBook.getBookPhone());
		pstmt.setString(3, borrowBook.getBookName());
		pstmt.setDate(4, borrowBook.getBorrowDate());
		pstmt.setDate(5, borrowBook.getDueDate());
		pstmt.setString(6, borrowBook.getBookStatus());
		pstmt.setString(7, borrowBook.getBorrowStatus());
		pstmt.setFloat(8, borrowBook.getPenalty());
		pstmt.setString(9, borrowBook.getRemark());
		return pstmt.executeUpdate();
	}

	//图书查询方法
	public ResultSet list(Connection con, BorrowBook borrowBook) throws Exception {
		//动态结合，用StringBuffer比较好
		StringBuffer sb = new StringBuffer("select * from t_borrow_book  where status = '0'");
		//sql语句查询
		//查询图书名称
		if (StringUtil.isNotEmpty(borrowBook.getBookName())) {
			sb.append(" and bookName like '%" + borrowBook.getBookName() + "%'");
		}
		//查询借阅人手机号
		if (StringUtil.isNotEmpty(borrowBook.getBookPhone())) {
			sb.append(" and bookPhone = '" + borrowBook.getBookPhone() + "'");
		}

		//执行
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	//图书删除方法
	//V2 改为逻辑删除
	public int deleteBorrowBook(Connection con, String id) throws Exception {
		String sql = "update  t_borrow_book  set status = '1' where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	//图书修改方法
	public int updateBook(Connection con, BorrowBook borrowBook) throws Exception {
		String sql = "UPDATE t_borrow_book SET userName = ?, " +
				"bookPhone = ?, bookName = ?," +
				" borrowDate = ?, dueDate = ?," +
				" bookStatus = ?, borrowStatus = ?, penalty = ?," +
				" status =? WHERE id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, borrowBook.getUserName());
		pstmt.setString(2, borrowBook.getBookPhone());
		pstmt.setString(3, borrowBook.getBookName());
		pstmt.setDate(4, borrowBook.getBorrowDate());
		pstmt.setDate(5, borrowBook.getDueDate());
		pstmt.setString(6, borrowBook.getBookStatus());
		pstmt.setString(7, borrowBook.getBorrowStatus());
		pstmt.setFloat(7, borrowBook.getPenalty());
		pstmt.setInt(7, borrowBook.getId());
		return pstmt.executeUpdate();
	}

	//判断指定图书类别下是否有图书
	public boolean exitBookByBookTypeId(Connection con, String bookTypeId) throws Exception {
		String sql = "select * from t_book where bookTypeId=? and status = '0'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}

	/**
	 * 根据用户名和手机号查询信息
	 */

	public int checkBookBorrowSize(Connection con, BorrowBook borrowBook) throws Exception {
		//查询 状态：0正常 图书状态不是已还书
		String sql = "select * from t_borrow_book where userName=? and bookPhone=?  and status = '0' and bookStatus !='2'";
		// 获取PreparedStatement接口
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 设置未知量的值
		pstmt.setString(1, borrowBook.getUserName());
		pstmt.setString(2, borrowBook.getBookPhone());
		// 返回ResultSet结果集
		ResultSet rs = pstmt.executeQuery();

		// rs.next()指向表中第一行数据 若第一行有效，则返回true，并继续指向第二行
		if (rs.next()) {
			//获取数据大小 也就是sql执行查询的数据大小 三条 结果为3
			int size = rs.getFetchSize();
			return size;
		}
		return 0;
	}

	/**
	 * 根据用户名和手机号查询信息
	 * 废弃
	 */

	public BorrowBook checkBookBorrow1(Connection con, BorrowBook borrowBook) throws Exception {
		//查询 状态：0正常 图书状态不是已还书
		String sql = "select * from t_borrow_book where userName=? and bookPhone=?  and status = '0' and bookStatus !='2'";
		// 获取PreparedStatement接口
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 设置未知量的值
		pstmt.setString(1, borrowBook.getUserName());
		pstmt.setString(2, borrowBook.getBookPhone());
		// 返回ResultSet结果集
		ResultSet rs = pstmt.executeQuery();

		BorrowBook borrowBook1 = null;

		// rs.next()指向表中第一行数据 若第一行有效，则返回true，并继续指向第二行
		if (rs.next()) {
			// 对用户进行实例化,取其中的set方法;
			borrowBook1 = new BorrowBook();
			// 取第一行id这个属性的数据，将结果返回给User实体的信息
			borrowBook1.setId(rs.getInt("id"));
			// 取第一行UserName这个属性的数据，将结果返回给User实体的信息
			borrowBook1.setUserName(rs.getString("userName"));
			borrowBook1.setBookPhone(rs.getString("bookPhone"));
			borrowBook1.setStatus(rs.getString("status"));
			borrowBook1.setBookName(rs.getString("bookName"));
			borrowBook1.setBookStatus(rs.getString("bookStatus"));
			borrowBook1.setBorrowStatus(rs.getString("borrowStatus"));
			borrowBook1.setPenalty(rs.getFloat("penalty"));
		}
		return borrowBook1;
	}
}
