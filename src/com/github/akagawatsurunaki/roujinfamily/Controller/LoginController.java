package com.github.akagawatsurunaki.roujinfamily.Controller;

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
	
	public boolean rqslogin(String nm, String psw) throws UserNotFoundException, UserInfoDataReadingException {
		System.out.println(nm + "," + psw);
		return service.login(nm, psw);
	}
	
	//#endregion
}
