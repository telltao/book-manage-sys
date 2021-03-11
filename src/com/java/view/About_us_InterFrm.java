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
		txtrJavaccjavajavaJava.setText("      Java是一门面向对象编程语言，不仅吸收了C++语言\r\n的各种优点，还摒弃了C++里难以理解的多继承、指针等\r\n概念，因此Java语言具有功能强大和简单易用两个特征\r\n      Java语言作为静态面向对象编程语言的代表，极好\r\n地实现了面向对象理论，允许程序员以优雅的思维方式进\r\n行复杂的编程 。\r\n      Java具有简单性、面向对象、分布式、健壮性、安\r\n全性、平台独立与可移植性、多线程、动态性等特点 。\r\nJava可以编写桌面应用程序、Web应用程序、分布式系\r\n统和嵌入式系统应用程序等 。");
		txtrJavaccjavajavaJava.setFont(new Font("楷体", Font.PLAIN, 20));
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
