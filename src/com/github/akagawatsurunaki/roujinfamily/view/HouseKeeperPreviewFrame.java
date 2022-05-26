package com.github.akagawatsurunaki.roujinfamily.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.controller.UserManagementController;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.User;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class HouseKeeperPreviewFrame extends JFrame {

	private JPanel contentPane;
	
	private JComboBox<String> memberListComboBox = new JComboBox<String>();
	private JComboBox<String> houseKeeperListComboBox = new JComboBox<String>();
	
	public JComboBox<String> getMemberListComboBox() {
		return memberListComboBox;
	}

	public JComboBox<String> getHouseKeeperListComboBox() {
		return houseKeeperListComboBox;
	}

	public HouseKeeperPreviewFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		memberListComboBox.setBounds(259, 71, 107, 23);
		contentPane.add(memberListComboBox);
		JLabel lblNewLabel = new JLabel("顾客");
		lblNewLabel.setBounds(259, 31, 107, 30);
		contentPane.add(lblNewLabel);
		
		
		houseKeeperListComboBox.setBounds(68, 71, 107, 23);
		contentPane.add(houseKeeperListComboBox);
		houseKeeperListComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String name = HouseKeeperPreviewFrame.this.houseKeeperListComboBox.getSelectedItem().toString();
				UserManagementController.getInstance().updateMemberListComboBox(name);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("生活管家");
		lblNewLabel_1.setBounds(68, 35, 107, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(68, 207, 97, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().getUserManagementFrame().setEnabled(true);
				HouseKeeperPreviewFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(269, 207, 97, 30);
		contentPane.add(btnNewButton_1);
	}
}
