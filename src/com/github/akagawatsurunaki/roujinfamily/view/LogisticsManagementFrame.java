package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.controller.LogisticsManagementController;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class LogisticsManagementFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	public JTable getTable() {
		return table;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogisticsManagementFrame frame = new LogisticsManagementFrame();
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
	public LogisticsManagementFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 114, 754, 271);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("新建班车");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().showNewRegularBusFrame();
			}
		});
		btnNewButton.setBounds(10, 65, 120, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除班车");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(140, 65, 120, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("修改班车");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(270, 65, 120, 39);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("查看乘客");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(400, 65, 120, 39);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("登记");
		btnNewButton_4.setBounds(530, 65, 120, 39);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("退出");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_5.setBounds(660, 65, 120, 39);
		contentPane.add(btnNewButton_5);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(763, 114, 17, 271);
		contentPane.add(scrollBar);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 113, 754, 272);
		contentPane.add(scrollPane);
	}
}
