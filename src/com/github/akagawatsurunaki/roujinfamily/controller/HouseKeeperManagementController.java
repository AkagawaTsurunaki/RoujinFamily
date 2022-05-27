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
	private int loginHouseKeeperId = -1;
	// #endregion

	public void loginInvoke(int houseKeeperId) {
		this.loginHouseKeeperId = houseKeeperId;
		HouseKeeperManagementController.getInstance().showHouseKeeperMngFrame();
	}
	
	public HouseKeeperManagementFrame getMainFrame() {
		return mainFrame;
	}
//	public void setMainFrame(Frame frame) {
//		this.mainFrame = (HouseKeeperManagementFrame) frame;
//	}
//	
	
	// #region Singleton Getter
	
	
	public static HouseKeeperManagementController getInstance() {
		if(instance == null) {
			instance = new HouseKeeperManagementController();
		}
		return instance;
	}
	
	private HouseKeeperManagementController() {};
	
	// #endregion
	
	// #region
	
	public void showMemberInfoEditFrame() {
		memberInfoEditFrame = new MemberInfoEditFrame();
		mainFrame.setEnabled(false);
		memberInfoEditFrame.setVisible(true);
		rqsEditMember();
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
		} catch (CanNotMatchException e) {
			showErrorMessageBox(e);
		} catch (FileWritingException e) {
			showErrorMessageBox(e);
		}
		
		try {
			updateTableContent();
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
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
			showErrorMessageBox(e);
		}
	}
	
	// #endregion
	
	// #region Show Methods
	
	public void showHouseKeeperMngFrame() {
		
		mainFrame = new HouseKeeperManagementFrame();
		mainFrame.setVisible(true);
		try {
			updateTableContent();
		} catch (FileReadingException e) {
			showErrorMessageBox(e);
		}

	}
	
	// #endregion
	
	// #region Update Methods
	
	public void updateTableContent() throws FileReadingException {
		
		// Load all of the data in files.
		service.loadAllMembersFromFile();
		// Create title and content.
		JTable table = mainFrame.getTable();
		List<Member> memberList = service.findMembersByHouseKeeperId(loginHouseKeeperId);
		
		String[] tableTitle = { "身份标识", "姓名", "性别", "出生日期", "电话" };
		String[][] tableContent = new String[memberList.size()][tableTitle.length];

		int i = 0;
		for (Member member : memberList) {
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
	
		// Update the whole table.
		table.setModel(tableModel);
	}
	
	public void updateTextField() {

		memberInfoEditFrame.getRealNameTxtFld().setText(selectedMember.getRealName());
		
		memberInfoEditFrame.getBirthdayTxtFld().setText(glbDateFormatter.format(selectedMember.getBirthday()));
		
		memberInfoEditFrame.getTelNumTxtFld().setText(selectedMember.getTelNumber());
		
		setGenderRadio(memberInfoEditFrame.getMaleRdBtn(), selectedMember);
		
	}
	
	// #endregion
	
}
