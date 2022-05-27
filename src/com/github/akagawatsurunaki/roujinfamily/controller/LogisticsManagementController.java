package com.github.akagawatsurunaki.roujinfamily.controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.LogisticsManagementFrame;

public class LogisticsManagementController extends Controller {
	
	private static LogisticsManagementController instance = new LogisticsManagementController(); 
	private LogisticsManagementService service = LogisticsManagementServiceImpl.getInstance();
	public static LogisticsManagementController getInstance() {
		if(instance == null) {
			instance = new LogisticsManagementController();
		}
		return instance;
	}
	
	private LogisticsManagementFrame mainFrame;
	
	
	
	public void loginInvoke() {
		
//		try {
//			service.loadAllRegularBuses();
//		} catch (FileReadingException e) {
//			showErrorMessageBox(e);
//		}
//		showMainFrame();
	}
	
	public void showMainFrame() {
		mainFrame = new LogisticsManagementFrame();
		mainFrame.setVisible(true);
		updateRegularBusTable();
	}
	
	private void updateRegularBusTable() {
		
		String[] tableTitle = service.getRegularBusTableTitle();
		String[][] tableContent = service.getRegularBusTableAsStringArray();
		
		TableModel tableModel = new DefaultTableModel(tableContent, tableTitle) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		mainFrame.getTable().setModel(tableModel);
	}
	
	// #region Override Methods from Super Methods

	protected void showErrorMessageBox(RouJinFamilyException e) {
		String msg = "错误信息：" + e.getErrorMessage() + "\n发起者：" + e.getPositionInfo();
		String title = e.getTitle();
		JOptionPane.showMessageDialog(mainFrame, msg, title, JOptionPane.OK_OPTION);
	}
	
	protected void showErrorMessageBox(String msg, String title, String pos) {
		String message = "错误信息：" + msg + "\n发起者：" + pos;
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.OK_OPTION);
	}
	
	// #endregion

}
