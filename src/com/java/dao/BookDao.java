package com.java.dao;

import com.java.model.Book;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 图书Dao类
 * @author Huke
 *
 */
public class BookDao {

	//图书添加方法
	public int addBook(Connection con,Book book) throws Exception{
		String sql = "INSERT INTO t_book(id, bookName, author, sex, price, bookTypeId, bookTypeName, bookDesc, status, createTime)  values(null,?,?,?,?,?,?,?,'0',sysdate())";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setInt(5, book.getBookTypeId());
		pstmt.setString(6, book.getBookTypeName());
		pstmt.setString(7, book.getBookDesc());
		return pstmt.executeUpdate();
	}

	//图书查询方法
	public ResultSet list(Connection con,Book book) throws Exception{
		//动态结合，用StringBuffer比较好
		StringBuffer sb = new StringBuffer("select * from t_book b,t_bookType bt where b.bookTypeId=bt.id and b.status = '0'");
		//sql语句查询
		if (StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and b.bookName like '%" + book.getBookName() + "%'");
		}
		if (StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and b.author like '%" + book.getAuthor() + "%'");
		}
		if (book.getBookTypeId() != null && book.getBookTypeId() != -1) {
			sb.append(" and b.bookTypeId =" + book.getBookTypeId());
		}
		//执行
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	//图书删除方法
	//V2 改为逻辑删除
	public int deleteBook(Connection con, String id) throws Exception {
		String sql = "update  t_book  set status = '1' where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	//图书修改方法
	public int updateBook(Connection con,Book book)throws Exception{
		String sql="update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=?,bookTypeId=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getSex());
		pstmt.setFloat(4, book.getPrice());
		pstmt.setString(5, book.getBookDesc());
		pstmt.setInt(6, book.getBookTypeId());
		pstmt.setInt(7, book.getId());
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

	//根据图书编号查询图书
	public Book getBookByBookId(Connection con, String bookId) throws Exception {
		//查询状态为正常的图书
		Book resultBook = null;

		String sql = "select * from t_book where id=? and status = '0'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookId);
		ResultSet rs = pstmt.executeQuery();
		// rs.next()指向表中第一行数据 若第一行有效，则返回true，并继续指向第二行
		if (rs.next()) {
			// 对用户进行实例化,取其中的set方法;
			resultBook = new Book();
			// 取第一行id这个属性的数据，将结果返回给User实体的信息
			resultBook.setId(rs.getInt("id"));
			resultBook.setBookName(rs.getString("bookName"));
			resultBook.setAuthor(rs.getString("author"));
			resultBook.setSex(rs.getString("sex"));
			resultBook.setPrice(rs.getFloat("price"));
			resultBook.setBookTypeId(rs.getInt("bookTypeId"));
			resultBook.setBookTypeName(rs.getString("bookTypeName"));
			resultBook.setBookDesc(rs.getString("bookDesc"));
			resultBook.setStatus(rs.getString("status"));
		}
		return resultBook;
	}
}
