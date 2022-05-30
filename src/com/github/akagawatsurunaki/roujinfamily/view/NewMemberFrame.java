package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.UserManagementController;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class NewMemberFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField realNameTxtFld;
	private JLabel lblNewLabel_1;
	private JTextField birthdayTxtFld;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField telNumTxtFld;
	private JButton btnNewButton_1;
	private JRadioButton maleRdBtn;
	private JLabel lblNewLabel_4;
	private JComboBox<String> comboBox;
		
	public JTextField getRealNameTxtFld() {
		return realNameTxtFld;
	}

	public JTextField getBirthdayTxtFld() {
		return birthdayTxtFld;
	}

	public JTextField getTelNumTxtFld() {
		return telNumTxtFld;
	}

	public JRadioButton getMaleRdBtn() {
		return maleRdBtn;
	}
	
	public JComboBox<String> getComboBox(){
		return comboBox;
	}
	

	public NewMemberFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setBounds(48, 62, 90, 30);
		contentPane.add(lblNewLabel);
		
		realNameTxtFld = new JTextField();
		realNameTxtFld.setBounds(164, 63, 181, 30);
		contentPane.add(realNameTxtFld);
		realNameTxtFld.setColumns(10);
		
		lblNewLabel_1 = new JLabel("出生日期");
		lblNewLabel_1.setBounds(48, 147, 90, 30);
		contentPane.add(lblNewLabel_1);
		
		birthdayTxtFld = new JTextField();
		birthdayTxtFld.setBounds(164, 152, 181, 30);
		contentPane.add(birthdayTxtFld);
		birthdayTxtFld.setColumns(10);
		
		maleRdBtn = new JRadioButton("男");
		maleRdBtn.setBounds(164, 419, 82, 23);
		contentPane.add(maleRdBtn);
		
		JRadioButton femalRdBtn = new JRadioButton("女");
		femalRdBtn.setBounds(268, 419, 73, 23);
		contentPane.add(femalRdBtn);
		
		ButtonGroup btnGrp = new ButtonGroup(); 
		btnGrp.add(maleRdBtn);
		btnGrp.add(femalRdBtn);
		
		lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setBounds(48, 423, 58, 15);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("电话号码");
		lblNewLabel_3.setBounds(48, 236, 90, 30);
		contentPane.add(lblNewLabel_3);
		
		telNumTxtFld = new JTextField();
		telNumTxtFld.setBounds(164, 241, 181, 30);
		contentPane.add(telNumTxtFld);
		telNumTxtFld.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().rqsAddMemberToHouseKeeper();
				UserManagementController.getInstance().getUserManagementFrame().setEnabled(true);
				NewMemberFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(48, 482, 97, 38);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().getUserManagementFrame().setEnabled(true);
				NewMemberFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(248, 482, 97, 38);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_4 = new JLabel("房管");
		lblNewLabel_4.setBounds(48, 340, 58, 15);
		contentPane.add(lblNewLabel_4);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(165, 336, 175, 23);
		contentPane.add(comboBox);
	}
}
