package com.java.view;

import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
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
public class BookType_Manager_InterFrm extends JInternalFrame {
	private JTable bookTypeTable;
	private JTextArea bookTypeDesc_Txt;

	private DbUtil dbUtil=new DbUtil();
	private BookTypeDao bookTypeDao=new BookTypeDao();
	private BookDao bookDao=new BookDao();
	private JTextField s_bookTypeName_Txt;
	private JTextField id_Txt;
	private JTextField bookTypeName_Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookType_Manager_InterFrm frame = new BookType_Manager_InterFrm();
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
	public BookType_Manager_InterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书类别管理");
		setBounds(100, 100, 570, 506);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("图书类别名称：");

		s_bookTypeName_Txt = new JTextField();
		s_bookTypeName_Txt.setColumns(10);

		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(BookType_Manager_InterFrm.class.getResource("/images/search.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(78)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(s_bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addGap(36)
										.addComponent(button))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
						.addGap(39))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(43)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(s_bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(8))
				);

		JLabel label_1 = new JLabel("编号:");

		id_Txt = new JTextField();
		id_Txt.setEditable(false);
		id_Txt.setColumns(10);

		JLabel label_2 = new JLabel("图书类别名称:");

		bookTypeName_Txt = new JTextField();
		bookTypeName_Txt.setColumns(10);

		JLabel label_3 = new JLabel("描述:");

		bookTypeDesc_Txt = new JTextArea();

		// 图书类别修改功能
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionEvent(e);
			}
		});
		button_1.setIcon(new ImageIcon(BookType_Manager_InterFrm.class.getResource("/images/modify.png")));

		//图书类别删除功能
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);
			}
		});
		button_2.setIcon(new ImageIcon(BookType_Manager_InterFrm.class.getResource("/images/delete.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(label_1)
														.addGap(18)
														.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(35)
														.addComponent(label_2)
														.addGap(18)
														.addComponent(bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(label_3)
														.addGap(18)
														.addComponent(bookTypeDesc_Txt, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))))
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(116)
										.addComponent(button_1)
										.addGap(74)
										.addComponent(button_2)))
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2)
								.addComponent(bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(bookTypeDesc_Txt, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1)
								.addComponent(button_2))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);

		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"编号", "图书类别名称", "图书类别描述"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTypeTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		bookTypeTable.getColumnModel().getColumn(2).setPreferredWidth(122);
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new BookType());

		//设置JTextArea边框的代码
		bookTypeDesc_Txt.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

	}

	/**
	 * 图书类别删除功能
	 * @param e
	 */
	private void bookTypeDeleteActionEvent(ActionEvent evt) {
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
				boolean flag=bookDao.exitBookByBookTypeId(con, id);
				if(flag){
					JOptionPane.showMessageDialog(null, "当前图书类别下有图书，不能删除此类别!");
					return;
				}
				int deleteNum=bookTypeDao.delete_Book(con, id);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功!");
					this.resetValue();
					this.fillTable(new BookType());
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
	 * 图书类别修改功能
	 * @param e
	 */
	private void bookTypeUpdateActionEvent(ActionEvent evt) {
		//获取界面信息
		String id=id_Txt.getText();
		String bookTypeName=bookTypeName_Txt.getText();
		String bookTypeDesc=bookTypeDesc_Txt.getText();
		//判断id是否为空，若为空，则不能修改
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
		if(StringUtil.isEmpty(bookTypeName)){
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空!");
			return;
		}
		//获取到信息，进行实例化
		BookType bookType=new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int modifyNum=bookTypeDao.update_Book(con, bookType);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功!");
				//重置表单
				this.resetValue();
				//填充表单
				this.fillTable(new BookType());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败!");
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void bookTypeTableMousePressed(MouseEvent evt) {
		//获取选中的行，返回行号
		int row=bookTypeTable.getSelectedRow();
		//获取第row行，第1列的值
		id_Txt.setText((String)bookTypeTable.getValueAt(row, 0));
		//获取第row行，第2列的值
		bookTypeName_Txt.setText((String)bookTypeTable.getValueAt(row, 1));
		//获取第row行，第3列的值
		bookTypeDesc_Txt.setText((String)bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * 图书类别搜索事件处理
	 * @param evt
	 */
	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s_bookTypeName=this.s_bookTypeName_Txt.getText();
		BookType bookType=new BookType();
		bookType.setBookTypeName(s_bookTypeName);
		this.fillTable(bookType);
	}

	/**
	 * 初始化表格
	 * @param bookType
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillTable(BookType bookType){
		DefaultTableModel dtm=(DefaultTableModel) bookTypeTable.getModel();
		//清空表格
		dtm.setRowCount(0);
		//连接数据库
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=bookTypeDao.list(con, bookType);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
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
	
	//重置表单
	private void resetValue(){
		this.id_Txt.setText("");
		this.bookTypeName_Txt.setText("");
		this.bookTypeDesc_Txt.setText("");
	}
}
