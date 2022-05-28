package com.github.akagawatsurunaki.roujinfamily.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.akagawatsurunaki.roujinfamily.controller.LogisticsManagementController;
import com.github.akagawatsurunaki.roujinfamily.controller.UserManagementController;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

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
		passengerCbBox.setBounds(291, 126, 118, 23);
		contentPane.add(passengerCbBox);
		
		JLabel lblNewLabel = new JLabel("°à³µ");
		lblNewLabel.setBounds(62, 82, 58, 15);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Ô¤Ô¼Õß");
		lblNewLabel_1.setBounds(291, 82, 58, 15);
		contentPane.add(lblNewLabel_1);
	}


}
