package com.java.view.book;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
import com.java.model.Book;
import com.java.model.BookType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

@SuppressWarnings("serial")
public class AddBookFrame extends JInternalFrame {
	private JTextField bookName_Txt;
	private JTextField author_Txt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField price_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox bookType_Jcb;
	private JTextArea bookDesc_Txt;
	private JRadioButton man_Jrb;
	private JRadioButton woman_Jrb;

	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookFrame frame = new AddBookFrame();
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
	public AddBookFrame() {
		setClosable(true);
		setIconifiable(true);
		setTitle("图书添加");
		setBounds(100, 100, 519, 543);
//		this.setBounds((900 - 519) / 2,(900 - 543) / 2,519,543);


		JLabel label = new JLabel("图书名称：");

		bookName_Txt = new JTextField();
		bookName_Txt.setColumns(10);

		JLabel label_1 = new JLabel("图书作者：");

		author_Txt = new JTextField();
		author_Txt.setColumns(10);

		JLabel label_2 = new JLabel("作者性别：");

		man_Jrb = new JRadioButton("男");
		buttonGroup.add(man_Jrb);
		man_Jrb.setSelected(true);

		woman_Jrb = new JRadioButton("女");
		buttonGroup.add(woman_Jrb);

		JLabel label_3 = new JLabel("图书价格：");

		price_Txt = new JTextField();
		price_Txt.setColumns(10);

		JLabel label_4 = new JLabel("图书类别：");

		bookType_Jcb = new JComboBox();

		JLabel label_5 = new JLabel("图书描述：");

		bookDesc_Txt = new JTextArea();

		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//?????????????
				bookAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(AddBookFrame.class.getResource("/images/add.png")));

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重置事件
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(AddBookFrame.class.getResource("/images/reset.png")));
		
		/*JButton btnNewButton = new JButton("关闭");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//在此处添加关闭事件
				e.getModifiers();
			}
		});
		btnNewButton.setIcon(new ImageIcon(AddBookFrame.class.getResource("/images/delete.png")));
		*/
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookType_Jcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_2)
										.addComponent(label))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(man_Jrb)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(woman_Jrb)))))
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addGap(18)
									.addComponent(author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_3)
									.addGap(18)
									.addComponent(price_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
													.addComponent(button)
													.addGap(18)
//									.addComponent(btnNewButton)
//									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(button_1)
													.addGap(67))
											.addComponent(bookDesc_Txt, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))
						.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(man_Jrb)
						.addComponent(woman_Jrb)
						.addComponent(label_3)
						.addComponent(price_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4)
								.addComponent(bookType_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5)
								.addComponent(bookDesc_Txt, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button)
								.addComponent(button_1))
//						.addComponent(btnNewButton)
						.addGap(47))
		);
		getContentPane().setLayout(groupLayout);

		//????JTextArea???????
		bookDesc_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

		//调用
		fillBookType();
	}


	//重置事件处理
	private void resetValueActionPerformed(ActionEvent e) {
		//调用resetValue
		this.resetValue();
	}

	/**
	 * 图书添加事件处理
	 * @param evt
	 */
	private void bookAddActionPerformed(ActionEvent evt) {
		//添加的时候获取字段
		String bookName=this.bookName_Txt.getText();
		String author=this.author_Txt.getText();
		String price=this.price_Txt.getText();
		String bookDesc=this.bookDesc_Txt.getText();

		//进行判断
		if(StringUtil.isEmpty(bookName)){
			JOptionPane.showMessageDialog(null, "图书名为空");
			return;
		}
		if(StringUtil.isEmpty(author)){
			JOptionPane.showMessageDialog(null, "作者为空");
			return;
		}
		if(StringUtil.isEmpty(price)){
			JOptionPane.showMessageDialog(null, "价格为空");
			return;
		}

		//获取性别数据
		String sex = "";
		//方法isSelected
		if (man_Jrb.isSelected()) {
			sex = "男";
		} else if (woman_Jrb.isSelected()) {
			sex = "女";
		}

		//?????????id
		BookType bookType = (BookType) bookType_Jcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		String bookTypeName = bookType.getBookTypeName();

		// Float.parseFloat(price)将String类型强转型成Float
		Book book = new Book(bookName, author, sex, Float.parseFloat(price), bookTypeId, bookTypeName, bookDesc);

		//数据库连接
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int addNum = bookDao.addBook(con, book);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "图书添加成功");
				resetValue();
			} else {
				JOptionPane.showMessageDialog(null, "图书添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
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
	private void resetValue(){
		this.bookName_Txt.setText("");
		this.author_Txt.setText("");
		this.price_Txt.setText("");
		//重置时默认男的选中
		this.man_Jrb.setSelected(true);
		this.bookDesc_Txt.setText("");
		//判断如果图书类型为0，会报错，但是此种情况不会出现
		if(this.bookType_Jcb.getItemCount()>0){
			//设置选中的项为第一项
			this.bookType_Jcb.setSelectedIndex(0);
		}
	}

	/**
	 * 初始化图书类别下拉框
	 */
	@SuppressWarnings("unchecked")
	private void fillBookType(){
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, new BookType());
			while(rs.next()){
				BookType bookType=new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				//bookType_Jcb的方法叫添加项“addItem”
				//this.bookType_Jcb.addItem(rs.getString("bookTypeName"));
				this.bookType_Jcb.addItem(bookType);

			}
//			bookType_Jcb.setModel(new DefaultComboBoxModel(new String[]{"请选择...", "3天", "5天", "10天"}));

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
