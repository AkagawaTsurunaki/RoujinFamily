package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.LogisticsManagementController;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class EditTerminateTimeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public JTextField getTextField() {
		return textField;
	}

	private JPanel contentPane;
	private JTextField textField;

	public EditTerminateTimeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().rqsEditTerminateTime();
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				EditTerminateTimeFrame.this.dispose();
				
			}
		});
		btnNewButton.setBounds(46, 111, 97, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				EditTerminateTimeFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(281, 111, 97, 34);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(46, 68, 332, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("修改班车");
		lblNewLabel.setBounds(46, 31, 58, 15);
		contentPane.add(lblNewLabel);
	}
}
