package com.java.view;

import com.java.dao.BookTypeDao;
import com.java.model.BookType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

@SuppressWarnings("serial")
public class BookType_Add_InterFrm extends JInternalFrame {

	//JTextField和JTextArea的实例化
	private JTextField bookTypeName_Txt;
	private JTextArea bookTypeDesc_Txt;

	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookType_Add_InterFrm frame = new BookType_Add_InterFrm();
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
	public BookType_Add_InterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("图书类别添加");
		setBounds(100, 100, 566, 393);

		JLabel label = new JLabel("图书类别名称：");

		bookTypeName_Txt = new JTextField();
		bookTypeName_Txt.setColumns(10);

		JLabel label_1 = new JLabel("图书类别描述：");

		bookTypeDesc_Txt = new JTextArea();

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加事件引用
				bookTypeAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookType_Add_InterFrm.class.getResource("/images/add.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重置事件引用
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookType_Add_InterFrm.class.getResource("/images/reset.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(74)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(label)
														.addComponent(label_1)))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addContainerGap(146, Short.MAX_VALUE)
												.addComponent(button)
												.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(bookTypeName_Txt, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
														.addComponent(bookTypeDesc_Txt))
												.addGap(102))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(143)
												.addComponent(button_1)
												.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(62)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(50)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(bookTypeDesc_Txt, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(button)
										.addComponent(button_1))
								.addGap(40))
		);
		getContentPane().setLayout(groupLayout);

		//设置JTextArea边框的代码
		bookTypeDesc_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}

	//添加事件处理
	private void bookTypeAddActionPerformed(ActionEvent evt) {

		//从界面获取用户填的图书类别名称和图书类别描述信息
		String bookTypeName=this.bookTypeName_Txt.getText();
		String bookTypeDesc=this.bookTypeDesc_Txt.getText();
		//判断图书类别名称是否为空
		if(StringUtil.isEmpty(bookTypeName)){
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空！");
			return;
		}

		//进行数据库连接
		BookType bookType=new BookType(bookTypeName,bookTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();//首先连接数据库
			int result=bookTypeDao.add_Book(con,bookType);//返回的是Int类型，表示执行了几条数据
			if(result==1){
				JOptionPane.showMessageDialog(null, "图书类别添加成功！");
				resetValue();//添加成功后要立即将框中内容重置
			}else{
				JOptionPane.showMessageDialog(null, "图书类别添加失败！");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书类别添加失败！");
		}finally{
			try {
				dbUtil.close(con);//最后要关闭数据库连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//重置事件处理
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}

	//重置表单方法
	private void resetValue(){
		this.bookTypeName_Txt.setText("");
		this.bookTypeDesc_Txt.setText("");
	}

}
