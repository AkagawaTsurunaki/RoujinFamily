package com.github.akagawatsurunaki.roujinfamily.controller;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.service.LoginService;
import com.github.akagawatsurunaki.roujinfamily.service.LoginServiceImpl;

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
	
	public Role rqsLogin(String userName, String password) throws ObjectNotFoundException, FileReadingException {
		service.initialize();
		return service.login(userName, password);
	}
	
	
	//#endregion
}
