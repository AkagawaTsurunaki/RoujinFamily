package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.controller.HouseKeeperManagementController;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HouseKeeperManagementFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	public JTable getTable() {
		return table;
	}

	public HouseKeeperManagementFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 56, 478, 309);
		contentPane.add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(488, 56, 17, 309);
		contentPane.add(scrollBar);
		
		JButton btnNewButton = new JButton("修改个人信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HouseKeeperManagementController.getInstance().showMemberInfoEditFrame();
			}
		});
		btnNewButton.setBounds(10, 10, 115, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(390, 10, 115, 36);
		contentPane.add(btnNewButton_1);
	}
}
