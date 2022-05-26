package com.github.akagawatsurunaki.roujinfamily.controller;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.service.HouseKeeperService;
import com.github.akagawatsurunaki.roujinfamily.service.HouseKeeperServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.HouseKeeperManagementFrame;

public class HouseKeeperManagementController extends Controller {
	
	// #region Properties 
	
	private static HouseKeeperManagementController instance = new HouseKeeperManagementController();
	private HouseKeeperService service = HouseKeeperServiceImpl.getInstance();
	private HouseKeeperManagementFrame mainFrame;
	private int loginHouseKeeperId = -1;
	// #endregion
	
	// 临时主函数
	
	public void loginInvoke(int houseKeeperId) {
		this.loginHouseKeeperId = houseKeeperId;
		HouseKeeperManagementController.getInstance().showHouseKeeperMngFrame();
	}
	
	
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
	
	public void rqsEditMember() {
		 //List<Member> memberList = service.getMemberTable().getData();
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
	
	// #endregion
	
}
