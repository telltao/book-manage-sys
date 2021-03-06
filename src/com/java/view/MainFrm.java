package com.java.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.java.view.about.AboutFrame;
import com.java.view.book.AddBookFrame;
import com.java.view.book.BookManageFrame;
import com.java.view.booktype.AddBookTypeFrame;
import com.java.view.booktype.BookTypeManagerFrame;
import com.java.view.borrow.AddBorrowBookFrame;
import com.java.view.borrow.BorrowBookManageFrame;
import com.java.view.user.AddUserFrame;
import com.java.view.user.UserManageFrame;

@SuppressWarnings("serial")
public class MainFrm extends JFrame {

	private JPanel contentPane;

	private JDesktopPane table = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("图书管理系统主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 704);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("图书类别管理");
		menu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/bookTypemanager1.png")));
		menuBar.add(menu);

		JMenuItem menuItem_1 = new JMenuItem("图书类别添加");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookTypeFrame bookType_add_InterFrm=new AddBookTypeFrame();
				bookType_add_InterFrm.setVisible(true);
				table.add(bookType_add_InterFrm);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/adduser2.png")));
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("图书类别操作");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BookTypeManagerFrame bookType_Manager_InterFrm=new BookTypeManagerFrame();
				bookType_Manager_InterFrm.setVisible(true);//??????
				table.add(bookType_Manager_InterFrm);//??BookType_Manager_InterFrm????????????
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/caozuo.png")));
		menu.add(menuItem_2);

		JMenu menu_1 = new JMenu("图书管理");
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/图书.jpg")));
		menuBar.add(menu_1);

		JMenuItem menuItem_3 = new JMenuItem("图书添加");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AddBookFrame book_Add_InterFrm=new AddBookFrame();
				book_Add_InterFrm.setVisible(true);//??????
				table.add(book_Add_InterFrm);//??Book_Add_InterFrm????????????
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add5.png")));
		menu_1.add(menuItem_3);


		JMenuItem menuItem_4 = new JMenuItem("图书操作");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BookManageFrame book_Manage_InterFrm = new BookManageFrame();
				book_Manage_InterFrm.setVisible(true);
				table.add(book_Manage_InterFrm);
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menu_1.add(menuItem_4);

		JMenu menu2 = new JMenu("用户管理");
		menu2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/user11.png")));
		menuBar.add(menu2);

		JMenuItem menuItem_5 = new JMenuItem("添加用户");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserFrame addUserFrame = new AddUserFrame();
				addUserFrame.setVisible(true);
				table.add(addUserFrame);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		menu2.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("用户列表");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserManageFrame userManageFrame = new UserManageFrame();
				userManageFrame.setVisible(true);
				table.add(userManageFrame);

			}
		});
		menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/操作2.png")));
		menu2.add(menuItem_6);

		JMenu menu4 = new JMenu("借阅管理");
		menu4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/借阅.png")));
		menuBar.add(menu4);

		JMenuItem menuItem_7 = new JMenuItem("借阅图书");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBorrowBookFrame addUserFrame = new AddBorrowBookFrame();
				addUserFrame.setVisible(true);
				table.add(addUserFrame);
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/借阅图书.png")));
		menu4.add(menuItem_7);

		JMenuItem menuItem_8 = new JMenuItem("借阅列表");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BorrowBookManageFrame borrowBookManageFrame = new BorrowBookManageFrame();
				borrowBookManageFrame.setVisible(true);
				table.add(borrowBookManageFrame);

			}
		});
		menuItem_8.setIcon(new ImageIcon(MainFrm.class.getResource("/images/caozuo3.png")));
		menu4.add(menuItem_8);


		//JMenuItem
		JMenuItem menuItem = new JMenuItem("安全退出");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "确定要退出当前系统吗?");
				if (result == 0) {
					dispose();
				}
			}
		});


		//JMenuItem
		JMenuItem mntmNewMenuItem = new JMenuItem("关于我们");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				AboutFrame about = new AboutFrame();
				about.setVisible(true);//
				table.add(about);//
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/关于我们.png")));
		menuBar.add(mntmNewMenuItem);
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/退出.png")));
		menuBar.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		table = new JDesktopPane();
		//在此处增加背景图片 参考: https://blog.csdn.net/zhengruli/article/details/78465498
		getContentPane().add(table, BorderLayout.CENTER);
		//创建一个标签组件，用于放置背景图片
		final JLabel backgroundLabel = new JLabel();
		//需要自己导入一张背景图片到src目录下
		URL resource = this.getClass().getResource("/images/background1.JPG");
		 final ImageIcon icon = new ImageIcon(resource);
		//压缩背景图片，使其适应窗口大小
		// 此处注释掉是因为压缩影响性能导致页面卡顿,需要则放开或注释
		//icon.setImage(icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
		backgroundLabel.setIcon(icon);
		backgroundLabel.setBounds(0, 0, 876, 750);
		table.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		//当改变窗口大小时，自动调整背景图片大小
		getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = e.getComponent().getWidth();
				int heigth = e.getComponent().getHeight();
				// 此处注释掉是因为压缩影响性能导致页面卡顿,需要则放开或注释
				//icon.setImage(icon.getImage().getScaledInstance(width, heigth, Image.SCALE_AREA_AVERAGING));
				backgroundLabel.setBounds(0, 0, width, heigth);
			}
		});
		contentPane.add(table);
	}
}
