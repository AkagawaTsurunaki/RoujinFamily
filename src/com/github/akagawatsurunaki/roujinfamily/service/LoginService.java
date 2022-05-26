package com.github.akagawatsurunaki.roujinfamily.service;

import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Role;

public interface LoginService {
	public void initialize() throws FileReadingException;
	public Role login(String nm, String rawPsw) throws ObjectNotFoundException, FileReadingException;
}
