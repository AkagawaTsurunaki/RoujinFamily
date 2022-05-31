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
				showErrorMessageBox("输入错误次数过多，为保护系统安全，程序即将关闭。", "程序即将关闭", "本次程序关闭是由控制器发起的。", mainLoginFrame);
				System.exit(0);
			}
			
			String userName = mainLoginFrame.getAccountTxtFld().getText();
			String password = new String(mainLoginFrame.getPasswordTxtFld().getPassword());
			
			service.initialize();
			
			if (userName == null || userName.isEmpty()) {
				showErrorMessageBox("您未输入用户名。", "登陆失败", "该错误是由控制器发起的。", mainLoginFrame);
				return;
			}
			
			if (password == null || password.isEmpty()) {
				showErrorMessageBox("您未输入密码。", "登陆失败", "该错误是由控制器发起的。", mainLoginFrame);
				return;
			}
			
			Role role = service.login(userName, password);
			
			if (role == null) {
				mainLoginFrame.getPasswordTxtFld().setText("");
				showErrorMessageBox("您输入的密码错误。\n你还可以再次尝试登陆" + (MAX_ERROR_COUNT - errorCount)  + "次。", "登陆失败", "该错误是由控制器发起的。", mainLoginFrame);
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
			showErrorMessageBox("您输入的用户名有误。", "登陆失败", "该错误是由控制器发起的。", mainLoginFrame);
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
