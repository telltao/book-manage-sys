package com.java.dao;

import com.java.model.BookType;
import com.java.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 图书类别Dao
 * @author
 *
 */
public class BookTypeDao {

	//图书类别添加方法
	public int add_Book(Connection con,BookType bookType) throws Exception{
		String sql = "INSERT INTO t_booktype(id, bookTypeName, bookTypeDesc, status, createTime) values(null,?,?,'0',sysdate())";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		//返回操作的结果
		return pstmt.executeUpdate();
	}

	//图书类别查询的集合
	public ResultSet list(Connection con,BookType bookType) throws Exception {
		//动态结合，用StringBuffer比较好
		StringBuffer sb = new StringBuffer("select * from t_booktype where status = '0'");
		//sql语句查询，当条件有多个时，就用and暂时替代where
		if (StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" and bookTypeName like '%" + bookType.getBookTypeName() + "%'");
		}
		//调用replaceFirst方法将and替换掉
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	//图书类别修改方法
	public int update_Book(Connection con,BookType booktype) throws Exception{
		String sql="update t_booktype set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, booktype.getBookTypeName());
		pstmt.setString(2, booktype.getBookTypeDesc());
		pstmt.setInt(3, booktype.getId());
		return pstmt.executeUpdate();
	}

	//图书类别删除方法
	public int delete_Book(Connection con,String id) throws Exception {
		String sql = "update  t_booktype set status = '1' where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
}
