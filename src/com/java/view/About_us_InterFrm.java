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
		txtrJavaccjavajavaJava.setText("      Java\u662F\u4E00\u95E8\u9762\u5411\u5BF9\u8C61\u7F16\u7A0B\u8BED\u8A00\uFF0C\u4E0D\u4EC5\u5438\u6536\u4E86C++\u8BED\u8A00\r\n\u7684\u5404\u79CD\u4F18\u70B9\uFF0C\u8FD8\u6452\u5F03\u4E86C++\u91CC\u96BE\u4EE5\u7406\u89E3\u7684\u591A\u7EE7\u627F\u3001\u6307\u9488\u7B49\r\n\u6982\u5FF5\uFF0C\u56E0\u6B64Java\u8BED\u8A00\u5177\u6709\u529F\u80FD\u5F3A\u5927\u548C\u7B80\u5355\u6613\u7528\u4E24\u4E2A\u7279\u5F81\r\n      Java\u8BED\u8A00\u4F5C\u4E3A\u9759\u6001\u9762\u5411\u5BF9\u8C61\u7F16\u7A0B\u8BED\u8A00\u7684\u4EE3\u8868\uFF0C\u6781\u597D\r\n\u5730\u5B9E\u73B0\u4E86\u9762\u5411\u5BF9\u8C61\u7406\u8BBA\uFF0C\u5141\u8BB8\u7A0B\u5E8F\u5458\u4EE5\u4F18\u96C5\u7684\u601D\u7EF4\u65B9\u5F0F\u8FDB\r\n\u884C\u590D\u6742\u7684\u7F16\u7A0B \u3002\r\n      Java\u5177\u6709\u7B80\u5355\u6027\u3001\u9762\u5411\u5BF9\u8C61\u3001\u5206\u5E03\u5F0F\u3001\u5065\u58EE\u6027\u3001\u5B89\r\n\u5168\u6027\u3001\u5E73\u53F0\u72EC\u7ACB\u4E0E\u53EF\u79FB\u690D\u6027\u3001\u591A\u7EBF\u7A0B\u3001\u52A8\u6001\u6027\u7B49\u7279\u70B9 \u3002\r\nJava\u53EF\u4EE5\u7F16\u5199\u684C\u9762\u5E94\u7528\u7A0B\u5E8F\u3001Web\u5E94\u7528\u7A0B\u5E8F\u3001\u5206\u5E03\u5F0F\u7CFB\r\n\u7EDF\u548C\u5D4C\u5165\u5F0F\u7CFB\u7EDF\u5E94\u7528\u7A0B\u5E8F\u7B49 \u3002");
		txtrJavaccjavajavaJava.setFont(new Font("¿¬Ìå", Font.PLAIN, 20));
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
