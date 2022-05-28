package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.LoginController;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends Frame {

	public JTextField getAccountTxtFld() {
		return accountTxtFld;
	}

	public JPasswordField getPasswordTxtFld() {
		return passwordTxtFld;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField accountTxtFld;
	private JPasswordField passwordTxtFld;
	
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		accountTxtFld = new JTextField();
		accountTxtFld.setBounds(174, 77, 228, 21);
		contentPane.add(accountTxtFld);
		accountTxtFld.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ÕË»§");
		lblNewLabel.setBounds(58, 71, 126, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ÃÜÂë");
		lblNewLabel_1.setBounds(58, 138, 126, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("ÍË³ö");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(276, 222, 126, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("µÇÂ½");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController.getInstance().rqsLogin();
			}
		});
		btnNewButton_1.setBounds(58, 222, 126, 39);
		contentPane.add(btnNewButton_1);
		
		passwordTxtFld = new JPasswordField();
		passwordTxtFld.setBounds(174, 144, 228, 21);
		contentPane.add(passwordTxtFld);
	}

}
