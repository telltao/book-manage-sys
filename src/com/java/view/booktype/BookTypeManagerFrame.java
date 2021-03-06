package com.java.view.booktype;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.java.dao.BookDao;
import com.java.dao.BookTypeDao;
import com.java.model.BookType;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
import com.java.view.book.AddBookFrame;
import javax.swing.border.EtchedBorder;
import java.awt.Color;


@SuppressWarnings("serial")
public class BookTypeManagerFrame extends JInternalFrame {
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
					BookTypeManagerFrame frame = new BookTypeManagerFrame();
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
	public BookTypeManagerFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书类别管理");
		setBounds(100, 100, 698, 539);
//		this.setBounds((900 - 698) / 2,(900 - 539) / 2,698,539);
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
		button.setIcon(new ImageIcon(BookTypeManagerFrame.class.getResource("/images/查询-default.png")));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u56FE\u4E66\u7C7B\u522B\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton button_3 = new JButton("重置");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue();
			}
		});
		button_3.setIcon(new ImageIcon(BookTypeManagerFrame.class.getResource("/images/重置.png")));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(s_bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(button)
							.addGap(26)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
							.addGap(49))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(s_bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(button_3))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
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
		button_1.setIcon(new ImageIcon(BookTypeManagerFrame.class.getResource("/images/4修改.png")));

		//图书类别删除功能
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);//create之后直接跳转到图书类别删除功能
			}
		});
		button_2.setIcon(new ImageIcon(BookTypeManagerFrame.class.getResource("/images/删 除 .png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1)
									.addGap(18)
									.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_3)
									.addGap(18)
									.addComponent(bookTypeDesc_Txt, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(button_1)
							.addGap(74)
							.addComponent(button_2)
							.addGap(166))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(id_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookTypeName_Txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(bookTypeDesc_Txt, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(button_2))
					.addContainerGap(25, Short.MAX_VALUE))
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
		//判断id是否为空，若为空，则不能删除,即确认用户要选中数据,否则无法删除
		if(StringUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录!");
			return;
		}
		//以防用户误点,用以提示
		int n=JOptionPane.showConfirmDialog(null, "确定要删除该记录吗?");
		//n=0表示用户已经选中
		if(n==0){
			Connection con=null;
			try {
				con=dbUtil.getCon();
				boolean flag=bookDao.exitBookByBookTypeId(con, id);
				//存在
				if(flag){
					JOptionPane.showMessageDialog(null, "当前图书类别下有图书，不能删除此类别!");
					return;
				}
				int deleteNum=bookTypeDao.delete_Book(con, id);
				if(deleteNum==1) {
					JOptionPane.showMessageDialog(null, "删除成功!");
					this.resetUpdateValue();
					this.fillTable(new BookType());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				//出现异常弹出提示"删除失败"
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
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录!");
			return;
		}
		if (StringUtil.isEmpty(bookTypeName)) {
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空!");
			return;
		}
		//获取到信息，进行实例化
		BookType bookType = new BookType(Integer.parseInt(id), bookTypeName, bookTypeDesc, null);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int modifyNum = bookTypeDao.update_Book(con, bookType);
			if (modifyNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功!");
				//重置表单,调用下方写好的程序
				this.resetUpdateValue();
				//填充表单,重新new一个实例
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
		//获取第row行，第1列的值设置到里面,转换为String
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
		//文本框重命名为”图书类别名称”
		bookType.setBookTypeName(s_bookTypeName);
		//调用已经封装好的
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
			//遍历
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

	//修改成功后会重置表单
	private void resetUpdateValue() {
		this.id_Txt.setText("");
		this.bookTypeName_Txt.setText("");
		this.bookTypeDesc_Txt.setText("");
	}

	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.s_bookTypeName_Txt.setText("");
		//重置时，重新查询
		this.fillTable(new BookType());
	}
}
