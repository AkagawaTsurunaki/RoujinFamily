package com.github.akagawatsurunaki.roujinfamily.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;
import com.github.akagawatsurunaki.roujinfamily.view.NewUserFrame;
import com.github.akagawatsurunaki.roujinfamily.view.UserManagementFrame;

public class UserManagementController {

	public static void main(String[] args) {
		UserManagementController.getInstance().showMainFrame();
	}

	private static UserManagementController instance = new UserManagementController();

	public static UserManagementController getInstance() {
		if (instance == null) {
			instance = new UserManagementController();
		}
		return instance;
	}

	private UserManagementController() {
	};

	private UserManagementService service = UserManagementServiceImpl.getInstance();
	private UserManagementFrame managementMainFrame;
	private NewUserFrame newUserFrame;

	public void rqsLoadAllUsers() {
		try {
			service.loadAllUsers();
		} catch (UserInfoDataReadingException e) {
			errMsgBox("用户文件读取失败。\n文件不存在或不可读。", "读取文件时遇到问题");
		}
	}

	private void showMainFrame() {
		this.managementMainFrame = new UserManagementFrame();
		managementMainFrame.setVisible(true);
		rqsLoadAllUsers();
		updateUserTableContent();
	}

	private void updateUserTableContent() {
		try {
			rqsLoadAllUsers();
			List<User> usersList = service.getUsersTable().getData();
			String[] tableTitle = {"身份标识", "账户", "姓名", "性别", "出生日期", "电话", "权限" };
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

			managementMainFrame.tableContent(tableModel);

		} catch (UserInfoDataReadingException e) {
			errMsgBox("用户文件读取失败。\n文件不存在或不可读。", "读取文件时遇到问题");
		}
	}

	private void errMsgBox(String msg, String title) {
		JOptionPane.showMessageDialog(managementMainFrame, msg, title, JOptionPane.OK_OPTION);
	}

	public void showNewUserFrame() {
		freezeMngmtMainFrame(false);
		newUserFrame = new NewUserFrame();
		newUserFrame.setVisible(true);
	}
	
	public void freezeMngmtMainFrame(boolean b) {
		managementMainFrame.setEnabled(b);
		
	}
	
	public void rqsAddUser() {
		freezeMngmtMainFrame(true);
		
		int id = -1;
		String userName = newUserFrame.getUserNameTxtFld().getText();
		String password = newUserFrame.getPasswordTxtFld().getText();
		String realName = newUserFrame.getRealNameTxtFld().getText();
		
		Gender gender =
				newUserFrame.getFemaleRdBt().isSelected()
				? Gender.FEMALE : Gender.MALE;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		LocalDate birthday = LocalDate.parse(newUserFrame.getBirthdayTxtFld().getText(), formatter);
		String telNumber = newUserFrame.getTelNumTxtFld().getText();
		
		Role role = (Role) newUserFrame.getRoleCbBox().getSelectedItem();
				
		try {
			User user = new User(id, userName, password, realName, gender, birthday, telNumber, role);
			if(service.addUser(user)) {
				updateUserTableContent();
			}
		} catch (UserInfoInvalidException e) {
			errMsgBox(e.getErrorMessage(), "非法输入");
		} catch (UserInfoDataWritingException e) {
			// TODO Auto-generated catch block
			errMsgBox("用户文件读取失败。\n文件不存在或不可读。", "读取文件时遇到问题");
		}
	}

	public void rqsRemoveUser() {
		
	}
}
