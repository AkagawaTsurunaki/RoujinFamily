package com.github.akagawatsurunaki.roujinfamily.controller;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.RouteDirection;
import com.github.akagawatsurunaki.roujinfamily.model.RouteType;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.util.GlobalFormatter;
import com.github.akagawatsurunaki.roujinfamily.view.LogisticsManagementFrame;
import com.github.akagawatsurunaki.roujinfamily.view.NewRegularBusFrame;

public class LogisticsManagementController extends Controller {

	private static LogisticsManagementController instance = new LogisticsManagementController();
	private LogisticsManagementService service = LogisticsManagementServiceImpl.getInstance();
	private NewRegularBusFrame newRegularBusFrame;

	public static LogisticsManagementController getInstance() {
		if (instance == null) {
			instance = new LogisticsManagementController();
		}
		return instance;
	}

	private LogisticsManagementFrame mainFrame;

	public void rqsAddRegularBus() {
		String routeCode = newRegularBusFrame.getRouteCodeTxtFld().getText();
		String routeName = newRegularBusFrame.getRouteNameTxtFld().getText();

		int index = newRegularBusFrame.getWeekCbBox().getSelectedIndex();

		DayOfWeek week = DayOfWeek.FRIDAY;

		switch (index) {
		case 1: {
			week = DayOfWeek.FRIDAY;
			break;
		}
		case 2: {
			week = DayOfWeek.TUESDAY;
			break;
		}
		case 3: {
			week = DayOfWeek.WEDNESDAY;
			break;
		}
		case 4: {
			week = DayOfWeek.THURSDAY;
			break;
		}
		case 5: {
			week = DayOfWeek.FRIDAY;
			break;
		}
		case 6: {
			week = DayOfWeek.SATURDAY;
			break;
		}
		case 7: {
			week = DayOfWeek.SUNDAY;
			break;
		}
		}
		
		RouteDirection routeDirection;
		
		if(newRegularBusFrame.getUpRdVtn().isSelected()) {
			routeDirection = RouteDirection.UP;
		}
		else {
			routeDirection = RouteDirection.DOWN;
		}
		
		RouteType routeType;
		
		if(newRegularBusFrame.getInlandRdBtn().isSelected()) {
			routeType = RouteType.INNER_ISLAND;
		}
		else {
			routeType = RouteType.OUTER_ISLAND;
		}
		
		LocalTime dptTime = LocalTime.parse(newRegularBusFrame.getDptTimeTxtFld().getText());
		
		LocalTime tmnTime = LocalTime.of(23, 59);
		
		String notes = newRegularBusFrame.getNotesTxtArea().getText();
		
		try {
			RegularBus bus = new RegularBus(
					-1, 
					routeCode,
					routeName,
					routeType,
					routeDirection,
					week,
					dptTime,
					tmnTime,
					notes,
					new ArrayList<Member>()
					);
			
			service.addRegularBus(bus);
		} catch (CanNotMatchException e) {
			showErrorMessageBox(e);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		}
				

	}

	public LogisticsManagementFrame getMainFrame() {
		return this.mainFrame;
	}

	public void showNewRegularBusFrame() {
		newRegularBusFrame = new NewRegularBusFrame();
		newRegularBusFrame.setVisible(true);
		mainFrame.setEnabled(false);
	}

	public void loginInvoke() {

		try {
			service.loadAllRegularBuses();
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
		}
		showMainFrame();
	}

	public void showMainFrame() {
		mainFrame = new LogisticsManagementFrame();
		mainFrame.setVisible(true);
		updateRegularBusTable();
	}

	private void updateRegularBusTable() {

		String[] tableTitle = service.getRegularBusTableTitle();
		String[][] tableContent;
		try {
			tableContent = service.getRegularBusTableAsStringArray();
			TableModel tableModel = new DefaultTableModel(tableContent, tableTitle) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			mainFrame.getTable().setModel(tableModel);
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
		}

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
