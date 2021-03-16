package com.java.view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

@SuppressWarnings("serial")
public class AboutFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutFrame frame = new AboutFrame();
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
	public AboutFrame() {
		getContentPane().setBackground(Color.PINK);
		setBackground(Color.CYAN);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u5173\u4E8E\u6211\u4EEC");
		setBounds(100, 100, 700, 450);

		JTextArea txtrJavaccjavajavaJava = new JTextArea();
		txtrJavaccjavajavaJava.setBackground(Color.PINK);
		txtrJavaccjavajavaJava.setForeground(Color.RED);
		txtrJavaccjavajavaJava.setText("关于我们");
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
