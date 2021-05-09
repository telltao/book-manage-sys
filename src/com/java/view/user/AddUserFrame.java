package com.java.view.user;

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
import java.util.Date;

@SuppressWarnings("serial")
public class AddUserFrame extends JInternalFrame {
	private JTextField loginName_Txt;
	private JTextField passWord_Txt;
	JComboBox s_cashPledgeStatus_Jcb = new JComboBox();
	JComboBox s_cashPledge_Jcb = new JComboBox();

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private JTextField userName_Txt;
	private JTextField phoneField;

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
//		setBounds(100, 100, 519, 543);
		setBounds(100, 100, 519, 543);

//		this.setBounds((900 - 519) / 2,(900 - 543) / 2,519,543);
		JLabel label = new JLabel("登录名：");

		loginName_Txt = new JTextField();
		loginName_Txt.setColumns(10);

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

		JLabel label_1_1 = new JLabel("押金状态：");

		s_cashPledgeStatus_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "已交押金", "未交押金", "已退押金"}));

		JLabel label_1_1_1 = new JLabel("押金金额：");

		s_cashPledge_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "20元", "30元", "50元"}));

		JLabel label_2 = new JLabel("姓名：");

		userName_Txt = new JTextField();
		userName_Txt.setColumns(10);

		JLabel label_1_2 = new JLabel("手机号码：");

		phoneField = new JTextField();
		phoneField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(102)
																.addComponent(button_1))
														.addComponent(button)))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGap(125)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																		.addComponent(label_1_1)
																		.addComponent(label_1_1_1))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(s_cashPledgeStatus_Jcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(s_cashPledge_Jcb, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
														.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
																		.addGap(6)
																		.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(label)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(loginName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																				.addComponent(label_1_2, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
																		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
																				.addComponent(label_1)
																				.addPreferredGap(ComponentPlacement.UNRELATED)
																				.addComponent(passWord_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
								.addGap(169))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(54)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(5)
												.addComponent(label_2))
										.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(loginName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(passWord_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1_2))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(s_cashPledgeStatus_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1_1))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(s_cashPledge_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1_1_1))
								.addGap(57)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(button)
										.addComponent(button_1))
								.addContainerGap(116, Short.MAX_VALUE))
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
		//登录名
		String loginName = this.loginName_Txt.getText();
		//密码
		String passWord = this.passWord_Txt.getText();
		//用户名
		String userName = this.userName_Txt.getText();
		//手机号码
		String phone = this.phoneField.getText();
		//是否缴纳押金    获取选中的索引下标 -1 为未选中 0为 请选择 1为已缴纳 2为未缴纳
		Integer cashPledgeStatus = this.s_cashPledgeStatus_Jcb.getSelectedIndex();
		//获取缴纳押金的钱选中的下标 -1 0为未选择或者未缴纳押金  1为 20元 2为 30元 3为 50元
		Integer cashPledge = this.s_cashPledge_Jcb.getSelectedIndex();


		//验证条件
		if (StringUtil.isEmpty(loginName)) {
			JOptionPane.showMessageDialog(null, "登录名为空");
			return;
		}
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名为空");
			return;
		}
		if (StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "手机号码为空");
			return;
		}
		if (StringUtil.isEmpty(passWord)) {
			JOptionPane.showMessageDialog(null, "密码为空");
			return;
		}
		/*if (cashPledgeStatus == 0 || cashPledgeStatus == -1) {
			JOptionPane.showMessageDialog(null, "请确认是否缴纳押金");
			return;
		} else {*/
		//cashPledgeStatus：为1表示选择的是 1为已缴纳 则需要去验证是否选择押金下拉框 <=0 表示未选择押金
		if (cashPledgeStatus == 1 && cashPledge <= 0) {
			JOptionPane.showMessageDialog(null, "缴纳押金需要选择押金金额");
			return;
		}
		//	}

		//数据库存选择下拉框对应的值
		int newCashPledge = 0;
		//-1 0为未选择或者未缴纳押金  1为 20元 2为 30元 3为 50元
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

		//添加实体
		User user = new User(0, loginName, userName, phone, passWord, null, newCashPledge, new Date(), cashPledgeStatus.toString(), 0);

		//新增到数据库
		Connection con = null;
		try {
			con = dbUtil.getCon();

			//校验用户名是否已经存在
			User userCheck = new User(loginName, null, "0");
			User returnUser = userDao.login(con, userCheck);
			if (returnUser != null) {
				JOptionPane.showMessageDialog(null, "该登录名已存在，请修改后重试");
				return;
			}
			int addNum = userDao.addBook(con, user);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "用户添加成功");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "用户添加失败");
				return;
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
		this.loginName_Txt.setText("");
		this.userName_Txt.setText("");
		this.passWord_Txt.setText("");
		this.s_cashPledgeStatus_Jcb.setSelectedIndex(0);
		this.s_cashPledge_Jcb.setSelectedIndex(0);
	}
}
