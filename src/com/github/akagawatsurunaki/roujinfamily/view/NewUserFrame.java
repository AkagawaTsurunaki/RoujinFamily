package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.controller.UserManagementController;
import com.github.akagawatsurunaki.roujinfamily.model.Role;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUserFrame extends JFrame {

	public JTextField getUserNameTxtFld() {
		return userNameTxtFld;
	}

	public JTextField getPasswordTxtFld() {
		return passwordTxtFld;
	}

	public JTextField getRealNameTxtFld() {
		return realNameTxtFld;
	}

	public JTextField getBirthdayTxtFld() {
		return birthdayTxtFld;
	}

	public JTextField getTelNumTxtFld() {
		return telNumTxtFld;
	}

	public JRadioButton getFemaleRdBt() {
		return femaleRdBtn;
	}

	public JComboBox<Role> getRoleCbBox() {
		return roleCbBox;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField userNameTxtFld;
	private JLabel lblNewLabel_1;
	private JTextField passwordTxtFld;
	private JLabel lblNewLabel_2;
	private JTextField realNameTxtFld;
	private JLabel lblNewLabel_3;
	private JTextField birthdayTxtFld;
	private JLabel lblNewLabel_4;
	private JTextField telNumTxtFld;
	private JLabel lblNewLabel_5;
	private JRadioButton maleRdBt;
	private JRadioButton femaleRdBtn;
	private JLabel lblNewLabel_6;
	private JComboBox<Role> roleCbBox;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private int userId = -1;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int id) {
		userId = id;
	}
	
	

	public NewUserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(60, 51, 79, 30);
		contentPane.add(lblNewLabel);
		
		userNameTxtFld = new JTextField();
		userNameTxtFld.setBounds(178, 50, 225, 33);
		contentPane.add(userNameTxtFld);
		userNameTxtFld.setColumns(10);
		
		lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(60, 118, 79, 30);
		contentPane.add(lblNewLabel_1);
		
		passwordTxtFld = new JTextField();
		passwordTxtFld.setBounds(178, 123, 225, 33);
		contentPane.add(passwordTxtFld);
		passwordTxtFld.setColumns(10);
		
		lblNewLabel_2 = new JLabel("真实姓名");
		lblNewLabel_2.setBounds(60, 193, 79, 33);
		contentPane.add(lblNewLabel_2);
		
		realNameTxtFld = new JTextField();
		realNameTxtFld.setBounds(178, 199, 225, 30);
		contentPane.add(realNameTxtFld);
		realNameTxtFld.setColumns(10);
		
		lblNewLabel_3 = new JLabel("出生日期");
		lblNewLabel_3.setBounds(60, 268, 79, 30);
		contentPane.add(lblNewLabel_3);
		
		birthdayTxtFld = new JTextField();
		birthdayTxtFld.setBounds(178, 273, 225, 30);
		contentPane.add(birthdayTxtFld);
		birthdayTxtFld.setColumns(10);
		
		lblNewLabel_4 = new JLabel("电话号码");
		lblNewLabel_4.setBounds(60, 339, 79, 33);
		contentPane.add(lblNewLabel_4);
		
		telNumTxtFld = new JTextField();
		telNumTxtFld.setBounds(178, 342, 225, 30);
		contentPane.add(telNumTxtFld);
		telNumTxtFld.setColumns(10);
		
		lblNewLabel_5 = new JLabel("性别");
		lblNewLabel_5.setBounds(60, 416, 79, 30);
		contentPane.add(lblNewLabel_5);
		
		maleRdBt = new JRadioButton("男");
		maleRdBt.setBounds(178, 420, 63, 23);
		contentPane.add(maleRdBt);
		
		femaleRdBtn = new JRadioButton("女");
		femaleRdBtn.setBounds(276, 420, 127, 23);
		contentPane.add(femaleRdBtn);
		//
		ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(maleRdBt);
        btnGroup.add(femaleRdBtn);
        maleRdBt.setSelected(true);
		//
		lblNewLabel_6 = new JLabel("权限");
		lblNewLabel_6.setBounds(60, 495, 79, 30);
		contentPane.add(lblNewLabel_6);
		
		roleCbBox = new JComboBox<Role>();
		roleCbBox.setBounds(178, 499, 225, 23);
		contentPane.add(roleCbBox);
		
		btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(276, 570, 127, 40);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("确定");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController c = UserManagementController.getInstance();
				c.rqsAddUser();
				NewUserFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(60, 570, 127, 40);
		contentPane.add(btnNewButton_1);
		roleCbBox.addItem(Role.ADMINISTRATOR);
		roleCbBox.addItem(Role.HOUSE_KEEPER);
		roleCbBox.addItem(Role.LOGISTICS);
	}
}
