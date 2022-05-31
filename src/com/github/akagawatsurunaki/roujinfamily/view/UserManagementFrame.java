package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import com.github.akagawatsurunaki.roujinfamily.controller.UserManagementController;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserManagementFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table = new JTable();
	JScrollBar scrollBar  = new JScrollBar();
	JScrollPane scrollPane = new JScrollPane(table);
	
	public JTable getTable(){
		return table;
	}

	/**
	 * Create the frame.
	 */
	public UserManagementFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().showNewUserFrame();
			}
		});
		btnNewButton.setBounds(51, 67, 103, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserManagementController.getInstance().rqsRemoveUser();
			}
		});
		btnNewButton_1.setBounds(164, 67, 103, 38);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("查看服务");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					UserManagementController.getInstance().showHouseKeeperEditFrame(id);
				}
			}
		});
		btnNewButton_2.setBounds(277, 67, 103, 38);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("设置服务");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().showMemberEditFrame();
			}
		});
		btnNewButton_3.setBounds(390, 67, 103, 38);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("修改信息");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManagementController.getInstance().showEditUserFrame();
			}
		});
		btnNewButton_5.setBounds(51, 363, 103, 40);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("退出");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_6.setBounds(500, 362, 103, 42);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_4 = new JButton("添加老人");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserManagementController.getInstance().showNewMemberFrame();
			}
		});
		btnNewButton_4.setBounds(503, 67, 97, 38);
		contentPane.add(btnNewButton_4);
		
		
	}
	
	public void updateUserTableContent(TableModel tableModel) {
		
		scrollBar.setBounds(586, 137, 17, 212);
		contentPane.add(scrollBar);
		table.setVisible(true);
		table.setBounds(51, 137, 537, 212);
		contentPane.add(table);
		table.getTableHeader().setVisible(true);
		table.setModel(tableModel);
		table.setEnabled(true);
		scrollPane.setBounds(51, 133, 553, 215);
		contentPane.add(scrollPane);

	}
}
