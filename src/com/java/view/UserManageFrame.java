package com.java.view;

import com.java.dao.UserDao;
import com.java.model.BookType;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

@SuppressWarnings("serial")
public class UserManageFrame extends JInternalFrame {
	private JTable userTable;
	private JTextField s_userName_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox s_status_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox s_status_Txt_Update;

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private JTextField id_Txt;
	private JTextField userName_Txt;
	private JTextField passWord_Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManageFrame frame = new UserManageFrame();
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
	public UserManageFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("用户管理");
		setBounds(100, 100, 723, 660);
//		this.setBounds((900 - 723) / 2,(900 - 660) / 2,723,660);
		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(37)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
												.addContainerGap())
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
														.addComponent(panel, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
												.addGap(28))))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(31)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
								.addGap(36))
		);

		JLabel label_3 = new JLabel("编号:");

		id_Txt = new JTextField();
		id_Txt.setEditable(false);
		id_Txt.setColumns(10);

		JLabel label_4 = new JLabel("用户名称:");

		userName_Txt = new JTextField();
		userName_Txt.setColumns(10);

		JLabel label_6 = new JLabel("用户密码:");

		passWord_Txt = new JTextField();
		passWord_Txt.setColumns(10);

		JLabel label_8 = new JLabel("用户状态:");

		s_status_Txt_Update = new JComboBox();

		//图书修改事件
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userUpdateActionPerformed(evt);
			}
		});
		button_1.setIcon(new ImageIcon(UserManageFrame.class.getResource("/images/modify.png")));

		//用户删除事件
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userDeleteActionPerformed(evt);
			}
		});
		button_2.setIcon(new ImageIcon(UserManageFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(101)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(label_6, Alignment.TRAILING)
														.addComponent(label_3, Alignment.TRAILING))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(passWord_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(55)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_4)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_8)
																.addGap(18)
																.addComponent(s_status_Txt_Update, 0, 124, Short.MAX_VALUE))))
										.addGroup(gl_panel_1.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 125, GroupLayout.PREFERRED_SIZE)
												.addComponent(button_1)
												.addGap(105)
												.addComponent(button_2)
												.addGap(99)))
								.addGap(92))
		);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(14)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)
										.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_8)
										.addComponent(s_status_Txt_Update, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6)
										.addComponent(passWord_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(57)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(button_2)
										.addComponent(button_1))
								.addContainerGap(126, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		JLabel label = new JLabel("用户名称:");

		s_userName_Txt = new JTextField();
		s_userName_Txt.setColumns(10);

		JLabel label_2 = new JLabel("用户状态:");
		s_status_Txt = new JComboBox();

		//用户查询事件
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookTypeManagerFrame.class.getResource("/images/search.png")));


		JButton button_3 = new JButton("重置");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		button_3.setIcon(new ImageIcon(AddBookFrame.class.getResource("/images/reset.png")));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_userName_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(label_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_status_Txt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(s_userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2)
										.addComponent(s_status_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(button)
										.addComponent(button_3))
								.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		//userTable的行点击事件
		userTable = new JTable();
		userTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				userTableMousePressed(met);
			}
		});
		userTable.setModel(new DefaultTableModel(
				new Object[][]{
				},
				new String[]{
						"编号", "用户名称", "密码", "状态"
				}
		) {
			boolean[] columnEditables = new boolean[]{
					false, false, false, false
			};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});
		userTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(97);
		userTable.getColumnModel().getColumn(2).setPreferredWidth(93);
		userTable.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPane.setViewportView(userTable);
		getContentPane().setLayout(groupLayout);


		//设置JTextArea边框的代码
//		s_status_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

		//查询下拉框方法引用
		this.fillBookType();
		//表单的方法
		this.fillTable(new User());
	}

	/**
	 * 用户删除事件方法
	 *
	 * @param evt
	 */
	private void userDeleteActionPerformed(ActionEvent evt) {
		//获取界面信息
		String id = id_Txt.getText();
		//判断id是否为空，若为空，则不能删除
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗?");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int deleteNum = userDao.deleteUser(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功!");
					this.resetUpdateValue();
					this.fillTable(new User());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败!");
			} finally {
				try {
					dbUtil.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 用户修改事件方法
	 *
	 * @param evt
	 */
	private void userUpdateActionPerformed(ActionEvent evt) {
		//获取界面信息
		String id = this.id_Txt.getText();
		String userName = this.userName_Txt.getText();
		String passWord = this.passWord_Txt.getText();
		String status = this.s_status_Txt_Update.getSelectedItem().toString();

		//状态 0启用 1禁用
		if (status.equals("禁用")) {
			status = "1";
		} else {
			status = "0";
		}
		//判断id是否为空，若为空，则不能修改
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
		//判断其他信息是否为空
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名称不能为空！");
			return;
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "用户密码不能为空！");
			return;
		}
		if (StringUtil.isEmpty(status)) {
			JOptionPane.showMessageDialog(null, "用户状态不能为空！");
			return;
		}

		User user = new User(Integer.valueOf(id), userName, passWord, status);

		//进行添加操作，数据库连接
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = userDao.updateUser(con, user);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "用户修改成功！");
				//清空表单
				resetValue();
				//填充表单
				this.fillTable(new User());
			} else {
				JOptionPane.showMessageDialog(null, "用户修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "用户修改失败！");
		} finally {
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.s_userName_Txt.setText("");
		if (this.s_status_Txt.getItemCount() > 0) {
			//默认设置选中第一项
			this.s_status_Txt.setSelectedIndex(0);
		}
		//重置时，重新查询
		this.fillTable(new User());
	}

	/**
	 * 重置修改的表单
	 */
	private void resetUpdateValue() {
		this.id_Txt.setText("");
		this.userName_Txt.setText("");
		this.passWord_Txt.setText("");
		this.s_status_Txt_Update.removeAllItems();
		if (this.s_status_Txt.getItemCount() > 0) {
			//默认设置选中第一项
			this.s_status_Txt.setSelectedIndex(0);
		}
		//重置时，重新查询
		this.fillTable(new User());
	}


	//表格行点击事件方法
	private void userTableMousePressed(MouseEvent met) {
		//获取选中的行，返回行号 下标从0开始
		int row = this.userTable.getSelectedRow();
		//获取第row行，第1列的值
		this.id_Txt.setText((String) userTable.getValueAt(row, 0));
		//获取第row行，第2列的值
		this.userName_Txt.setText((String) userTable.getValueAt(row, 1));
		//获取第row行，第3列的值
		this.passWord_Txt.setText((String) userTable.getValueAt(row, 2));
		//在此处给修改处的下拉框赋值
		String select_status = (String) userTable.getValueAt(row, 3);
		//先删除再重置
		this.s_status_Txt_Update.removeAllItems();
		if (select_status.equals("启用")) {
			this.s_status_Txt_Update.addItem(select_status);
			this.s_status_Txt_Update.addItem("禁用");
		} else {
			this.s_status_Txt_Update.addItem("禁用");
			this.s_status_Txt_Update.addItem("启用");
		}
	}

	/**
	 * 用户查询功能
	 *
	 * @param e
	 */
	private void userSearchActionPerformed(ActionEvent e) {
		//从界面获取信息
		String userName = this.s_userName_Txt.getText();
		String selectedItem = this.s_status_Txt.getSelectedItem().toString();
		//状态 0启用 1禁用
		String status = "0";
		if (selectedItem.equals("禁用")) {
			status = "1";
		} else {
			status = "";
		}
		User user = new User(userName, null, status);
		this.fillTable(user);
	}


	/**
	 * 初始化表格数据
	 *
	 * @param user
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void fillTable(User user) {
		DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
		//清空表格
		dtm.setRowCount(0);
		//连接数据库
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = userDao.list(con, user);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("userName"));
				v.add(rs.getString("passWord"));
				if (rs.getString("status").equals("0")) {
					v.add("启用");
				} else {
					v.add("禁用");
				}
				v.add(rs.getDate("createTime"));
				dtm.addRow(v);
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
	}

	/**
	 * 初始化下拉框
	 */
	@SuppressWarnings("unchecked")
	private void fillBookType() {
		this.s_status_Txt.addItem("请选择...");
		this.s_status_Txt.addItem("启用");
		this.s_status_Txt.addItem("禁用");

	}
}
