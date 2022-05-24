package com.github.akagawatsurunaki.roujinfamily.Controller;

import javax.swing.JOptionPane;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.service.LoginService;
import com.github.akagawatsurunaki.roujinfamily.service.LoginServiceImpl;
import com.github.akagawatsurunaki.roujinfamily.view.LoginFrame;

public class LoginController {
	
	
	private static LoginController instance = new LoginController();
	//#region Singleton Constructor
	public static LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	private LoginController() {}
	//#endregion
	
	private static LoginService service = LoginServiceImpl.getInstance();
	
	//#region S
	
	public boolean rqslogin(String userName, String password) throws UserNotFoundException, UserInfoDataReadingException {
		service.initialize();
		return service.login(userName, password);		
	}
	
	
	//#endregion
}
