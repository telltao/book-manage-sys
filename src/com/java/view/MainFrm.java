package com.java.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainFrm extends JFrame {

	private JPanel contentPane;
	
	private JDesktopPane table=null;

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
		setBounds(100, 100, 962, 796);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("图书类别管理");
		menu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/bookTypeManager.png")));
		menuBar.add(menu);
		
		//?????????
		JMenuItem menuItem_1 = new JMenuItem("图书类别添加");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??BookType_Add_InterFrm??????????
				BookType_Add_InterFrm bookType_add_InterFrm=new BookType_Add_InterFrm();
				bookType_add_InterFrm.setVisible(true);//??????
				table.add(bookType_add_InterFrm);//??BookType_Add_InterFrm????????????
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		menu.add(menuItem_1);
		
		//?????????
		JMenuItem menuItem_2 = new JMenuItem("图书类别维护");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??BookType_Manager_InterFrm??????????
				BookType_Manager_InterFrm bookType_Manager_InterFrm=new BookType_Manager_InterFrm();
				bookType_Manager_InterFrm.setVisible(true);//??????
				table.add(bookType_Manager_InterFrm);//??BookType_Manager_InterFrm????????????
			}
		});
		menuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menu.add(menuItem_2);
		
		JMenu menu_1 = new JMenu("图书管理");
		menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/bookManager.png")));
		menuBar.add(menu_1);
		
		//?????????
		JMenuItem menuItem_3 = new JMenuItem("图书添加");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??Book_Add_InterFrm??????????
				Book_Add_InterFrm book_Add_InterFrm=new Book_Add_InterFrm();
				book_Add_InterFrm.setVisible(true);//??????
				table.add(book_Add_InterFrm);//??Book_Add_InterFrm????????????
			}
		});
		menuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/add.png")));
		menu_1.add(menuItem_3);
		
		//??????????
		JMenuItem menuItem_4 = new JMenuItem("图书维护");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??Book_Manage_InterFrm??????????
				Book_Manage_InterFrm book_Manage_InterFrm=new Book_Manage_InterFrm();
				book_Manage_InterFrm.setVisible(true);//??????
				table.add(book_Manage_InterFrm);//??Book_Manage_InterFrm????????????
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/edit.png")));
		menu_1.add(menuItem_4);
		
		//?????? JMenuItem
		JMenuItem menuItem = new JMenuItem("安全退出");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "弹出的是个啥");
				if(result==0){
					dispose();
				}
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/exit.png")));
		menuBar.add(menuItem);
		
		//???????? JMenuItem
		JMenuItem mntmNewMenuItem = new JMenuItem("关于我们");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//??About_us_InterFrm??????????????????
				About_us_InterFrm about=new About_us_InterFrm();
				about.setVisible(true);//??????
				table.add(about);//??about???????????????????
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/about.png")));
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table=new JDesktopPane();
		table.setBackground(Color.CYAN);
		contentPane.add(table, BorderLayout.CENTER);
		
		//????JFrame???????
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//????JFrame??????????
		this.setLocationRelativeTo(null);
	}

}
