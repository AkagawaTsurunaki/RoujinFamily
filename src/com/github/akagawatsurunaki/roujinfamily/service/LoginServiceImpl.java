package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.LoginDao;
import com.github.akagawatsurunaki.roujinfamily.dao.LoginDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;

public class LoginServiceImpl implements LoginService {
	private static LoginService instance = new LoginServiceImpl();
	private static LoginDao dao = LoginDaoImpl.getInstance();
	//#region Singleton
	public static LoginService getInstance() {
		if(instance == null) {
			instance = new LoginServiceImpl();
		}
		return instance;
	}
	private LoginServiceImpl() {}
	//#endregion
	@Override
	public boolean login(String nm, String rawPsw) throws UserNotFoundException, UserInfoDataReadingException {
		return dao.login(nm, rawPsw);
	}
	
	
}
