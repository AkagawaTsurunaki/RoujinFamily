package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.Controller.LoginController;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Constants;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(174, 77, 228, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("�˻�");
		lblNewLabel.setBounds(58, 71, 126, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setBounds(58, 138, 126, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ϵͳ׼���˳�");
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(276, 222, 126, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ȷ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nm = textField.getText();
				//nm.substring(0, Constants.MAX_USERNAME_LENGYH);
				String psw = new String(passwordField.getPassword());
				//psw.substring(0, Constants.MAX_PASSWORD_LENGTH);
				
				try {
					if(LoginController.getInstance().rqslogin(nm, psw)) {
						System.out.println("��½�ɹ�");
					}
					else {
						loginFailed("�������", "��½ʧ��");
					}
				} catch (UserInfoDataReadingException e1) {
					loginFailed("���ܶ�ȡ�û��ļ���", "��½ʧ��");
				} catch (UserNotFoundException e2) {
					loginFailed("�Ҳ������û���", "��½ʧ��");
				}
			}
		});
		btnNewButton_1.setBounds(58, 222, 126, 39);
		contentPane.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 144, 228, 21);
		contentPane.add(passwordField);
	}
	void loginFailed(String msg, String title) {
		JOptionPane.showMessageDialog(this, msg, title, JOptionPane.OK_OPTION);
	}
	
}
