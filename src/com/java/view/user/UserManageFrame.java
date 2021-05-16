package com.java.view.user;

import com.java.dao.UserDao;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.java.view.booktype.BookTypeManagerFrame;
import com.java.view.book.AddBookFrame;

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
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class UserManageFrame extends JInternalFrame {
	private JTable userTable;
	private JTextField s_loginName_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox s_status_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox s_status_Txt_Update;

	JComboBox s_cashPledge_Jcb = new JComboBox();
	JComboBox s_cashPledgeStatus_Jcb = new JComboBox();

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private JTextField userName_Txt;
	private JTextField phone_Txt;
	private JTextField penalty;
	private JTextField s_userName_Txt;
	private JTextField id_Txt;
	private JTextField loginName_Txt;

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
		setTitle("用户列表");
//		setBounds(100, 100, 723, 760);
		this.setBounds((900 - 723) / 2, (900 - 760) / 2, 723, 724);
		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel restartPhone = new JPanel();
		restartPhone.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u7528\u6237\u5217\u8868\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(restartPhone, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(restartPhone, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		JLabel label_3 = new JLabel("用户名:");

		userName_Txt = new JTextField();
		userName_Txt.setColumns(10);

		JLabel label_4 = new JLabel("手机号:");

		phone_Txt = new JTextField();
		phone_Txt.setColumns(10);

		JLabel label_6 = new JLabel("用户密码:");

		JLabel label_8 = new JLabel("用户状态:");

		s_status_Txt_Update = new JComboBox();

		//图书修改事件
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userUpdateActionPerformed(evt);
			}
		});
		button_1.setIcon(new ImageIcon(UserManageFrame.class.getResource("/images/4修改.png")));

		//用户删除事件
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userDeleteActionPerformed(evt);
			}
		});
		button_2.setIcon(new ImageIcon(UserManageFrame.class.getResource("/images/删 除 .png")));

		JLabel label_6_1 = new JLabel("押金状态：");

		s_cashPledge_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "20元", "30元", "50元"}));

		JLabel label_8_1 = new JLabel("押金金额：");

		s_cashPledgeStatus_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "已交押金", "未交押金", "已退押金"}));

		JLabel label_3_1 = new JLabel("罚款金额:");

		penalty = new JTextField();
		penalty.setColumns(10);

		JLabel label_3_1_1 = new JLabel("（只可输入正整数）");
		label_3_1_1.setForeground(Color.RED);

		JLabel label_3_2 = new JLabel("编号:");

		id_Txt = new JTextField();
		id_Txt.setEditable(false);
		id_Txt.setColumns(10);

		JButton button_1_1 = new JButton("重置密码");
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//重置密码
				resetPassword();
			}
		});

		JLabel label_4_1 = new JLabel("登录名:");

		loginName_Txt = new JTextField();
		loginName_Txt.setEditable(false);
		loginName_Txt.setColumns(10);
		GroupLayout gl_restartPhone = new GroupLayout(restartPhone);
		gl_restartPhone.setHorizontalGroup(
			gl_restartPhone.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_restartPhone.createSequentialGroup()
					.addGap(78)
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addComponent(label_3_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
							.addComponent(label_6_1)
							.addComponent(label_6)
							.addComponent(label_3_1, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addComponent(label_3)))
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
								.addComponent(penalty, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(s_cashPledgeStatus_Jcb, 0, 130, Short.MAX_VALUE)
								.addComponent(button_1_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
						.addComponent(userName_Txt, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addComponent(id_Txt, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4)
								.addComponent(label_4_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_8)
								.addComponent(label_8_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
								.addComponent(loginName_Txt, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
								.addComponent(s_status_Txt_Update, Alignment.TRAILING, 0, 155, Short.MAX_VALUE)
								.addComponent(s_cashPledge_Jcb, 0, 136, Short.MAX_VALUE)
								.addComponent(phone_Txt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
							.addContainerGap(64, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addComponent(label_3_1_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_restartPhone.createSequentialGroup()
					.addGap(241)
					.addComponent(button_1)
					.addGap(64)
					.addComponent(button_2)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		gl_restartPhone.setVerticalGroup(
			gl_restartPhone.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_restartPhone.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
							.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_3_2))
						.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_4_1)
							.addComponent(loginName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addGap(12)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addGap(18)
							.addComponent(phone_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_6)
								.addComponent(button_1_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_restartPhone.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_8)
								.addComponent(s_status_Txt_Update, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
						.addComponent(s_cashPledgeStatus_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6_1)
						.addComponent(label_8_1)
						.addComponent(s_cashPledge_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3_1)
						.addComponent(penalty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3_1_1))
					.addGap(18)
					.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addComponent(button_2)
						.addComponent(button_1))
					.addGap(47))
		);
		restartPhone.setLayout(gl_restartPhone);

		JLabel label = new JLabel("登录名:");

		s_loginName_Txt = new JTextField();
		s_loginName_Txt.setColumns(10);

		JLabel label_2 = new JLabel("用户状态:");
		s_status_Txt = new JComboBox();

		//用户查询事件
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(UserManageFrame.class.getResource("/images/查询-default.png")));


		JButton button_3 = new JButton("重置");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		button_3.setIcon(new ImageIcon(UserManageFrame.class.getResource("/images/重置.png")));

		JLabel label_1 = new JLabel("用户名:");
		//查询页的用户名
		s_userName_Txt = new JTextField();
		s_userName_Txt.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_loginName_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_userName_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(label_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_status_Txt, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_3)
					.addGap(44))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(label_2)
							.addComponent(s_status_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(button)
							.addComponent(button_3)
							.addComponent(s_loginName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(s_userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(label_1)))
					.addGap(1))
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
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u767B\u5F55\u540D", "\u7528\u6237\u540D", "\u624B\u673A\u53F7", "\u72B6\u6001", "\u521B\u5EFA\u65F6\u95F4", "\u62BC\u91D1\u72B6\u6001", "\u62BC\u91D1\u91D1\u989D(\u5143)", "\u7F5A\u6B3E\u91D1\u989D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		userTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		userTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		userTable.getColumnModel().getColumn(3).setPreferredWidth(93);
		userTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		userTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		userTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		userTable.getColumnModel().getColumn(7).setPreferredWidth(50);
		userTable.getColumnModel().getColumn(8).setPreferredWidth(50);
		scrollPane.setViewportView(userTable);
		getContentPane().setLayout(groupLayout);

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
		String loginName = this.loginName_Txt.getText();
		String userName = this.userName_Txt.getText();
//		String passWord = this.passWord_Txt.getText();
		String phone = this.phone_Txt.getText();
		//状态
		String status = this.s_status_Txt_Update.getSelectedItem().toString();
		//是否缴纳押金    获取选中的索引下标 -1 为未选中 0为 请选择 1为已缴纳 2为未缴纳
		Integer cashPledgeStatus = this.s_cashPledgeStatus_Jcb.getSelectedIndex();
		//获取缴纳押金的钱选中的下标 -1 0为未选择或者未缴纳押金  1为 20元 2为 30元 3为 50元
		Integer cashPledge = this.s_cashPledge_Jcb.getSelectedIndex();
		//状态 0启用 1禁用
		if (status.equals("禁用")) {
			status = "1";
		} else {
			status = "0";
		}
		//罚款金额
		String penalty = this.penalty.getText();

		//判断id是否为空，若为空，则不能修改
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
		//判断其他信息是否为空
		if (StringUtil.isEmpty(loginName)) {
			JOptionPane.showMessageDialog(null, "登录名不能为空！");
			return;
		}
		//判断其他信息是否为空
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		/*if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "用户密码不能为空！");
			return;
		}*/
		if (StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "手机号码不能为空！");
			return;
		}
		if (StringUtil.isEmpty(status)) {
			JOptionPane.showMessageDialog(null, "用户状态不能为空！");
			return;
		}
		if (StringUtil.isNotEmpty(penalty)) {
			//自己写的工具类提供 加个感叹号就是否
			if (!StringUtil.isNumbers(penalty)) {
				//校验罚款金额是否为小数
				JOptionPane.showMessageDialog(null, "罚款金额格式有误！");
				return;
			}
		}

		//cashPledgeStatus：为1表示选择的是 1为已缴纳 则需要去验证是否选择押金下拉框 <=0 表示未选择押金
		if (cashPledgeStatus == 1 && cashPledge <= 0) {
			JOptionPane.showMessageDialog(null, "缴纳押金需要选择押金金额");
			return;
		}

		//数据库存选择下拉框对应的值
		int newCashPledge = 0;
		//获取缴纳押金的钱选中的下标 -1 1为未选择或者未缴纳押金  2为 20元 3为 30元 4为 50元 此处转换存数据库
		if (cashPledge != -1 && cashPledge != 0) {
			switch (cashPledge) {
				case 1:
					newCashPledge = 20;
					break;
				case 2:
					newCashPledge = 30;
					break;
				case 3:
					newCashPledge = 50;
					break;
				default:
					newCashPledge = 0;
					break;
			}
		}

		User user = new User(Integer.valueOf(id), loginName, userName, phone, null, status, newCashPledge,
				null, cashPledgeStatus.toString(), Integer.valueOf(penalty));

		//进行添加操作，数据库连接
		Connection con = null;
		try {
			con = dbUtil.getCon();

			//校验用户名是否已经存在 根据登录名查询状态为启用的
			User userCheck = new User(loginName, null, "0");
			User returnUser = userDao.login(con, userCheck);
			if (returnUser != null) {
				JOptionPane.showMessageDialog(null, "该登录名已存在，请修改其他登录名后重试");
				return;
			}

			int addNum = userDao.updateUser(con, user);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "用户修改成功！");
				//清空表单
				resetValue();
				//填充表单
				this.fillTable(new User());
			} else {
				JOptionPane.showMessageDialog(null, "用户修改失败！");
				return;
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
		this.s_loginName_Txt.setText("");
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
		this.userName_Txt.setText("");
		this.phone_Txt.setText("");
//		this.passWord_Txt.setText("");
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
		this.loginName_Txt.setText((String) userTable.getValueAt(row, 1));
		//获取第row行，第3列的值
		this.userName_Txt.setText((String) userTable.getValueAt(row, 2));
		//获取第row行，第4列的值
		this.phone_Txt.setText((String) userTable.getValueAt(row, 3));
		//在此处给修改处的下拉框赋值
		String select_status = (String) userTable.getValueAt(row, 4);
		//先删除再重置
		this.s_status_Txt_Update.removeAllItems();
		if ("启用".equals(select_status)) {
			this.s_status_Txt_Update.addItem(select_status);
			this.s_status_Txt_Update.addItem("禁用");
		} else {
			this.s_status_Txt_Update.addItem("禁用");
			this.s_status_Txt_Update.addItem("启用");
		}

		//押金状态  0未交押金 1已交押金 2已退押金
		String cashPledgeStatus = userTable.getValueAt(row, 6).toString();
		if ("已交押金".equals(cashPledgeStatus)) {
			this.s_cashPledgeStatus_Jcb.setSelectedIndex(1);
		} else if ("已退押金".equals(cashPledgeStatus)) {
			this.s_cashPledgeStatus_Jcb.setSelectedIndex(2);
		} else {
			this.s_cashPledgeStatus_Jcb.setSelectedIndex(0);
		}
		//押金金额
		String cashPledge = userTable.getValueAt(row, 7).toString();
		if ("20".equals(cashPledge)) {
			this.s_cashPledge_Jcb.setSelectedIndex(1);
		} else if ("30".equals(cashPledge)) {
			this.s_cashPledge_Jcb.setSelectedIndex(2);
		} else if ("50".equals(cashPledge)) {
			this.s_cashPledge_Jcb.setSelectedIndex(3);
		} else {
			this.s_cashPledge_Jcb.setSelectedIndex(0);
		}
		//罚款金额
		this.penalty.setText(userTable.getValueAt(row, 8).toString());
	}

	/**
	 * 用户查询功能
	 *
	 * @param e
	 */
	private void userSearchActionPerformed(ActionEvent e) {
		//从界面获取信息
		String loginName = this.s_loginName_Txt.getText();
		String userName = this.s_userName_Txt.getText();
		String selectedItem = this.s_status_Txt.getSelectedItem().toString();
		//状态 0启用 1禁用
		String status = "0";
		if (selectedItem.equals("禁用")) {
			status = "1";
		} else if (selectedItem.equals("请选择...")) {
			status = null;
		}
		User user = new User(0, loginName, userName, null, null, status, 0, null, null, 0);

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
				//id
				v.add(rs.getString("id"));
				//登录名
				v.add(rs.getString("loginName"));
				//用户名
				v.add(rs.getString("userName"));
				//手机号码
				v.add(rs.getString("phone"));
				//状态
				if (rs.getString("status").equals("0")) {
					v.add("启用");
				} else {
					v.add("禁用");
				}

				//创建时间
				v.add(rs.getDate("createTime"));
				//押金状态： 0未交押金 1已交押金 2已退押金
				int cashPledgeStauts = rs.getInt("cashPledgeStauts");

				if (cashPledgeStauts == 1) {
					v.add("已交押金");
				} else if (cashPledgeStauts == 2) {
					v.add("已交押金");
				} else {
					v.add("未交押金");
				}
				//押金
				v.add(rs.getInt("cashPledge"));
				//罚款金额
				v.add(rs.getInt("penalty"));
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


	/**
	 * 重置密码
	 */
	private void resetPassword() {
		String resetPasswordId = this.id_Txt.getText();
		if (StringUtil.isEmpty(resetPasswordId)) {
			JOptionPane.showMessageDialog(null, "请选择用户之后再重置");
			return;
		}
		//重置密码
		int result = JOptionPane.showConfirmDialog(null, "确定要将密码重置为 0000 吗?");
		if (result == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				//修改密码
				int resetPasswordStatus = userDao.resetPassword(con, resetPasswordId);
				if (resetPasswordStatus == 1) {
					JOptionPane.showMessageDialog(null, "重置成功!");
					this.resetUpdateValue();
					this.fillTable(new User());
				} else {
					JOptionPane.showMessageDialog(null, "重置失败,请重试!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "重置失败,请重试!");
			} finally {
				try {
					dbUtil.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


	}
}
