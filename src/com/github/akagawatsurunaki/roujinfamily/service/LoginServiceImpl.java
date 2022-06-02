package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Role;

public class LoginServiceImpl implements LoginService {
	
	// #region Properties
	
	private static LoginService instance = new LoginServiceImpl();
	private static UserDao dao = UserDaoImpl.getInstance();
	
	// #endregion
	
	// #region Singleton Getter
	
	public static LoginService getInstance() {
		if(instance == null) {
			instance = new LoginServiceImpl();
		}
		return instance;
	}
	private LoginServiceImpl() {}
	
	// #endregion
	
	// #region Override Methods
	
	public void initialize() throws FileReadingException {
		dao.initialize();
	}
	
	@Override
	public Role login(String nm, String rawPsw) throws ObjectNotFoundException, FileReadingException {
		return dao.login(nm, rawPsw);
	}
	@Override
	public int findUserIdByPassword(String password) {
		return dao.findUserIdByPassword(password);
	}
	
	// #endregion
	
}
