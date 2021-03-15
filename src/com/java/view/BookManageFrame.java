package com.java.view;

import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
import com.java.model.Book;
import com.java.model.BookType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
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
public class BookManageFrame extends JInternalFrame {
	private JTable bookTable;
	private JTextField s_bookName_Txt;
	private JTextField s_author_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox s_bookType_Jcb;
	private JRadioButton man_Jrb;
	private JRadioButton woman_Jrb;
	private JTextArea bookDesc_Txt;
	@SuppressWarnings("rawtypes")
	private JComboBox bookType_Jcb;

	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private JTextField id_Txt;
	private JTextField bookName_Txt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField price_Txt;
	private JTextField author_Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageFrame frame = new BookManageFrame();
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
	public BookManageFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setBounds(100, 100, 723, 660);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(37)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
								.addGap(28))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(31)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
								.addGap(27))
		);

		JLabel label_3 = new JLabel("\u7F16\u53F7:");

		id_Txt = new JTextField();
		id_Txt.setEditable(false);
		id_Txt.setColumns(10);

		JLabel label_4 = new JLabel("\u56FE\u4E66\u540D\u79F0:");

		bookName_Txt = new JTextField();
		bookName_Txt.setColumns(10);

		JLabel label_5 = new JLabel("\u4F5C\u8005\u6027\u522B:");

		man_Jrb = new JRadioButton("\u7537");
		buttonGroup.add(man_Jrb);
		man_Jrb.setSelected(true);

		woman_Jrb = new JRadioButton("\u5973");
		buttonGroup.add(woman_Jrb);

		JLabel label_6 = new JLabel("\u4EF7\u683C:");

		price_Txt = new JTextField();
		price_Txt.setColumns(10);

		JLabel label_7 = new JLabel("\u56FE\u4E66\u4F5C\u8005:");

		author_Txt = new JTextField();
		author_Txt.setColumns(10);

		JLabel label_8 = new JLabel("\u56FE\u4E66\u7C7B\u522B:");

		bookType_Jcb = new JComboBox();

		JLabel label_9 = new JLabel("\u56FE\u4E66\u63CF\u8FF0:");

		bookDesc_Txt = new JTextArea();

		//图书修改事件
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookUpdateActionPerformed(evt);
			}
		});
		button_1.setIcon(new ImageIcon(BookManageFrame.class.getResource("/images/modify.png")));

		//图书删除事件
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookDeleteActionPerformed(evt);
			}
		});
		button_2.setIcon(new ImageIcon(BookManageFrame.class.getResource("/images/delete.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(29)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(label_9)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(bookDesc_Txt, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_3)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_6)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(price_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGap(29)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_4)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_7)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGap(32)
												.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_5)
																.addGap(14)
																.addComponent(man_Jrb)
																.addGap(18)
																.addComponent(woman_Jrb))
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(label_8)
																.addGap(18)
																.addComponent(bookType_Jcb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
								.addContainerGap(53, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addContainerGap(244, Short.MAX_VALUE)
								.addComponent(button_1)
								.addGap(105)
								.addComponent(button_2)
								.addGap(155))
		);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(27)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)
										.addComponent(bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5)
										.addComponent(man_Jrb)
										.addComponent(woman_Jrb))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_6)
										.addComponent(price_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_7)
										.addComponent(author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_8)
										.addComponent(bookType_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(36)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_9)
										.addComponent(bookDesc_Txt, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(button_2)
										.addComponent(button_1))
								.addContainerGap(29, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0:");

		s_bookName_Txt = new JTextField();
		s_bookName_Txt.setColumns(10);

		JLabel label_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005:");

		s_author_Txt = new JTextField();
		s_author_Txt.setColumns(10);

		JLabel label_2 = new JLabel("\u56FE\u4E66\u7C7B\u522B:");

		s_bookType_Jcb = new JComboBox();

		//图书查询事件
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_bookName_Txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(label_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_author_Txt, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(label_2)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(s_bookType_Jcb, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(button)
										.addComponent(s_bookName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_1)
										.addComponent(s_author_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_2)
										.addComponent(s_bookType_Jcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		//bookTable的行点击事件
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				bookTableMousePressed(met);
			}
		});
		bookTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u4F5C\u8005\u6027\u522B", "\u56FE\u4E66\u4EF7\u683C", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u522B"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(97);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(93);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(92);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(101);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(91);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);

		//设置JTextArea边框的代码
		bookDesc_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

		//查询下拉框方法引用
		this.fillBookType("search");
		//修改下拉框方法引用
		this.fillBookType("modify");
		//表单的方法
		this.fillTable(new Book());
	}

	/**
	 * 图书删除事件方法
	 * @param evt
	 */
	private void bookDeleteActionPerformed(ActionEvent evt) {
		//获取界面信息
		String id=id_Txt.getText();
		//判断id是否为空，若为空，则不能删除
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录!");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗?");
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				int deleteNum=bookDao.deleteBook(con, id);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功!");
					this.resetValue();
					this.fillTable(new Book());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败!");
			}finally{
				try {
					dbUtil.close(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 图书修改事件方法
	 * @param evt
	 */
	private void bookUpdateActionPerformed(ActionEvent evt) {
		//获取界面信息
		String id=this.id_Txt.getText();
		String bookName=this.bookName_Txt.getText();
		String author=this.author_Txt.getText();
		String price=this.price_Txt.getText();
		String bookDesc=this.bookDesc_Txt.getText();

		//判断id是否为空，若为空，则不能修改
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
		//判断其他信息是否为空
		if(StringUtil.isEmpty(bookName)){
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}
		if(StringUtil.isEmpty(author)){
			JOptionPane.showMessageDialog(null, "作者名称不能为空！");
			return;
		}
		if(StringUtil.isEmpty(price)){
			JOptionPane.showMessageDialog(null, "价格不能为空！");
			return;
		}

		//获取性别信息
		String sex="";
		if(man_Jrb.isSelected()){
			sex="男";
		}else if(woman_Jrb.isSelected()){
			sex="女";
		}

		//获取图书类别id
		BookType bookType=(BookType) bookType_Jcb.getSelectedItem();
		int bookTypeId=bookType.getId();

		//进行封装
		Book book=new Book(Integer.parseInt(id), bookName, author, sex, Float.parseFloat(price), bookTypeId, bookDesc);

		//进行添加操作，数据库连接
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int addNum=bookDao.updateBook(con,book);
			if(addNum==1){
				JOptionPane.showMessageDialog(null, "图书修改成功！");
				//清空表单
				resetValue();
				//填充表单
				this.fillTable(new Book());
			}else{
				JOptionPane.showMessageDialog(null, "图书修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书修改失败！");
		} finally{
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
	private void resetValue(){
		this.id_Txt.setText("");
		this.bookName_Txt.setText("");
		this.author_Txt.setText("");
		this.price_Txt.setText("");
		this.man_Jrb.setSelected(true);
		this.bookDesc_Txt.setText("");
		if(this.bookType_Jcb.getItemCount()>0){
			//默认设置选中第一项
			this.bookType_Jcb.setSelectedIndex(0);
		}
	}

	//表格行点击事件方法
	private void bookTableMousePressed(MouseEvent met) {
		//获取选中的行，返回行号
		int row=this.bookTable.getSelectedRow();
		//获取第row行，第1列的值
		this.id_Txt.setText((String)bookTable.getValueAt(row, 0));
		//获取第row行，第2列的值
		this.bookName_Txt.setText((String)bookTable.getValueAt(row, 1));
		//获取第row行，第3列的值
		this.author_Txt.setText((String)bookTable.getValueAt(row, 2));
		//获取性别
		String sex=(String)bookTable.getValueAt(row, 3);
		//人工判断
		if("男".equals(sex)){
			this.man_Jrb.setSelected(true);
		}else if("女".equals(sex)){
			this.woman_Jrb.setSelected(true);
		}
		//获取第row行，第5列的值
		this.price_Txt.setText((Float)bookTable.getValueAt(row, 4)+"");
		//获取第row行，第6列的值
		this.bookDesc_Txt.setText((String)bookTable.getValueAt(row, 5));
		//获取第row行，第7列的值
		String bookTypeName=(String)this.bookTable.getValueAt(row, 6);
		//获取下拉框的行数
		int n=this.bookType_Jcb.getItemCount();
		//开始遍历下拉框的所有内容
		for(int i=0;i<n;i++){
			//把每个下拉框的内容拿出来与bookTypeName进行比较，若相同，则设置为选中
			BookType item=(BookType)this.bookType_Jcb.getItemAt(i);
			if(item.getBookTypeName().equals(bookTypeName)){
				this.bookType_Jcb.setSelectedIndex(i);
			}
		}
	}

	/**
	 * 图书查询功能
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {
		//从界面获取信息
		String bookName=this.s_bookName_Txt.getText();
		String author=this.s_author_Txt.getText();
		BookType bookType=(BookType) this.s_bookType_Jcb.getSelectedItem();
		int bookTypeId=bookType.getId();

		Book book=new Book(bookName,author,bookTypeId);
		this.fillTable(book);
	}

	/**
	 * 初始化下拉框
	 * @param type 下拉框类型
	 */
	@SuppressWarnings("unchecked")
	private void fillBookType(String type){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, new BookType());
			if("search".equals(type)){
				BookType bookType=new BookType();
				bookType.setBookTypeName("请选择...");
				bookType.setId(-1);
				this.s_bookType_Jcb.addItem(bookType);
			}
			while(rs.next()){
				BookType bookType=new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if("search".equals(type)){
					this.s_bookType_Jcb.addItem(bookType);
				}else if("modify".equals(type)){
					this.bookType_Jcb.addItem(bookType);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化表格数据
	 * @param book
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillTable(Book book){
		DefaultTableModel dtm=(DefaultTableModel) bookTable.getModel();
		//清空表格
		dtm.setRowCount(0);
		//连接数据库
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=bookDao.list(con, book);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("sex"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeName"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
