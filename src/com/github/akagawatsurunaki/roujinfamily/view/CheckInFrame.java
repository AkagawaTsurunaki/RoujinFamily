package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.LogisticsManagementController;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckInFrame extends JFrame {

	public JComboBox<String> getBusCbBox() {
		return busCbBox;
	}



	private static final long serialVersionUID = 1L;

	public JComboBox<String> getPassengerCbBox() {
		return passengerCbBox;
	}

	

	JComboBox<String> busCbBox;
	JComboBox<String> passengerCbBox;


	private JPanel contentPane;
	private JButton btnNewButton_1;

	public CheckInFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		busCbBox = new JComboBox<String>();
		busCbBox.setBounds(62, 126, 118, 23);
		contentPane.add(busCbBox);
		
		busCbBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(CheckInFrame.this.getBusCbBox().getSelectedItem() != null) {
					LogisticsManagementController.getInstance().updateCheckInFrameRight();
				}
			}
		});
		
		passengerCbBox = new JComboBox<String>();
		passengerCbBox.setBounds(292, 126, 118, 23);
		contentPane.add(passengerCbBox);
		
		JLabel lblNewLabel = new JLabel("班车");
		lblNewLabel.setBounds(62, 82, 58, 15);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("预约者");
		lblNewLabel_1.setBounds(292, 82, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				LogisticsManagementController.getInstance().updateRegularBusTable();
				CheckInFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(187, 357, 97, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("取消该登记");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().rqsRemoveMemberFromBus();
			}
		});
		btnNewButton_1.setBounds(187, 265, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}
