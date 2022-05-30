package com.github.akagawatsurunaki.roujinfamily.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Constants;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.service.HouseKeeperService;
import com.github.akagawatsurunaki.roujinfamily.service.HouseKeeperServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.HouseKeeperManagementFrame;
import com.github.akagawatsurunaki.roujinfamily.view.MemberInfoEditFrame;

public class HouseKeeperManagementController extends Controller {
	
	// #region Properties 
	
	private static HouseKeeperManagementController instance = new HouseKeeperManagementController();
	private HouseKeeperService service = HouseKeeperServiceImpl.getInstance();
	private HouseKeeperManagementFrame mainFrame;
	private MemberInfoEditFrame memberInfoEditFrame;
	private Member selectedMember;
	private int loginHouseKeeperId = Constants.DEFAULT_OBJECT_ID;
	
	// #endregion

	// #region Singleton Getter
	
	public static HouseKeeperManagementController getInstance() {
		if(instance == null) {
			instance = new HouseKeeperManagementController();
		}
		return instance;
	}

	private HouseKeeperManagementController() {};
	
	// #endregion
	
	// #region Invoke Method
	
	public void loginInvoke(int houseKeeperId) {
		this.loginHouseKeeperId = houseKeeperId;
		HouseKeeperManagementController.getInstance().showHouseKeeperManagementFrame();
	}
	
	// #endregion
	
	// #region Frame Getter
	
	public HouseKeeperManagementFrame getMainFrame() {
		return mainFrame;
	}
	
	// #endregion

	// #region Show Frame Methods
	
	public void showMemberInfoEditFrame() {
		memberInfoEditFrame = new MemberInfoEditFrame();
		mainFrame.setEnabled(false);
		memberInfoEditFrame.setVisible(true);
		rqsEditMember();
	}
	
	public void showHouseKeeperManagementFrame() {
		mainFrame = new HouseKeeperManagementFrame();
		mainFrame.setVisible(true);
		try {
			updateTableContent();
		} catch (FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	// #endregion

	// #region Request Service Methods
	
	public void rqsAddMember() {

		int id = selectedMember.getId();
		Gender gender = getGenderFromRdBtn(memberInfoEditFrame.getMaleRdBtn());
		String telNum = memberInfoEditFrame.getTelNumTxtFld().getText();
		String realName = memberInfoEditFrame.getRealNameTxtFld().getText();
		LocalDate birthday = LocalDate.parse(memberInfoEditFrame.getBirthdayTxtFld().getText(), glbDateFormatter);
		
		try {
			Member newMember = new Member(id, realName, gender, birthday, telNum, loginHouseKeeperId);
			service.addMember(newMember);
			updateTableContent();
		} catch (CanNotMatchException | FileWritingException | FileReadingException e) {
			showErrorMessageBox(e, mainFrame);
		}
		
	}
	
	public void rqsEditMember() {
		
		int row = mainFrame.getTable().getSelectedRow();
		if(row == -1) {
			return;
		}
		int memberId = Integer.parseInt(mainFrame.getTable().getValueAt(row, 0).toString());
		
		try {
			selectedMember = service.findMemberById(memberId);
			updateTextField();
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox(e, mainFrame);
		}
	}
	
	// #endregion

	// #region Update Methods
	
	private void updateTableContent() throws FileReadingException {
		// Load all of the data in files.
		service.loadAllMembersFromFile();
		// Update the whole table.
		mainFrame.getTable().setModel(service.getMemberEditTableModel(loginHouseKeeperId));
	}
	
	private void updateTextField() {
		memberInfoEditFrame.getRealNameTxtFld().setText(selectedMember.getRealName());
		memberInfoEditFrame.getBirthdayTxtFld().setText(glbDateFormatter.format(selectedMember.getBirthday()));
		memberInfoEditFrame.getTelNumTxtFld().setText(selectedMember.getTelNumber());
		setGenderRadio(memberInfoEditFrame.getMaleRdBtn(), selectedMember);	
	}
	
	// #endregion
	
}
