package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.LogisticsManagementController;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class BookFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public JTextField getTextField() {
		return textField;
	}

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public BookFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(191, 54, 185, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("乘客真实姓名");
		lblNewLabel.setBounds(41, 57, 96, 15);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().rqsBook();
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				BookFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(40, 116, 97, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				BookFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(279, 116, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}
