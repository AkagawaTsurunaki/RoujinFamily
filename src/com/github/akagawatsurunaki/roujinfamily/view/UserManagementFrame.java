package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class UserManagementFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagementFrame frame = new UserManagementFrame();
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
	public UserManagementFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.setBounds(164, 67, 103, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ɾ��");
		btnNewButton_1.setBounds(51, 67, 103, 38);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("�鿴����...");
		btnNewButton_2.setBounds(277, 67, 103, 38);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("���÷���");
		btnNewButton_3.setBounds(390, 67, 103, 38);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("�������");
		btnNewButton_4.setBounds(500, 67, 103, 38);
		contentPane.add(btnNewButton_4);
		
		table = new JTable();
		table.setBounds(51, 137, 537, 212);
		contentPane.add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(586, 137, 17, 212);
		contentPane.add(scrollBar);
		
		JButton btnNewButton_5 = new JButton("����Ȩ��");
		btnNewButton_5.setBounds(51, 363, 103, 40);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("�˳�");
		btnNewButton_6.setBounds(500, 362, 103, 42);
		contentPane.add(btnNewButton_6);
	}
}
