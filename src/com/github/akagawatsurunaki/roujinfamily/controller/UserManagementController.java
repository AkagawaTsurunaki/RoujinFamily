package com.github.akagawatsurunaki.roujinfamily.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.NewUserFrame;
import com.github.akagawatsurunaki.roujinfamily.view.UserManagementFrame;

public class UserManagementController {

	// #region Properties
	private static UserManagementController instance = new UserManagementController();
	private UserManagementService service = UserManagementServiceImpl.getInstance();
	private UserManagementFrame managementMainFrame;
	private NewUserFrame newUserFrame;
	
	private UserManagementController() {
	};
	
	public static void main(String[] args) {
		UserManagementController.getInstance().showMainFrame();
	}
	
	public static UserManagementController getInstance() {
		if (instance == null) {
			instance = new UserManagementController();
		}
		return instance;
	}
	// #endregion

	// #region Request Service Methods

	// Request service to load all users into the memory.
	private void rqsLoadAllUsers() {
		try {
			service.loadAllUsers();
		} catch (UserInfoDataReadingException e) {
			showErrorMessageBox("用户文件读取失败。\n文件不存在或不可读。", "读取文件时遇到问题");
		}
	}
	// Request service to add a new user into the current user data list in the memory.
	public void rqsAddUser() {
		try {
			service.addUser(packUserDataSeg());
		} catch (UserInfoInvalidException e) {
			showErrorMessageBox(e.getErrorMessage(), "非法输入");
		} catch (UserInfoDataWritingException e) {
			showErrorMessageBox("用户文件读取失败。\n文件不存在或不可读。", "读取文件时遇到问题");
		}
		// After a request, must fresh the table content.
		updateUserTableContent();
	}
	// Request service to remove the selected user from the current user data list in the memory.
	// This method allow system user to multiselect users that will be removed. 
	public void rqsRemoveUser() {
		JTable table = managementMainFrame.getTable();
		int[] slcRowsIndex = table.getSelectedRows();
		for (int i : slcRowsIndex) {
			int id = Integer.parseInt(table.getValueAt(i, 0).toString());
	
			try {
				service.removeUser(id);
			} catch (UserInfoDataWritingException e) {
				showErrorMessageBox("用户文件写入失败。\n文件不存在或不可写入。", "写入文件时遇到问题");
			} catch (UserNotFoundException e) {
				showErrorMessageBox("删除指定用户失败。\n用户不存在。", "删除用户时遇到问题");
			}
		}
		// After a request, must fresh the table content.
		updateUserTableContent();
	}
	// Request service to find a user with the same id, if not, it will throw UserNotFoundException.
	public User rqsFindUserById(int id) throws UserNotFoundException {
		return service.findUserById(id);
	}
	
	// #endregion
	
	// #region Show Frame Methods
	
	// Show an error message box on current window with error message and title. 
	// It will disable substratum windows. 
	private void showErrorMessageBox(String msg, String title) {
		JOptionPane.showMessageDialog(managementMainFrame, msg, title, JOptionPane.OK_OPTION);
	}
	// Show the main frame.
	// When a new main frame is showed, it will refresh table content automatically.
	public void showMainFrame() {
		this.managementMainFrame = new UserManagementFrame();
		managementMainFrame.setVisible(true);
		managementMainFrame.setEnabled(true);
		updateUserTableContent();
	}
	// Show a New User frame
	// Must close this window to refresh the table content
	// It will disable substratum windows. 
	public void showNewUserFrame() {
		managementMainFrame.setEnabled(false);
		newUserFrame = new NewUserFrame();
		newUserFrame.setVisible(true);
	}
	// Show Edit User frame (New User Frame alike)
	// The data content in edit text field will be filled automatically.
	// Must close this window to refresh the table content
	// It will disable substratum windows. 
	public void showEditUserFrame() {
		
		JTable table = managementMainFrame.getTable();
		int slcRowIndex = table.getSelectedRow();
		if(slcRowIndex == -1) {
			return;
		}
		managementMainFrame.setEnabled(false);
		
		showNewUserFrame();
		
		int id = Integer.parseInt(table.getValueAt(slcRowIndex, 0).toString());
		User user;
		
		try {
			user = rqsFindUserById(id);
			newUserFrame.setUserId(user.getId());
			newUserFrame.getUserNameTxtFld().setText(user.getUserName());
			newUserFrame.getPasswordTxtFld().setText(user.getPassword());
			newUserFrame.getRealNameTxtFld().setText(user.getRealName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			String birthday = formatter.format(user.getBirthday());
			newUserFrame.getBirthdayTxtFld().setText(birthday);
			newUserFrame.getTelNumTxtFld().setText(user.getTelNumber());
			if(user.getGender() == Gender.FEMALE) {
				newUserFrame.getFemaleRdBt().setSelected(true);
			}
			else {
				newUserFrame.getFemaleRdBt().setSelected(false);
			}
			newUserFrame.getRoleCbBox().setSelectedItem(user.getRole());
			
		} catch (UserNotFoundException e) {
			showErrorMessageBox("修改指定用户失败。\n用户不存在。", "修改用户时遇到问题");
		}
		
	}
	
	// #endregion
	
	// #region Util Methods
	
	// Package the user information into an object instance.
	// It will thorw UserInfoInvalidException when encounter certain not-matched data.
	private User packUserDataSeg() throws UserInfoInvalidException {
		int id = newUserFrame.getUserId();
		String userName = newUserFrame.getUserNameTxtFld().getText();
		String password = newUserFrame.getPasswordTxtFld().getText();
		String realName = newUserFrame.getRealNameTxtFld().getText();
		Gender gender = newUserFrame.getFemaleRdBt().isSelected() ? Gender.FEMALE : Gender.MALE;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		LocalDate birthday = LocalDate.parse(newUserFrame.getBirthdayTxtFld().getText(), formatter);
		String telNumber = newUserFrame.getTelNumTxtFld().getText();
		Role role = (Role) newUserFrame.getRoleCbBox().getSelectedItem();
		User user = new User(id, userName, password, realName, gender, birthday, telNumber, role);
		return user;
	}
	// Refresh the table content about the information of the users.
	private void updateUserTableContent() {
		try {
			rqsLoadAllUsers();
			List<User> usersList = service.getUsersTable().getData();
			String[] tableTitle = { "身份标识", "账户", "姓名", "性别", "出生日期", "电话", "权限" };
			String[][] tableContent = new String[usersList.size()][tableTitle.length];
			int i = 0;
			for (User u : usersList) {
				tableContent[i] = Arrays.copyOf(u.toStringArray(), tableTitle.length);
				i++;
			}
			TableModel tableModel = new DefaultTableModel(tableContent, tableTitle) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			managementMainFrame.updateUserTableContent(tableModel);
		} catch (UserInfoDataReadingException e) {
			showErrorMessageBox("用户文件读取失败。\n文件不存在或不可读。", "读取文件时遇到问题");
		}
	}
	// #endregion

}
