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
import com.github.akagawatsurunaki.roujinfamily.model.Constants;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.util.GlobalFormatter;
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
	
	//private User selectedUser;
	// Must through this method to get selected house keeper.
	private User getSelectedUserInMainFrame() throws ObjectNotFoundException {
		int selectedRow = mainFrame.getTable().getSelectedRow();
		if(selectedRow == -1) {
			return null;
		}
		int selectedHouseKeeperId = 
				Integer.parseInt(mainFrame.getTable().getValueAt(selectedRow, 0).toString());
		return service.findUserById(selectedHouseKeeperId);
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
	
	public void rqsUpdateMemberListComboBox(String name) {
		try {
			updateMemberListComboBox(name);
		} catch (FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	
	// Request service to add a new user into the current user data list in the memory.
	public void rqsAddUser() {
		try {
			service.addUser(packUserInfo());
			// After a request, must fresh the table content.
			updateUserTableContent();
		} catch (CanNotMatchException | FileWritingException e) {
			showErrorMessageBox(e, mainFrame);
		} catch (Exception e) {
			showErrorMessageBox("您输入的信息是非法的。\n这可能是由于您输入了无法读取的信息，或者存在未输入的部分。", "非法输入", "该错误是由控制器层发起的。", mainFrame);
		}
		
	}
	// Request service to remove the selected user from the current user data list in the memory.
	// This method allow system user to multiselect users that will be removed. 
	public void rqsRemoveUser() {
		int[] slcRowsIndex = mainFrame.getTable().getSelectedRows();
		// Remove users for each row selected.
		for (int i : slcRowsIndex) {
			int id = Integer.parseInt(mainFrame.getTable().getValueAt(i, 0).toString());
			try {
				service.removeUser(id);
			} catch (FileWritingException  | ObjectNotFoundException e) {
				showErrorMessageBox(e, mainFrame);
			}
		}
		// After a request, must fresh the table content.
		updateUserTableContent();
	}
	
	public void rqsEditMember(int selectedItemIndexInRightComboBox) {

		try {
			List<Member> memberList = service.getMemberListCanBeAdded(getSelectedUserInMainFrame().getId());
			Member member = memberList.get(selectedItemIndexInRightComboBox);
			member.setHouseKeeperId(getSelectedUserInMainFrame().getId());
			service.saveAllMembers();
			updateMemberEditTable();
			updateRightMemberComboBox();
		} catch (ObjectNotFoundException | FileReadingException | FileWritingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	public void rqsRemoveHouseKeeperFromMember() {
		
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
			showErrorMessageBox(e, mainFrame);
		}
	}

	public void rqsAddMemberToHouseKeeper() {
		
		List<User> userList = service.findUsersByRole(Role.HOUSE_KEEPER);
		int selectedItemIndex = newMemberFrame.getComboBox().getSelectedIndex();
		
		if(selectedItemIndex < 0) {
			showErrorMessageBox("未指派生活管家给这个会员。", "增加会员失败", "该错误是由控制器发起的。", mainFrame);
			return;
		}
		
		int houseKeeperId = userList.get(selectedItemIndex).getId();
		Gender gender = getGenderFromRdBtn(newMemberFrame.getMaleRdBtn());
		String telNum = newMemberFrame.getTelNumTxtFld().getText();
		String realName = newMemberFrame.getRealNameTxtFld().getText();
		LocalDate birthday = LocalDate.parse(newMemberFrame.getBirthdayTxtFld().getText(), glbDateFormatter);
		
		try {
			Member newMember = new Member(Constants.DEFAULT_OBJECT_ID, realName, gender, birthday, telNum, houseKeeperId);
			service.addMember(newMember);
		} catch (CanNotMatchException | FileWritingException e) {
			showErrorMessageBox(e, mainFrame);
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
		if(slcRowIndex == -1) { return; }
		mainFrame.setEnabled(false);
		showNewUserFrame();

		try {
			int id = Integer.parseInt(table.getValueAt(slcRowIndex, 0).toString());
			User user = service.findUserById(id);
			newUserFrame.setUserId(user.getId());
			newUserFrame.getUserNameTxtFld().setText(user.getUserName());
			newUserFrame.getPasswordTxtFld().setText(user.getPassword());
			newUserFrame.getRealNameTxtFld().setText(user.getRealName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			String birthday = formatter.format(user.getBirthday());
			newUserFrame.getBirthdayTxtFld().setText(birthday);
			newUserFrame.getTelNumTxtFld().setText(user.getTelNumber());
			newUserFrame.getFemaleRdBt().setSelected(user.getGender() == Gender.FEMALE ? true : false);
			newUserFrame.getRoleCbBox().setSelectedItem(user.getRole());
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e, mainFrame);
		}
		
	}
	// 
	public void showHouseKeeperEditFrame(int id) {
		try {
			User user = service.findUserById(id);
			if(user.getRole() == Role.HOUSE_KEEPER) {
				mainFrame.setEnabled(false);
				houseKeeperPreviewFrame = new HouseKeeperPreviewFrame();
				houseKeeperPreviewFrame.setVisible(true);
				updateHouseKeeperListComboBox();
			}
			else {
				showErrorMessageBox("这个用户没有服务。", "查看用户服务时遇到问题", "该错误是由控制器发起的。", mainFrame);
			}
			
		} catch (ObjectNotFoundException | FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	// 
	public void showMemberEditFrame() {

		try {
			if(getSelectedUserInMainFrame() == null) {
				return;
			}
			if(getSelectedUserInMainFrame().getRole() != Role.HOUSE_KEEPER) {
				showErrorMessageBox("这个用户不支持修改服务，修改服务的对象必须是生活管家。", "修改服务时遇到问题", "该错误是由控制器发起的。", mainFrame);
				return;
			}
			mainFrame.setEnabled(false);
			memberEditFrame = new MemberEditFrame();
			memberEditFrame.setVisible(true);
			updateMemberEditTable();
			updateRightMemberComboBox();
		} catch (ObjectNotFoundException | FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	public void showNewMemberFrame() {
		newMemberFrame = new NewMemberFrame();
		newMemberFrame.setVisible(true);
		try {
			updateNewMemberComboBox();
		} catch (FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	// #endregion
	
	// #region Util Methods
	
	// Package the user information into an object instance.
	// It will thorw UserInfoInvalidException when encounter certain not-matched data.
	private User packUserInfo() throws CanNotMatchException, Exception {
		int id = newUserFrame.getUserId();
		String userName = newUserFrame.getUserNameTxtFld().getText();
		String password = newUserFrame.getPasswordTxtFld().getText();
		String realName = newUserFrame.getRealNameTxtFld().getText();
		Gender gender = newUserFrame.getFemaleRdBt().isSelected() ? Gender.FEMALE : Gender.MALE;
		String birthdayStr = newUserFrame.getBirthdayTxtFld().getText();
		LocalDate birthday = LocalDate.parse(birthdayStr, GlobalFormatter.dateFormatter);
		String telNumber = newUserFrame.getTelNumTxtFld().getText();
		Role role = (Role) newUserFrame.getRoleCbBox().getSelectedItem();
		User user = new User(id, userName, password, realName, gender, birthday, telNumber, role);
		return user;
	}
	
	// #endregion
	
	// #region Update Methods
	
	// Refresh the table content about the information of the users.
	private void updateUserTableContent() {
		try {
			mainFrame.updateUserTableContent(service.getUserTableModel());
		} catch (FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	private void updateMemberListComboBox(String houseKeeperRealName) throws FileReadingException {
		service.loadAllMembers();
		User houseKeeper = service.findUserByRealName(houseKeeperRealName);		
		updateComboBoxWithRealName(
				service.findMembersByHouseKeeperId(houseKeeper.getId()), 
				houseKeeperPreviewFrame.getMemberListComboBox());
	}
	
	private void updateHouseKeeperListComboBox() throws FileReadingException {
		service.loadAllUsers();
		List<User> houseKeeperList = service.findUsersByRole(Role.HOUSE_KEEPER);
		for(User houseKeeper : houseKeeperList) {
			houseKeeperPreviewFrame.getHouseKeeperListComboBox().addItem(houseKeeper.getRealName());
		}
	}
	
	private void updateMemberEditTable() throws ObjectNotFoundException, FileReadingException {
		
		// Load all of the data in files.
		service.loadAllMembers();
		service.loadAllUsers();
		// Create title and content.
		// Update the left-up label with housekeeper name.
		memberEditFrame.getHouseKeeperLabel().setText(getSelectedUserInMainFrame().getRealName());
		// Update the whole table.
		memberEditFrame.getLeftTable().setModel(service.getMemberEditTableModel(getSelectedUserInMainFrame().getId()));
	}
	
	// This method will refresh the data in the right combo box in MemberEditFrame
	
	private void updateRightMemberComboBox() throws ObjectNotFoundException, FileReadingException {
		service.loadAllMembers();
		updateComboBoxWithRealName(
				service.getMemberListCanBeAdded(getSelectedUserInMainFrame().getId()),
				memberEditFrame.getRightMemberComboBox());
	}
	
	private void updateNewMemberComboBox() throws FileReadingException {
		service.loadAllMembers();
		updateComboBoxWithRealName(service.findUsersByRole(Role.HOUSE_KEEPER), newMemberFrame.getComboBox());
	}
	// #endregion
}
