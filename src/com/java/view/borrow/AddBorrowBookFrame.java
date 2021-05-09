package com.java.view.borrow;

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
import java.sql.Date;

@SuppressWarnings("serial")
public class AddBorrowBookFrame extends JInternalFrame {
	private JTextField borrowName_Txt;
	private JTextField borrowPhone_Txt;
	JComboBox s_bookDate_Jcb = new JComboBox();

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private JTextField borrowBookName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBorrowBookFrame frame = new AddBorrowBookFrame();
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
	public AddBorrowBookFrame() {
		setClosable(true);
		setIconifiable(true);
		setTitle("借阅图书");
//		setBounds(100, 100, 519, 543);
		setBounds(100, 100, 519, 543);

//		this.setBounds((900 - 519) / 2,(900 - 543) / 2,519,543);
		JLabel label = new JLabel("借阅人名称：");

		borrowName_Txt = new JTextField();
		borrowName_Txt.setColumns(10);

		JLabel label_1 = new JLabel("借阅人手机号：");

		borrowPhone_Txt = new JTextField();
		borrowPhone_Txt.setColumns(10);

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//?????????????
				bookAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AddBorrowBookFrame.class.getResource("/images/add.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(AddBorrowBookFrame.class.getResource("/images/reset.png")));

		JLabel label_1_1 = new JLabel("借阅图书名称：");

		JLabel label_1_1_1 = new JLabel("借阅天数：");

		s_bookDate_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "3天", "5天", "10天"}));

		borrowBookName = new JTextField();
		borrowBookName.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap(178, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(102)
												.addComponent(button_1))
										.addComponent(button))
								.addGap(157))
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(125)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(label_1_1)
														.addComponent(label_1)
														.addComponent(label_1_1_1))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(borrowBookName, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
														.addComponent(borrowPhone_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(s_bookDate_Jcb, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(label)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(borrowName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(143, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(98)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(borrowName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(borrowPhone_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1_1)
										.addComponent(borrowBookName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(s_bookDate_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1_1_1))
								.addGap(78)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(button)
										.addComponent(button_1))
								.addContainerGap(134, Short.MAX_VALUE))
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
		//借阅人名称
		String borrowName = this.borrowName_Txt.getText();
		//借阅人手机号
		String borrowPhone = this.borrowPhone_Txt.getText();
		//借阅图书名称
		String borrowBookName = this.borrowBookName.getText();
		//用户名
		//获取选择的借阅天数 -1,0:未选择借阅天数  1为 3天 2为 5天 3为 10天
		Integer bookDate = this.s_bookDate_Jcb.getSelectedIndex();


		//验证条件
		if (StringUtil.isEmpty(borrowName)) {
			JOptionPane.showMessageDialog(null, "请输入借阅人名称");
			return;
		}

		if (StringUtil.isEmpty(borrowPhone)) {
			JOptionPane.showMessageDialog(null, "请输入借阅人手机号");
			return;
		}
		if (StringUtil.isEmpty(borrowBookName)) {
			JOptionPane.showMessageDialog(null, "请输入借阅图书名称");
			return;
		}

		if (bookDate <= 0) {
			JOptionPane.showMessageDialog(null, "请选择借阅天数");
			return;
		}

		//选择借阅天数后,获取到当前时间,并+上借阅的天数 =归还时间
		//当前时间
		Date date = new Date(System.currentTimeMillis());

		//数据库存选择下拉框对应的值
		int selectDay = 0;
		//-1 0为未选择借阅天数  1为 3天 2为 5天 3为 10天
		if (bookDate != -1 && bookDate != 0) {
			switch (bookDate) {
				case 1:
					selectDay = 3;
					break;
				case 2:
					selectDay = 5;
					break;
				case 3:
					selectDay = 10;
					break;
				default:
					selectDay = 0;
					break;
			}
		}

		//添加实体
		User user = null;

		//新增到数据库
		Connection con = null;
		try {
			con = dbUtil.getCon();

			//校验用户名是否已经存在
			User userCheck = null;
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
		this.borrowName_Txt.setText("");
		this.borrowPhone_Txt.setText("");
		this.s_bookDate_Jcb.setSelectedIndex(0);
	}
}
