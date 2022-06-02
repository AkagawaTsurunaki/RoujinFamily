package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.HouseKeeperManagementController;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class MemberInfoEditFrame extends JFrame {

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
	private JRadioButton femalRdBtn;
	
	
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
	
	public JRadioButton getFemaleRdBtn() {
		return femalRdBtn;
	}

	public MemberInfoEditFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 505);
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
		maleRdBtn.setBounds(164, 319, 82, 23);
		contentPane.add(maleRdBtn);
		
		femalRdBtn = new JRadioButton("女");
		femalRdBtn.setBounds(272, 319, 73, 23);
		contentPane.add(femalRdBtn);
		
		ButtonGroup btnGrp = new ButtonGroup(); 
		btnGrp.add(maleRdBtn);
		btnGrp.add(femalRdBtn);
		
		lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setBounds(48, 323, 58, 15);
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
				HouseKeeperManagementController.getInstance().rqsAddMember();
				HouseKeeperManagementController.getInstance().getMainFrame().setEnabled(true);
				MemberInfoEditFrame.this.dispose();
				
			}
		});
		btnNewButton.setBounds(48, 398, 97, 38);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HouseKeeperManagementController.getInstance().getMainFrame().setEnabled(true);
				MemberInfoEditFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(248, 398, 97, 38);
		contentPane.add(btnNewButton_1);
	}
}
