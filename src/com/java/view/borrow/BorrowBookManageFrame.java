package com.java.view.borrow;

import com.java.dao.BorrowBookDao;
import com.java.dao.UserDao;
import com.java.model.BorrowBook;
import com.java.model.User;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.java.view.book.AddBookFrame;
import com.java.view.booktype.BookTypeManagerFrame;

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
public class BorrowBookManageFrame extends JInternalFrame {
	private JTable userTable;
	private JTextField s_userName_Txt;

	JComboBox borrowStatus = new JComboBox();
	JComboBox bookStatus = new JComboBox();

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private BorrowBookDao borrowBookDao = new BorrowBookDao();
	private JTextField phone_Txt;
	private JTextField bookName_Txt;
	private JTextField penalty;
	private JTextField s_phone_Txt;
	private JTextField id_Txt;
	private JTextField userName_Txt;
	private JTextField borrowDate;
	private JTextField dueDate;
	private JTextField remark;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowBookManageFrame frame = new BorrowBookManageFrame();
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
	public BorrowBookManageFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("借阅列表");
//		setBounds(100, 100, 723, 760);
		this.setBounds((900 - 723) / 2, (900 - 760) / 2, 723, 739);
		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "搜索条件", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel restartPhone = new JPanel();
		restartPhone.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(restartPhone, GroupLayout.PREFERRED_SIZE, 652, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(37)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
														.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGap(28))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(31)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(restartPhone, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(49, Short.MAX_VALUE))
		);

		JLabel label_3 = new JLabel("手机号:");

		phone_Txt = new JTextField();
		phone_Txt.setEditable(false);
		phone_Txt.setColumns(10);

		JLabel label_4 = new JLabel("图书名称:");

		bookName_Txt = new JTextField();
		bookName_Txt.setEditable(false);
		bookName_Txt.setColumns(10);

		JLabel label_6 = new JLabel("借阅时间:");

		//图书修改事件
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userUpdateActionPerformed(evt);
			}
		});
		button_1.setIcon(new ImageIcon(BorrowBookManageFrame.class.getResource("/images/modify.png")));

		//用户删除事件
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userDeleteActionPerformed(evt);
			}
		});
		button_2.setIcon(new ImageIcon(BorrowBookManageFrame.class.getResource("/images/delete.png")));

		JLabel label_6_1 = new JLabel("图书状态：");

		borrowStatus.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "正常", "污损", "缺页"}));

		JLabel label_8_1 = new JLabel("还书状态：");

		bookStatus.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "借阅中", "已丢失", "已还书"}));

		JLabel label_3_1 = new JLabel("罚金:");

		penalty = new JTextField();
		penalty.setColumns(10);

		JLabel label_3_1_1 = new JLabel("（只可输入正整数）");
		label_3_1_1.setForeground(Color.RED);

		JLabel label_3_2 = new JLabel("编号:");

		id_Txt = new JTextField();
		id_Txt.setEditable(false);
		id_Txt.setColumns(10);

		JLabel label_4_1 = new JLabel("用户名:");

		userName_Txt = new JTextField();
		userName_Txt.setEditable(false);
		userName_Txt.setColumns(10);

		borrowDate = new JTextField();
		borrowDate.setEditable(false);
		borrowDate.setColumns(10);

		JLabel label_6_2 = new JLabel("归还时间:");

		dueDate = new JTextField();
		dueDate.setEditable(false);
		dueDate.setColumns(10);

		JLabel label_3_1_2 = new JLabel("备注:");

		remark = new JTextField();
		remark.setColumns(10);
		GroupLayout gl_restartPhone = new GroupLayout(restartPhone);
		gl_restartPhone.setHorizontalGroup(
				gl_restartPhone.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_restartPhone.createSequentialGroup()
								.addGap(198)
								.addComponent(button_1)
								.addGap(122)
								.addComponent(button_2)
								.addContainerGap(204, Short.MAX_VALUE))
						.addGroup(gl_restartPhone.createSequentialGroup()
								.addGap(78)
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addComponent(label_3_1_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(remark, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addGroup(gl_restartPhone.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_restartPhone.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED, 29, GroupLayout.PREFERRED_SIZE)
																.addComponent(label_3_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_restartPhone.createSequentialGroup()
																.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
																		.addComponent(label_6_1)
																		.addGroup(gl_restartPhone.createParallelGroup(Alignment.TRAILING)
																				.addComponent(label_3)
																				.addComponent(label_6))
																		.addComponent(label_3_1, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
																		.addComponent(borrowDate, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_restartPhone.createParallelGroup(Alignment.TRAILING, false)
																				.addComponent(bookStatus, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(phone_Txt, Alignment.LEADING)
																				.addComponent(penalty, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																.addPreferredGap(ComponentPlacement.RELATED)))
												.addGroup(gl_restartPhone.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_restartPhone.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(label_6_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
																.addGap(15)
																.addComponent(dueDate, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_restartPhone.createParallelGroup(Alignment.TRAILING)
																.addGroup(gl_restartPhone.createSequentialGroup()
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(label_4_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																		.addGap(31)
																		.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_restartPhone.createSequentialGroup()
																				.addGap(39)
																				.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
																						.addComponent(label_4)
																						.addComponent(label_8_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
																						.addComponent(borrowStatus, 0, 155, Short.MAX_VALUE)
																						.addComponent(bookName_Txt, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
																		.addGroup(gl_restartPhone.createSequentialGroup()
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addComponent(label_3_1_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.RELATED, 143, GroupLayout.PREFERRED_SIZE)))))))
								.addGap(87))
		);
		gl_restartPhone.setVerticalGroup(
				gl_restartPhone.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_restartPhone.createSequentialGroup()
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addGap(5)
												.addComponent(label_3_2))
										.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addGap(5)
												.addComponent(label_4_1))
										.addComponent(userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
										.addComponent(phone_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)
										.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
												.addComponent(label_6)
												.addComponent(borrowDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addGap(5)
												.addComponent(label_6_2))
										.addComponent(dueDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_8_1)
										.addComponent(borrowStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(bookStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6_1))
								.addGap(18)
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3_1)
										.addComponent(penalty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3_1_1))
								.addGroup(gl_restartPhone.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addGap(56)
												.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
														.addComponent(button_1)
														.addComponent(button_2)))
										.addGroup(gl_restartPhone.createSequentialGroup()
												.addGap(18)
												.addGroup(gl_restartPhone.createParallelGroup(Alignment.BASELINE)
														.addComponent(label_3_1_2)
														.addComponent(remark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGap(49))
		);
		restartPhone.setLayout(gl_restartPhone);

		JLabel label = new JLabel("借阅用户名:");

		s_userName_Txt = new JTextField();
		s_userName_Txt.setColumns(10);

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

		JLabel label_1 = new JLabel("借阅人手机号:");
		//查询页的用户名
		s_phone_Txt = new JTextField();
		s_phone_Txt.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGap(26)
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_userName_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(40)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(s_phone_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
								.addComponent(button)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_3)
								.addGap(30))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(s_userName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(s_phone_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1)
										.addComponent(button_3)
										.addComponent(button))
								.addGap(21))
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
						"编号", "用户名", "手机号", "图书名称", "借阅时间", "归还时间", "图书状态", "还书状态", "罚金", "状态"
				}
		) {
			boolean[] columnEditables = new boolean[]{
					false, false, false, false, false, false, false, false, false, false
			};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});
		userTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		userTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		userTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		userTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		userTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		userTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		userTable.getColumnModel().getColumn(7).setPreferredWidth(30);
		userTable.getColumnModel().getColumn(8).setPreferredWidth(30);
		scrollPane.setViewportView(userTable);
		getContentPane().setLayout(groupLayout);


		//设置JTextArea边框的代码
//		s_status_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

		//查询下拉框方法引用
		this.fillBookType();
		//表单的方法
		this.fillTable(new BorrowBook());
	}

	/**
	 * 借阅用户删除事件方法
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

		//如果图书状态为借阅中,不允许删除
		//图书状态： 获取选中的索引下标 -1 为未选中 值0为请选择  值1为 借阅中，值2为 已丢失 值3为已还书
		//数据库存的状态: 图书状态：0借阅中，1已丢失 2已还书入库的时候,要将值-1
		Integer cashPledgeStatus = bookStatus.getSelectedIndex();
		//如果状态为 借阅中或已丢失,不允许删除 丢失需要去用户列表扣除押金
		if (cashPledgeStatus == 1 || cashPledgeStatus == 2) {
			JOptionPane.showMessageDialog(null, "借阅中或已丢失,不允许删除");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗?");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int deleteNum = borrowBookDao.deleteBorrowBook(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功!");
					this.resetUpdateValue();
					this.fillTable(new BorrowBook());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "系统异常,删除失败!");
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
		String phone = this.phone_Txt.getText();
		String bookName = this.bookName_Txt.getText();
		String borrowDate = this.borrowDate.getText();
		String dueDate = this.dueDate.getText();
		//图书状态： 获取选中的索引下标 -1 为未选中 值0为请选择  值1为 借阅中，值2为 已丢失 值3为已还书
		//数据库存的状态: 图书状态：0借阅中，1已丢失 2已还书入库的时候,要将值-1
		Integer bookStatus = this.bookStatus.getSelectedIndex();
		//还书状态
		Integer borrowStatus = this.borrowStatus.getSelectedIndex();

		//罚金
		String penalty = this.penalty.getText();

		//判断id是否为空，若为空，则不能修改
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
/*

		//判断其他信息是否为空
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}

		if (StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "手机号码不能为空！");
			return;
		}
*/

		if (StringUtil.isNotEmpty(penalty)) {
			//自己写的工具类提供 加个感叹号就是否
			if (!StringUtil.isNumbers(penalty)) {
				//校验罚款金额是否为小数
				JOptionPane.showMessageDialog(null, "罚款金额格式有误！");
				return;
			}
		}



		//进行添加操作，数据库连接
		Connection con = null;
		try {
			con = dbUtil.getCon();
		/*
			//添加实体
			User user = new User();
			user.setUserName(userName);
			user.setPhone(phone);

			修改只可修改图书的状态,不可以修改用户名,手机号啥的,所以在此处禁用了无法编辑
			//校验用户名是否已经存在
			User checkoutUser = userDao.checkBookBorrow(con, user);
			if (checkoutUser == null) {
				JOptionPane.showMessageDialog(null, "借阅人名称或借阅人手机号有误,请确认");
				return;
			}
			//验证账号状态
			String status = checkoutUser.getStatus();
			//账号已禁用,无法借阅
			if (status.equals("1")) {
				JOptionPane.showMessageDialog(null, "该账号已被禁用,请联系管理员");
				return;
			}

			//校验押金
			String cashPledgeStauts = checkoutUser.getCashPledgeStauts();
			// 这里加个 ! 表示 状态为没有交押金
			if (cashPledgeStauts == null || !cashPledgeStauts.equals("1")) {
				//弹窗 告诉哪个用户未交押金
				JOptionPane.showMessageDialog(null, userName + "未交押金,请先去缴纳押金");
				return;
			} else if (cashPledgeStauts.equals("1")) {
				//已交押金,校验押余额是否大于10元
				if (checkoutUser.getCashPledge() < 10) {
					JOptionPane.showMessageDialog(null, userName + "押金余额不足,必须大于10元人民币");
					return;
				}
			}*/

			BorrowBook borrowBook = new BorrowBook();
			borrowBook.setId(Integer.valueOf(id));
			//borrowBook.setUserName(userName);
			//borrowBook.setBookPhone(phone);
			//图书状态： 获取选中的索引下标 -1 为未选中 值0为请选择  值1为 借阅中，值2为 已丢失 值3为已还书
			//数据库存的状态: 图书状态：0借阅中，1已丢失 2已还书入库的时候,要将值-1
			borrowBook.setBookStatus(String.valueOf((bookStatus - 1)));
			//还书状态 0正常，1污损 2缺页 -1 为未选中 值0为请选择  值1为 正常，值2为 污损 值3为缺页
			//数据库存的状态: 图书状态：0借阅中，1已丢失 2已还书入库的时候,要将值-1
			borrowBook.setBorrowStatus(String.valueOf((borrowStatus - 1)));
			//将字符串转换为浮动类型数据
			borrowBook.setPenalty(Integer.valueOf(penalty));
			borrowBook.setRemark(remark.getText());

			int addNum = borrowBookDao.updateBook(con, borrowBook);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "借阅信息修改成功！");
				//清空表单
				resetValue();
				//填充表单
				this.fillTable(new BorrowBook());
			} else {
				JOptionPane.showMessageDialog(null, "借阅信息修改失败！");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "借阅信息修改失败！");
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
		this.s_phone_Txt.setText("");
		//重置时，重新查询
		this.fillTable(new BorrowBook());
	}

	/**
	 * 重置修改的表单
	 */
	private void resetUpdateValue() {
		//将id清空则无法再次修改
		this.id_Txt.setText("");
		//重置时，重新查询
		this.fillTable(new BorrowBook());
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
		this.phone_Txt.setText((String) userTable.getValueAt(row, 2));
		//获取第row行，第4列的值
		this.bookName_Txt.setText((String) userTable.getValueAt(row, 3));
		//在此处给修改处的下拉框赋值
		this.borrowDate.setText((String) userTable.getValueAt(row, 4));
		this.dueDate.setText((String) userTable.getValueAt(row, 5));


		//图书状态：0借阅中，1已丢失 2已还书
		String cashPledgeStatus = userTable.getValueAt(row, 6).toString();
		if ("借阅中".equals(cashPledgeStatus)) {
			this.bookStatus.setSelectedIndex(1);
		} else if ("已丢失".equals(cashPledgeStatus)) {
			this.bookStatus.setSelectedIndex(2);
		} else if ("已还书".equals(cashPledgeStatus)) {
			this.bookStatus.setSelectedIndex(3);
		} else {
			this.bookStatus.setSelectedIndex(0);
		}
		//还书状态： 0正常，1污损 2缺页
		String borrowStatus = userTable.getValueAt(row, 7).toString();
		if ("正常".equals(borrowStatus)) {
			this.borrowStatus.setSelectedIndex(1);
		} else if ("污损".equals(borrowStatus)) {
			this.borrowStatus.setSelectedIndex(2);
		} else if ("缺页".equals(borrowStatus)) {
			this.borrowStatus.setSelectedIndex(3);
		} else {
			this.borrowStatus.setSelectedIndex(0);
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
		String userName = this.s_userName_Txt.getText();
		String phone = this.s_phone_Txt.getText();

		BorrowBook borrowBook = new BorrowBook();
		borrowBook.setUserName(userName);
		borrowBook.setBookPhone(phone);
		this.fillTable(borrowBook);
	}


	/**
	 * 初始化表格数据
	 *
	 * @param book
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void fillTable(BorrowBook book) {
		DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
		//清空表格
		dtm.setRowCount(0);
		//连接数据库
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = borrowBookDao.list(con, book);
			while (rs.next()) {
				Vector v = new Vector();
				//id
				v.add(rs.getString("id"));
				//用户名
				v.add(rs.getString("userName"));
				//手机号码
				v.add(rs.getString("bookPhone"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("borrowDate"));
				v.add(rs.getString("dueDate"));
				//图书状态
				if (rs.getString("bookStatus").equals("0")) {
					v.add("借阅中");
				} else if ((rs.getString("bookStatus").equals("1"))) {
					v.add("已丢失");
				} else if ((rs.getString("bookStatus").equals("2"))) {
					v.add("已还书");
				} else {
					v.add("未知图书状态");
				}

				//还书状态
				if (rs.getString("borrowStatus").equals("0")) {
					v.add("正常");
				} else if ((rs.getString("borrowStatus").equals("1"))) {
					v.add("污损");
				} else if ((rs.getString("borrowStatus").equals("2"))) {
					v.add("缺页");
				} else {
					v.add("未知还书状态");
				}
				v.add(rs.getString("penalty"));
				//状态
				if (rs.getString("status").equals("0")) {
					v.add("正常");
				} else {
					v.add("已删除");
				}
				v.add(rs.getString("remark"));
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

	}
}
