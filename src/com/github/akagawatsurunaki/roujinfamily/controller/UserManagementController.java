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
			errMsgBox("�û��ļ���ȡʧ�ܡ�\n�ļ������ڻ򲻿ɶ���", "��ȡ�ļ�ʱ��������");
		}
	}

	public void showMainFrame() {
		this.managementMainFrame = new UserManagementFrame();
		managementMainFrame.setVisible(true);
		updateUserTableContent();
	}

	private void updateUserTableContent() {
		try {
			rqsLoadAllUsers();
			List<User> usersList = service.getUsersTable().getData();
			String[] tableTitle = { "��ݱ�ʶ", "�˻�", "����", "�Ա�", "��������", "�绰", "Ȩ��" };
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
			errMsgBox("�û��ļ���ȡʧ�ܡ�\n�ļ������ڻ򲻿ɶ���", "��ȡ�ļ�ʱ��������");
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

		try {
			service.addUser(rqsPackUser());
		} catch (UserInfoInvalidException e) {
			errMsgBox(e.getErrorMessage(), "�Ƿ�����");
		} catch (UserInfoDataWritingException e) {
			errMsgBox("�û��ļ���ȡʧ�ܡ�\n�ļ������ڻ򲻿ɶ���", "��ȡ�ļ�ʱ��������");
		}

		updateUserTableContent();
	}

	private User rqsPackUser() throws UserInfoInvalidException {
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

	public void rqsRemoveUser() {
		JTable table = managementMainFrame.getTable();
		int[] slcRowsIndex = table.getSelectedRows();
		for (int i : slcRowsIndex) {
			int id = Integer.parseInt(table.getValueAt(i, 0).toString());
	
			try {
				service.removeUser(id);
			} catch (UserInfoDataWritingException e) {
				errMsgBox("�û��ļ�д��ʧ�ܡ�\n�ļ������ڻ򲻿�д�롣", "д���ļ�ʱ��������");
			} catch (UserNotFoundException e) {
				errMsgBox("ɾ��ָ���û�ʧ�ܡ�\n�û������ڡ�", "ɾ���û�ʱ��������");
			}
		}
		updateUserTableContent();
	}
	
	public User rqsFindUserById(int id) throws UserNotFoundException {
		return service.findUserById(id);
	}
	// Show Edit User Frame (New User Frame)
	public void showEditUserFrame() {
		
		JTable table = managementMainFrame.getTable();
		int slcRowIndex = table.getSelectedRow();
		if(slcRowIndex == -1) {
			return;
		}
		freezeMngmtMainFrame(false);
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
			errMsgBox("�޸�ָ���û�ʧ�ܡ�\n�û������ڡ�", "�޸��û�ʱ��������");
		}
		
	}
}
