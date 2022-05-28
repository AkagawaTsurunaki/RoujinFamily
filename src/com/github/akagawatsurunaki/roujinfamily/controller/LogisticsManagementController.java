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
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Constants;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.RouteDirection;
import com.github.akagawatsurunaki.roujinfamily.model.RouteType;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.LogisticsManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.util.GlobalFormatter;
import com.github.akagawatsurunaki.roujinfamily.view.CheckInFrame;
import com.github.akagawatsurunaki.roujinfamily.view.EditTerminateTimeFrame;
import com.github.akagawatsurunaki.roujinfamily.view.LogisticsManagementFrame;
import com.github.akagawatsurunaki.roujinfamily.view.NewRegularBusFrame;

public class LogisticsManagementController extends Controller {

	private static LogisticsManagementController instance = new LogisticsManagementController();
	private LogisticsManagementService service = LogisticsManagementServiceImpl.getInstance();
	private NewRegularBusFrame newRegularBusFrame;
	private EditTerminateTimeFrame editTerminateTimeFrame;
	private CheckInFrame checkInFrame;
	public static LogisticsManagementController getInstance() {
		if (instance == null) {
			instance = new LogisticsManagementController();
		}
		return instance;
	}

	private LogisticsManagementFrame mainFrame;

	public void rqsAddRegularBus() {
		// Get data from frame.
		String routeCode = newRegularBusFrame.getRouteCodeTxtFld().getText();
		String routeName = newRegularBusFrame.getRouteNameTxtFld().getText();
		DayOfWeek operateDate = DayOfWeek.values()[1 + newRegularBusFrame.getWeekCbBox().getSelectedIndex()];
		RouteDirection routeDirection = newRegularBusFrame.getUpRdVtn().isSelected() ? 
										RouteDirection.UP : RouteDirection.DOWN;
		RouteType routeType = newRegularBusFrame.getInlandRdBtn().isSelected() ?
							  RouteType.INNER_ISLAND : RouteType.OUTER_ISLAND;
		LocalTime departureTime = LocalTime.parse(newRegularBusFrame.getDptTimeTxtFld().getText());
		LocalTime terminateTime = Constants.DEFAULT_TERMINNATE_TIME;
		String notes = newRegularBusFrame.getNotesTxtArea().getText();
		// Try to pack the data.
		try {
			RegularBus bus = new RegularBus(
					Constants.DEFAULT_OBJECT_ID, 
					routeCode,
					routeName,
					routeType,
					routeDirection,
					operateDate,
					departureTime,
					terminateTime,
					notes
					);
			
			// Try to add new bus.
			service.addRegularBus(bus);
			
			// Refresh the table beneath.
			updateRegularBusTable();
			
		} catch (CanNotMatchException e) {
			showErrorMessageBox(e);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		}
		
	}

	public void rqsRemoveRegularBus() {
		int[] rows = mainFrame.getTable().getSelectedRows();
		
		for(int i : rows) {
			
			int id = Integer.parseInt(mainFrame.getTable().getValueAt(i, 0).toString());
			
			try {
				service.removeRegularBus(id);
			} catch (FileWritingException e) {
				showErrorMessageBox(e);
			}
		}
		
		updateRegularBusTable();
		
	}
	
	
	public void rqsEditTerminateTime() {
		
		// TODO:GET -> SAVE -> REFRESH
		String time = editTerminateTimeFrame.getTextField().getText();
		int row = mainFrame.getTable().getSelectedRow();
		if(row < -1) {
			return;
		}
		int id = Integer.parseInt(mainFrame.getTable().getValueAt(row, 0).toString());
		try {
			service.editTerminateTime(id, time);
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e);
		} catch (CanNotMatchException e) {
			showErrorMessageBox(e);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		}
		updateRegularBusTable();
		
	}
	
	public LogisticsManagementFrame getMainFrame() {
		return this.mainFrame;
	}

	public void showNewRegularBusFrame() {
		newRegularBusFrame = new NewRegularBusFrame();
		newRegularBusFrame.setVisible(true);
		mainFrame.setEnabled(false);
		updateNewBusFrame();
	}
	
	public void showEditTerminateTimeFrame() {
		editTerminateTimeFrame = new EditTerminateTimeFrame();
		editTerminateTimeFrame.setVisible(true);
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

	public void showChenkInFrame() {
		checkInFrame = new CheckInFrame();
		mainFrame.setEnabled(false);
		checkInFrame.setVisible(true);
		updateCheckInFrameLeft();
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
	
	private void updateNewBusFrame() {
		
		newRegularBusFrame.getWeekCbBox().removeAllItems();
		
		for(DayOfWeek day : DayOfWeek.values()) {
			newRegularBusFrame.getWeekCbBox().addItem(day.toString());
		}

	}
	
	public void updateCheckInFrameLeft() {
		
		List<RegularBus> busList = service.getRegularBusTable().getData();
		checkInFrame.getBusCbBox().removeAllItems();
		for(RegularBus bus : busList) {
			checkInFrame.getBusCbBox().addItem(String.valueOf((bus.getId())));
		}
		
	}
	
	public void updateCheckInFrameRight() {
		try {
			int busId = Integer.parseInt((checkInFrame.getBusCbBox().getSelectedItem()).toString());
			List<Member> memberList = service.getPassengerListInRegularBus(busId);
			checkInFrame.getPassengerCbBox().removeAllItems();
			
			for(Member member : memberList) {
				checkInFrame.getPassengerCbBox().addItem(member.getRealName());
			}
		} catch (ObjectNotFoundException e) {
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
