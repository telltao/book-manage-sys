package com.java.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

@SuppressWarnings("serial")
public class About_us_InterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About_us_InterFrm frame = new About_us_InterFrm();
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
	public About_us_InterFrm() {
		getContentPane().setBackground(Color.PINK);
		setBackground(Color.CYAN);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u5173\u4E8E\u6211\u4EEC");
		setBounds(100, 100, 700, 450);
		
		JTextArea txtrJavaccjavajavaJava = new JTextArea();
		txtrJavaccjavajavaJava.setBackground(Color.PINK);
		txtrJavaccjavajavaJava.setForeground(Color.RED);
		txtrJavaccjavajavaJava.setText("      Java��һ��������������ԣ�����������C++����\n" +
				"�ĸ����ŵ㣬��������C++���������Ķ�̳С�ָ���\n" +
				"������Java���Ծ��й���ǿ��ͼ�������������\t\n" +
				"Java������Ϊ��̬������������ԵĴ�������\n" +
				"��ʵ��������������ۣ��������Ա�����ŵ�˼ά��ʽ��\n" +
				"�и��ӵı�� ��\r\n" +
				"Java���м��ԡ�������󡢷ֲ�ʽ����׳�ԡ���\r\n" +
				"ȫ�ԡ�ƽ̨���������ֲ�ԡ����̡߳���̬�Ե��ص� ��\r\n" +
				"Java���Ա�д����Ӧ�ó���WebӦ�ó��򡢷ֲ�ʽϵ\n" +
				"ͳ��Ƕ��ʽϵͳӦ�ó���� ��");
		txtrJavaccjavajavaJava.setFont(new Font("����", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(105)
					.addComponent(txtrJavaccjavajavaJava, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addComponent(txtrJavaccjavajavaJava, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
