package com.github.akagawatsurunaki.roujinfamily.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.akagawatsurunaki.roujinfamily.controller.LogisticsManagementController;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class NewRegularBusFrame extends JFrame {

	public JTextArea getNotesTxtArea() {
		return notesTxtArea;
	}

	public JTextField getDptTimeTxtFld() {
		return dptTimeTxtFld;
	}

	private static final long serialVersionUID = 1L;

	public JTextField getRouteCodeTxtFld() {
		return routeCodeTxtFld;
	}

	public JTextField getRouteNameTxtFld() {
		return routeNameTxtFld;
	}

	public JComboBox<String> getWeekCbBox() {
		return weekCbBox;
	}

	public JRadioButton getInlandRdBtn() {
		return inlandRdBtn;
	}

	public JRadioButton getUpRdVtn() {
		return upRdVtn;
	}

	private JPanel contentPane;
	private JTextField routeCodeTxtFld;
	private JTextField routeNameTxtFld;
	private JComboBox<String> weekCbBox;
	private JRadioButton inlandRdBtn;
	private JRadioButton upRdVtn;
	private JTextField dptTimeTxtFld;
	private JTextArea notesTxtArea;
	
	public NewRegularBusFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("线路代码");
		lblNewLabel.setBounds(61, 83, 58, 15);
		contentPane.add(lblNewLabel);
		
		routeCodeTxtFld = new JTextField();
		routeCodeTxtFld.setBounds(142, 80, 136, 21);
		contentPane.add(routeCodeTxtFld);
		routeCodeTxtFld.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("线路名称");
		lblNewLabel_1.setBounds(329, 83, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		routeNameTxtFld = new JTextField();
		routeNameTxtFld.setBounds(429, 80, 136, 21);
		contentPane.add(routeNameTxtFld);
		routeNameTxtFld.setColumns(10);

		inlandRdBtn = new JRadioButton("岛内班车");
		inlandRdBtn.setBounds(142, 140, 127, 23);
		contentPane.add(inlandRdBtn);
		
		JRadioButton outlandRbBtn = new JRadioButton("岛外班车");
		outlandRbBtn.setBounds(142, 175, 127, 23);
		contentPane.add(outlandRbBtn);
		
		ButtonGroup landBg = new ButtonGroup();
		landBg.add(inlandRdBtn);
		landBg.add(outlandRbBtn);
		
		JLabel lblNewLabel_2 = new JLabel("线路类型");
		lblNewLabel_2.setBounds(61, 144, 58, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("线路方向");
		lblNewLabel_3.setBounds(329, 144, 58, 15);
		contentPane.add(lblNewLabel_3);
		
		upRdVtn = new JRadioButton("上行");
		upRdVtn.setBounds(429, 140, 127, 23);
		contentPane.add(upRdVtn);
		
		JRadioButton downRdVtn = new JRadioButton("下行");
		downRdVtn.setBounds(429, 175, 127, 23);
		contentPane.add(downRdVtn);
		
		ButtonGroup updownBg = new ButtonGroup();
		updownBg.add(upRdVtn);
		updownBg.add(downRdVtn);
		
		JLabel lblNewLabel_4 = new JLabel("运营日期");
		lblNewLabel_4.setBounds(61, 236, 58, 15);
		contentPane.add(lblNewLabel_4);

		weekCbBox = new JComboBox<String>();
		weekCbBox.setBounds(142, 232, 136, 23);
		contentPane.add(weekCbBox);
		
		JLabel lblNewLabel_6 = new JLabel("发车时间");
		lblNewLabel_6.setBounds(329, 236, 58, 15);
		contentPane.add(lblNewLabel_6);
		
		dptTimeTxtFld = new JTextField();
		dptTimeTxtFld.setBounds(427, 233, 138, 21);
		contentPane.add(dptTimeTxtFld);
		dptTimeTxtFld.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("备注");
		lblNewLabel_7.setBounds(61, 304, 58, 15);
		contentPane.add(lblNewLabel_7);
		
		notesTxtArea = new JTextArea();
		notesTxtArea.setBounds(142, 304, 423, 101);
		contentPane.add(notesTxtArea);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().rqsAddRegularBus();
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				NewRegularBusFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(61, 464, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsManagementController.getInstance().getMainFrame().setEnabled(true);
				NewRegularBusFrame.this.dispose();
			}
		});
		btnNewButton_1.setBounds(468, 464, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}
