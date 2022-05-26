package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.controller.UserManagementController;
import com.github.akagawatsurunaki.roujinfamily.model.Member;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class MemberEditFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	


	public JComboBox<String> getRightMemberComboBox() {
		return rightMemberComboBox;
	}
	private JPanel contentPane;
	private JTable leftTable;
	JLabel houseKeeperLabel;
	private JComboBox<String> rightMemberComboBox;
	
	
	
	public JLabel getHouseKeeperLabel() {
		return houseKeeperLabel;
	}
	public JTable getLeftTable() {
		return this.leftTable;
	}
	public MemberEditFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		houseKeeperLabel = new JLabel("未命名用户");
		houseKeeperLabel.setBounds(34, 26, 99, 26);
		contentPane.add(houseKeeperLabel);
		
		leftTable = new JTable();
		leftTable.setBounds(34, 72, 374, 159);
		contentPane.add(leftTable);
		
		JButton btnNewButton = new JButton("添加顾客");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = MemberEditFrame.this.getRightMemberComboBox().getSelectedIndex();
				UserManagementController.getInstance().rqsEditMember(index);
			}
		});
		btnNewButton.setBounds(449, 103, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除顾客");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().rqsRemoveMemberFromHouseKeeper();
			}
		});
		btnNewButton_1.setBounds(448, 68, 107, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(408, 72, 17, 159);
		contentPane.add(scrollBar);
		
		JButton btnNewButton_3 = new JButton("返回");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().getUserManagementFrame().setEnabled(true);
				MemberEditFrame.this.dispose();
			}
		});
		btnNewButton_3.setBounds(449, 208, 106, 23);
		contentPane.add(btnNewButton_3);
		
		rightMemberComboBox = new JComboBox<String>();
		rightMemberComboBox.setBounds(449, 136, 106, 23);
		contentPane.add(rightMemberComboBox);
	}
}
