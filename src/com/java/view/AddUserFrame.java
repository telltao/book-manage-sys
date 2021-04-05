package com.java.view;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

@SuppressWarnings("serial")
public class AddUserFrame extends JInternalFrame {
	private JTextField userName_Txt;
	private JTextField passWord_Txt;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserFrame frame = new AddUserFrame();
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
	@SuppressWarnings("rawtypes")
	public AddUserFrame() {
		setClosable(true);
		setIconifiable(true);
		setTitle("用户注册");
		setBounds(100, 100, 519, 543);
//		this.setBounds((900 - 519) / 2,(900 - 543) / 2,519,543);
		JLabel label = new JLabel("姓名：");

		userName_Txt = new JTextField();
		userName_Txt.setColumns(10);

		JLabel label_1 = new JLabel("密码：");

		passWord_Txt = new JTextField();
		passWord_Txt.setColumns(10);

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//?????????????
				bookAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AddUserFrame.class.getResource("/images/add.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(AddUserFrame.class.getResource("/images/reset.png")));

		JButton btnNewButton = new JButton("关闭");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//在此处添加关闭事件
				//e.getModifiers();
			}
		});
		btnNewButton.setIcon(new ImageIcon(AddUserFrame.class.getResource("/images/delete.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(158)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(label_1)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(passWord_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(107)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(button)
																.addGap(140)
																.addComponent(button_1))
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(61)
																.addGroup(groupLayout.createSequentialGroup()
																		.addGap(45)
																		.addComponent(btnNewButton))
																.addPreferredGap(ComponentPlacement.RELATED, 20, GroupLayout.PREFERRED_SIZE)))))
								.addContainerGap(142, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(171)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(passWord_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(107)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(button)
										.addComponent(button_1)
										.addComponent(btnNewButton))
								.addContainerGap(121, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}


	//??????????
	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
	}

	/**
	 * ?????????
	 *
	 * @param evt
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
		//??????????????
		String userName = this.userName_Txt.getText();
		String passWord = this.passWord_Txt.getText();

		//?ж???????
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名为空");
			return;
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "密码为空");
			return;
		}

		//添加实体
		User user = new User(userName, passWord, null);

		//新增到数据库
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = userDao.addBook(con, user);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "用户添加成功");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "用户添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重置值
	 */
	private void resetValue() {
		this.userName_Txt.setText("");
		this.passWord_Txt.setText("");
	}
}
