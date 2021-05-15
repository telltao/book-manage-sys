package com.java.view.about;

import com.java.dao.AboutDao;
import com.java.model.About;
import com.java.util.DbUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AboutFrame extends JInternalFrame {

	private DbUtil dbUtil = new DbUtil();
	private AboutDao aboutDao = new AboutDao();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutFrame frame = new AboutFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AboutFrame() {
		getContentPane().setBackground(SystemColor.textHighlight);
		setBackground(Color.CYAN);
		setIconifiable(true);
		setClosable(true);
		setTitle("关于系统");
		setBounds(100, 100, 700, 450);
//		this.setBounds((900 - 700) / 2,(900 - 450) / 2,700,450);
		JTextArea txtrJavaccjavajavaJava = new JTextArea();
		txtrJavaccjavajavaJava.setRows(5);
		txtrJavaccjavajavaJava.setBackground(SystemColor.textHighlight);
		txtrJavaccjavajavaJava.setForeground(Color.WHITE);
		//TODO 在此处将数据从数据库中查询出来

		About about = initAbout();
		txtrJavaccjavajavaJava.setText("关于我们:" +
				"\n作者：" + about.getAuthor() +
				"\n说明：" + about.getContent() +
				"\n联系方式：" + about.getEmail() + "");
		txtrJavaccjavajavaJava.setFont(new Font("楷体", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap(63, Short.MAX_VALUE)
								.addComponent(txtrJavaccjavajavaJava, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE)
								.addGap(50))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(50)
								.addComponent(txtrJavaccjavajavaJava, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(143, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 初始化关于系统数据
	 */
	private About initAbout() {
		//连接数据库
		Connection con = null;
		About about = new About();
		try {
			con = dbUtil.getCon();
			ResultSet rs = aboutDao.list(con);
			int index = 0;
			//只查询一条，因为关于系统只有一条
			while (rs.next() && index == 0) {
				about.setAuthor(rs.getString("author"));
				about.setContent(rs.getString("content"));
				about.setEmail(rs.getString("email"));
				about.setVersion(rs.getString("version"));
				index = index + 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return about;
	}
}
