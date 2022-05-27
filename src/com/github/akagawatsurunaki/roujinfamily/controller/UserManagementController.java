package com.github.akagawatsurunaki.roujinfamily.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.HouseKeeperPreviewFrame;
import com.github.akagawatsurunaki.roujinfamily.view.MemberEditFrame;
import com.github.akagawatsurunaki.roujinfamily.view.NewMemberFrame;
import com.github.akagawatsurunaki.roujinfamily.view.NewUserFrame;
import com.github.akagawatsurunaki.roujinfamily.view.UserManagementFrame;

public class UserManagementController extends Controller {

	// #region Properties
	private static UserManagementController instance = new UserManagementController();
	private UserManagementService service = UserManagementServiceImpl.getInstance();
	private UserManagementFrame mainFrame;
	private NewUserFrame newUserFrame;
	private HouseKeeperPreviewFrame houseKeeperPreviewFrame;
	private MemberEditFrame memberEditFrame;
	private NewMemberFrame newMemberFrame;
	
	private User selectedUser;
	// Must through this method to get selected house keeper.
	private User getSelectedUser() throws ObjectNotFoundException {
		
		int selectedRow = mainFrame.getTable().getSelectedRow();
		
		if(selectedRow == -1) {
			this.selectedUser = null;
			return selectedUser;
		}
		
		int selectedHouseKeeperId = 
				Integer.parseInt(mainFrame.getTable().getValueAt(selectedRow, 0).toString());
		
		this.selectedUser = rqsFindUserById(selectedHouseKeeperId);
		
		return selectedUser;

	}
	
	private UserManagementController() {};
	
	public void loginInvoke() {
		UserManagementController.getInstance().showMainFrame();
	}
	
	public static UserManagementController getInstance() {
		if (instance == null) {
			instance = new UserManagementController();
		}
		return instance;
	}
	
	public UserManagementFrame getUserManagementFrame() {
		return this.mainFrame;
	}
	// #endregion

	// #region Request Service Methods

	// Request service to load all users into the memory.
	private void rqsLoadAllUsers() {
		try {
			service.loadAllUsers();
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
		}
	}
	//
	private void rqsLoadAllMembers() {
		try {
			service.loadAllMembers();
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
		}
	}
	// Request service to add a new user into the current user data list in the memory.
	public void rqsAddUser() {
		try {
			service.addUser(packUserDataSeg());
		} catch (CanNotMatchException e) {
			showErrorMessageBox(e);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		}
		// After a request, must fresh the table content.
		updateUserTableContent();
	}
	// Request service to remove the selected user from the current user data list in the memory.
	// This method allow system user to multiselect users that will be removed. 
	public void rqsRemoveUser() {
		JTable table = mainFrame.getTable();
		int[] slcRowsIndex = table.getSelectedRows();
		// Remove users for each row selected.
		for (int i : slcRowsIndex) {
			int id = Integer.parseInt(table.getValueAt(i, 0).toString());
	
			try {
				service.removeUser(id);
			} catch (FileWritingException e) {
				showErrorMessageBox(e);
			} catch (ObjectNotFoundException e) {
				showErrorMessageBox(e);
			}
		}
		// After a request, must fresh the table content.
		updateUserTableContent();
	}
	// Request service to find a user with the same id, if not, it will throw UserNotFoundException.
	public User rqsFindUserById(int id) throws ObjectNotFoundException {
		return service.findUserById(id);
	}
	//
	public List<Member> rqsfindMembersByHouseKeeperId(int houseKeeperId) {
		return service.findMembersByHouseKeeperId(houseKeeperId);
	}
	// 
	public User rqsFindUserByRealName(String realName) {
		return service.findUserByRealName(realName);
	}
	
	public List<User> rqsfindUsersByRole(Role role) {
		return service.findUsersByRole(role);
	}
	/*
	 * 
	 * 
	 * 
	 */
	public void rqsEditMember(int selectedItemIndexInRightComboBox) {

		try {
			List<Member> memberList = service.getMemberListCanBeAdded(getSelectedUser().getId());
			Member member = memberList.get(selectedItemIndexInRightComboBox);
			
			member.setHouseKeeperId(getSelectedUser().getId());
			service.saveAllMembers();
			
			updateMemberEditTable();
			updateRightMemberComboBox();
			
		} catch (ObjectNotFoundException | FileWritingException e) {
			showErrorMessageBox(e);
		}
	}
	
	public void rqsRemoveMemberFromHouseKeeper() {
		
		try {
			
			int row = memberEditFrame.getLeftTable().getSelectedRow();
			int memberId = Integer.parseInt(memberEditFrame.getLeftTable().getValueAt(row, 0).toString());
			List<Member> memberList = service.getMemberTable().getData();
			
			for(Member member : memberList) {
				if(member.getId() == memberId) {
					member.setHouseKeeperId(-1);
				}
			}
			
			service.saveAllMembers();
			updateMemberEditTable();
			updateRightMemberComboBox();
		
		} catch (ObjectNotFoundException | FileWritingException | FileReadingException e) {
			showErrorMessageBox(e);
		}
		
		
	}
	//
	public void rqsRemoveMember(int memberId) {
		try {
			service.removeMember(memberId);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e);
		}
	}
	//
	public void rqsAddMember() {
		//TODO
		
		List<User> userList = service.findUsersByRole(Role.HOUSE_KEEPER);
		
		int selectedItemIndex = newMemberFrame.getComboBox().getSelectedIndex();
		
		if(selectedItemIndex < 0) {
			showErrorMessageBox("未指派生活管家给这个会员。", "增加会员失败", "该错误是由控制器发起的。");
			return;
		}
		
		int houseKeeperId = userList.get(selectedItemIndex).getId();
		
		int id = -1;
		
		Gender gender = getGenderFromRdBtn(newMemberFrame.getMaleRdBtn());
		
		String telNum = newMemberFrame.getTelNumTxtFld().getText();
		
		String realName = newMemberFrame.getRealNameTxtFld().getText();
		
		LocalDate birthday = LocalDate.parse(newMemberFrame.getBirthdayTxtFld().getText(), glbDateFormatter);
		
		try {
			Member newMember = new Member(id, realName, gender, birthday, telNum, houseKeeperId);
			service.addMember(newMember);
		} catch (CanNotMatchException e) {
			showErrorMessageBox(e);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		}
		
	}
	// #endregion
	
	// #region Show Frame Methods
	

	
	// Show the main frame.
	// When a new main frame is showed, it will refresh table content automatically.
	public void showMainFrame() {
		this.mainFrame = new UserManagementFrame();
		mainFrame.setVisible(true);
		mainFrame.setEnabled(true);
		updateUserTableContent();
	}
	// Show a New User frame
	// Must close this window to refresh the table content
	// It will disable substratum windows. 
	public void showNewUserFrame() {
		mainFrame.setEnabled(false);
		newUserFrame = new NewUserFrame();
		newUserFrame.setVisible(true);
	}
	// Show Edit User frame (New User Frame alike)
	// The data content in edit text field will be filled automatically.
	// Must close this window to refresh the table content
	// It will disable substratum windows. 
	public void showEditUserFrame() {
		
		JTable table = mainFrame.getTable();
		int slcRowIndex = table.getSelectedRow();
		if(slcRowIndex == -1) {
			return;
		}
		mainFrame.setEnabled(false);
		
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
			
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e);
		}
		
	}
	// 
	public void showHouseKeeperEditFrame(int id) {
		try {
			User user = rqsFindUserById(id);
			if(user.getRole() == Role.HOUSE_KEEPER) {
				mainFrame.setEnabled(false);
				houseKeeperPreviewFrame = new HouseKeeperPreviewFrame();
				houseKeeperPreviewFrame.setVisible(true);
				updateHouseKeeperListComboBox();
				
			}
			else {
				showErrorMessageBox("这个用户没有服务。", "查看用户服务时遇到问题", "该错误是由控制器发起的。");
			}
			
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e);
		}

		
	}
	
	// 
	public void showMemberEditFrame() {

		try {
			if(getSelectedUser() == null) {
				return;
			}
			if(getSelectedUser().getRole() != Role.HOUSE_KEEPER) {
				showErrorMessageBox("这个用户不支持修改服务，修改服务的对象必须是生活管家。", "修改服务时遇到问题", "该错误是由控制器发起的。");
				return;
			}
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e);
		}

		mainFrame.setEnabled(false);
		memberEditFrame = new MemberEditFrame();
		memberEditFrame.setVisible(true);
		
		try {
			updateMemberEditTable();
			updateRightMemberComboBox();
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e);
		}
		
	}
	
	public void showNewMemberFrame() {
		//TODO
		newMemberFrame = new NewMemberFrame();
		newMemberFrame.setVisible(true);
		updateNewMemberComboBox();
	}
	
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
	
	// #region Util Methods
	
	// Package the user information into an object instance.
	
	// It will thorw UserInfoInvalidException when encounter certain not-matched data.
	
	private User packUserDataSeg() throws CanNotMatchException {
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
			mainFrame.updateUserTableContent(tableModel);
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
		}
	}
	
	public void updateMemberListComboBox(String houseKeeperRealName) {
		rqsLoadAllMembers();
		houseKeeperPreviewFrame.getMemberListComboBox().removeAllItems();
		User houseKeeper = rqsFindUserByRealName(houseKeeperRealName);
		List<Member> memberList = rqsfindMembersByHouseKeeperId(houseKeeper.getId());
		for(Member member : memberList) {
			houseKeeperPreviewFrame.getMemberListComboBox().addItem(member.getRealName());
		}
	}
	
	public void updateHouseKeeperListComboBox() {
		rqsLoadAllUsers();
		List<User> houseKeeperList = rqsfindUsersByRole(Role.HOUSE_KEEPER);
		for(User houseKeeper : houseKeeperList) {
			houseKeeperPreviewFrame.getHouseKeeperListComboBox().addItem(houseKeeper.getRealName());
		}
	}
	
	public void updateMemberEditTable() throws ObjectNotFoundException {
		
		// Load all of the data in files.
		rqsLoadAllMembers();
		rqsLoadAllUsers();
		
		// Create title and content.
		JTable table = memberEditFrame.getLeftTable();
		List<Member> segMemberList = service.findMembersByHouseKeeperId(getSelectedUser().getId());
		String[] tableTitle = { "身份标识", "姓名", "性别", "出生日期", "电话" };
		String[][] tableContent = new String[segMemberList.size()][tableTitle.length];
		
		int i = 0;
		for (Member member : segMemberList) {
			tableContent[i] = Arrays.copyOf(member.toStringArray(), tableTitle.length);
			i++;
		}
		
		TableModel tableModel = new DefaultTableModel(tableContent, tableTitle) {
			private static final long serialVersionUID = 1L;
			// The table can not be operated.
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		// Update the left-up label with housekeeper name.
		memberEditFrame.getHouseKeeperLabel().setText(getSelectedUser().getRealName());
		
		// Update the whole table.
		table.setModel(tableModel);
	}
	
	// This method will refresh the data in the right combo box in MemberEditFrame
	
	public void updateRightMemberComboBox() throws ObjectNotFoundException {
		
		rqsLoadAllMembers();
		List<Member> memberList;
		memberList = service.getMemberListCanBeAdded(getSelectedUser().getId());
		memberEditFrame.getRightMemberComboBox().removeAllItems();
		for(Member member : memberList) {
			memberEditFrame.getRightMemberComboBox().addItem(member.getRealName());
		}

	}
	
	public void updateNewMemberComboBox() {
		rqsLoadAllMembers();
		List<User> userList = service.findUsersByRole(Role.HOUSE_KEEPER);
		newMemberFrame.getComboBox().removeAllItems();
		for(User user : userList) {
			newMemberFrame.getComboBox().addItem(user.getRealName());
		}
	}
	

	// #endregion

	//

}
