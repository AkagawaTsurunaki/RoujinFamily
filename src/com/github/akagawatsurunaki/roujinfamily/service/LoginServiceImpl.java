package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;

public class LoginServiceImpl implements LoginService {
	private static LoginService instance = new LoginServiceImpl();
	private static UserDao dao = UserDaoImpl.getInstance();
	//#region Singleton
	public static LoginService getInstance() {
		if(instance == null) {
			instance = new LoginServiceImpl();
		}
		return instance;
	}
	private LoginServiceImpl() {}
	//#endregion
	
	public void initialize() throws UserInfoDataReadingException {
		dao.initialize();
	}
	
	@Override
	public boolean login(String nm, String rawPsw) throws UserNotFoundException, UserInfoDataReadingException {
		return dao.login(nm, rawPsw);
	}
	
	
}
