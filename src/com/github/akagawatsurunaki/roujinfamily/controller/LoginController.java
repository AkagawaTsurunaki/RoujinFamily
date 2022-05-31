package com.github.akagawatsurunaki.roujinfamily.controller;

import java.awt.EventQueue;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.service.LoginService;
import com.github.akagawatsurunaki.roujinfamily.service.LoginServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.LoginFrame;

public class LoginController extends Controller {
	
	// #region Properties
	private static int errorCount = 0;
	private static final int MAX_ERROR_COUNT = 5;
	private LoginFrame mainLoginFrame;
	private static LoginController instance = new LoginController();
	private static LoginService service = LoginServiceImpl.getInstance();
	
	// #endregion
	
	// #region Singleton Getters
	public static LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	private LoginController() {}
	// #endregion

	// #region Show Frame Methods
	
	public void showMainFrame() {
		mainLoginFrame = new LoginFrame();
		mainLoginFrame.setVisible(true);
	}
	
	// #endregion
	
	// #region Request Service Methods
	
	public void rqsLogin() {
		
		try {
			// If input error several times in succession.
			if (errorCount >= MAX_ERROR_COUNT) {
				showErrorMessageBox("�������������࣬Ϊ����ϵͳ��ȫ�����򼴽��رա�", "���򼴽��ر�", "���γ���ر����ɿ���������ġ�", mainLoginFrame);
				System.exit(0);
			}
			
			String userName = mainLoginFrame.getAccountTxtFld().getText();
			String password = new String(mainLoginFrame.getPasswordTxtFld().getPassword());
			
			service.initialize();
			
			if (userName == null || userName.isEmpty()) {
				showErrorMessageBox("��δ�����û�����", "��½ʧ��", "�ô������ɿ���������ġ�", mainLoginFrame);
				return;
			}
			
			if (password == null || password.isEmpty()) {
				showErrorMessageBox("��δ�������롣", "��½ʧ��", "�ô������ɿ���������ġ�", mainLoginFrame);
				return;
			}
			
			Role role = service.login(userName, password);
			
			if (role == null) {
				mainLoginFrame.getPasswordTxtFld().setText("");
				showErrorMessageBox("��������������\n�㻹�����ٴγ��Ե�½" + (MAX_ERROR_COUNT - errorCount)  + "�Ρ�", "��½ʧ��", "�ô������ɿ���������ġ�", mainLoginFrame);
				errorCount++;
				return;
			}
			
			errorCount = 0;
			
			switch (role) {
			case ADMINISTRATOR: {
				UserManagementController.getInstance().loginInvoke();
				break;
			}
			case HOUSE_KEEPER: {
				HouseKeeperManagementController.getInstance().loginInvoke(service.findUserIdByPassword(password));
				break;
			}
			case LOGISTICS: {
				LogisticsManagementController.getInstance().loginInvoke();
				break;
			}
			}
			
			mainLoginFrame.dispose();
			
		} catch (ObjectNotFoundException e) {
			showErrorMessageBox("��������û�������", "��½ʧ��", "�ô������ɿ���������ġ�", mainLoginFrame);
		} catch (FileReadingException e) {
			showErrorMessageBox(e, mainLoginFrame);
		}
	}
	
	
	//#endregion
	
	// #region Main 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginController.getInstance().showMainFrame();
				} catch (Exception e) {
					System.exit(-1);
				}
			}
		});
	}
	// #endregion
}
