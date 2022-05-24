package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserNotFoundException;

public interface LoginService {
	public void initialize() throws UserInfoDataReadingException;
	public boolean login(String nm, String rawPsw) throws UserNotFoundException, UserInfoDataReadingException;
}
