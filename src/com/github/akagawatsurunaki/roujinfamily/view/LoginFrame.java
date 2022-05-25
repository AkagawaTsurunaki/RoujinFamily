package com.github.akagawatsurunaki.roujinfamily.view;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.LoginController;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
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
		
		JLabel lblNewLabel = new JLabel("账户");
		lblNewLabel.setBounds(58, 71, 126, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(58, 138, 126, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(276, 222, 126, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				String password = new String(passwordField.getPassword());
				try {
					if(LoginController.getInstance().rqslogin(userName, password)) {
						System.out.println("登陆成功");
					}
					else {
						msgBox("密码错误。", "登陆失败");
					}
				} catch (UserNotFoundException e2) {
					msgBox("找不到该用户。", "登陆失败");
				} catch (UserInfoDataReadingException e1) {
					msgBox("用户文件读取失败。\n目标文件访问被拒或目标文件不存在或目标文件已损坏。", "登陆失败");
					System.exit(ERROR);
				}
			}
		});
		btnNewButton_1.setBounds(58, 222, 126, 39);
		contentPane.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 144, 228, 21);
		contentPane.add(passwordField);
	}

	public void msgBox(String msg, String title) {
		JOptionPane.showMessageDialog(this, msg, title, JOptionPane.OK_OPTION);
	}
}
