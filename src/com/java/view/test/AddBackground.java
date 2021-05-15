package com.java.view.test;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author telltao
 * @Date 2021/5/9 21:57
 */
public class AddBackground extends JFrame {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        AddBackground desktopPane = new AddBackground();
        desktopPane.setVisible(true);
    }

    public AddBackground() {
        super();
        setTitle("桌面面板添加背景图片示例");
        setBounds(100, 100, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //创建一个桌面面板
        final JDesktopPane table = new JDesktopPane();
        getContentPane().add(table, BorderLayout.CENTER);
        //创建一个标签组件，用于放置背景图片
        final JLabel backgroundLabel = new JLabel();
        //需要自己导入一张背景图片到src目录下
        URL resource = this.getClass().getResource("/images/mi.JPG");
        ImageIcon icon = new ImageIcon(resource);
        //压缩背景图片，使其适应窗口大小
        // 此处注释掉是因为压缩影响性能导致页面卡顿,需要则放开
        //icon.setImage(icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
        backgroundLabel.setIcon(icon);
        backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        table.add(backgroundLabel, new Integer(Integer.MIN_VALUE));
        //当改变窗口大小时，自动调整背景图片大小
        getContentPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int heigth = e.getComponent().getHeight();
                // 此处注释掉是因为压缩影响性能导致页面卡顿,需要则放开
                //icon.setImage(icon.getImage().getScaledInstance(width,heigth, Image.SCALE_AREA_AVERAGING));
                backgroundLabel.setBounds(0, 0, width, heigth);
            }
        });
    }
}
