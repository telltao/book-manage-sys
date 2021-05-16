package com.java.view.borrow;

import com.java.dao.BookDao;
import com.java.dao.BorrowBookDao;
import com.java.dao.UserDao;
import com.java.model.Book;
import com.java.model.BorrowBook;
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
	private BookDao bookDao = new BookDao();
	private BorrowBookDao borrowBookDao = new BorrowBookDao();

	private JTextField borrowBookId;
	private JTextField remarkTest;

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
		JLabel label = new JLabel("借阅用户名：");

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
		button.setIcon(new ImageIcon(AddBorrowBookFrame.class.getResource("/images/1添加.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??????????
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(AddBorrowBookFrame.class.getResource("/images/重置.png")));

		JLabel label_1_1 = new JLabel("借阅图书编号：");

		JLabel label_1_1_1 = new JLabel("借阅天数：");

		s_bookDate_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "3天", "5天", "10天"}));

		borrowBookId = new JTextField();
		borrowBookId.setColumns(10);

		JLabel label_1_1_2 = new JLabel("备注:");

		remarkTest = new JTextField();
		remarkTest.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(125)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(label_1_1)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)))
						.addComponent(label_1_1_1)
						.addComponent(label_1_1_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(102)
								.addComponent(button_1))
							.addComponent(button)
							.addComponent(remarkTest, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(borrowName_Txt, Alignment.TRAILING)
							.addComponent(borrowBookId, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addComponent(borrowPhone_Txt)
							.addComponent(s_bookDate_Jcb, 0, 179, Short.MAX_VALUE)))
					.addGap(140))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(98)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(borrowName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(borrowPhone_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1_1)
						.addComponent(borrowBookId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(s_bookDate_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1_1_1))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(remarkTest, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1_1_2))
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addGap(94))
		);
		getContentPane().setLayout(groupLayout);
	}


	//重置
	private void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
	}

	/**
	 * 添加借阅
	 *
	 * @param evt
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
		//借阅人名称
		String userName = this.borrowName_Txt.getText();
		//借阅人手机号
		String phone = this.borrowPhone_Txt.getText();
		//借阅图书编号
		String bookId = this.borrowBookId.getText();
		//获取选择的借阅天数 -1,0:未选择借阅天数  1为 3天 2为 5天 3为 10天
		Integer bookDate = this.s_bookDate_Jcb.getSelectedIndex();


		//验证条件
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "请输入借阅用户名");
			return;
		}
		if (StringUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "请输入借阅人手机号");
			return;
		}
		if (StringUtil.isEmpty(bookId)) {
			JOptionPane.showMessageDialog(null, "请输入借阅图书编号");
			return;
		}
		if (bookDate <= 0) {
			JOptionPane.showMessageDialog(null, "请选择借阅天数");
			return;
		}

		//获取到系统当前时间时间戳 毫秒
		long timeMillis = System.currentTimeMillis();
		Date borrowDate = new Date(timeMillis);

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
		//还书时间 //选择借阅天数后,获取到当前时间,并+上借阅的天数 =归还时间
		//计算公式 1000*60*60*24  这是一天的毫秒
		// 1分钟=60秒 1秒 = 1000毫秒  1000*60*60*24:一天的毫秒数
		long dueDateTimeMillis = timeMillis + (selectDay * 1000 * 60 * 60 * 24);
		Date dueDate = new Date(dueDateTimeMillis);

		//添加实体
		User user = new User();
		user.setUserName(userName);
		user.setPhone(phone);
		//新增到数据库
		Connection con = null;
		try {
			con = dbUtil.getCon();

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
			//校验图书是否存在 根据编号查图书表
			Book book = bookDao.getBookByBookId(con, bookId);
			if (book == null) {
				JOptionPane.showMessageDialog(null, "该图书编号输入有误");
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
			}
			BorrowBook borrowBook = new BorrowBook();
			borrowBook.setUserName(userName);
			borrowBook.setBookPhone(phone);
			//限制借阅次数
			int size = borrowBookDao.checkBookBorrowSize(con, borrowBook);
			//如果数量大于3 则不让借书
			if (size > 3) {
				JOptionPane.showMessageDialog(null, userName + "借阅图书过多,请先归还图书");
				return;
			}
			//保存
			borrowBook.setBorrowDate(borrowDate);
			borrowBook.setBookName(book.getBookName());
			borrowBook.setDueDate(dueDate);
			borrowBook.setBookStatus("0");
			borrowBook.setBorrowStatus("0");
			borrowBook.setPenalty(0);
			borrowBook.setStatus("0");
			borrowBook.setRemark(this.remarkTest.getText());
			int result = borrowBookDao.addBorrowBook(con, borrowBook);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "图书借阅成功！");
				resetValue();//添加成功后要立即将框中内容重置
			} else {
				JOptionPane.showMessageDialog(null, "图书借阅失败！");
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
		this.remarkTest.setText("");
		this.borrowBookId.setText("");
	}
}
