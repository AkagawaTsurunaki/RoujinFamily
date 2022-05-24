package com.github.akagawatsurunaki.roujinfamily;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import org.apache.commons.io.IOUtils;

import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;
import com.github.akagawatsurunaki.roujinfamily.view.UserManagementFrame;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementService;
import com.github.akagawatsurunaki.roujinfamily.service.UserManagementServiceImpl;

public class TestMain {

	public static void main(String[] args) throws UserInfoInvalidException {
		
		System.out.println("Start!");
		
		LocalDate birth = LocalDate.now();
		User admin = new User(
	            -1,
	            "Admin",
	            "y58Jk*326&23",
	            "≤‚ ‘”√π‹¿Ì‘±",
	            Gender.MALE,
	            birth,
	            "13999999999",
	            Role.ADMINISTRATOR
				);
		User user = new User(
	            -1,
	            "Test01",
	            "Test2022524?!",
	            "≤‚ ‘",
	            Gender.MALE,
	            birth,
	            "13999999999",
	            Role.LOGISTICS
				);
		User user2 = new User(
	            -1,
	            "Test02",
	            "Test2022524?!",
	            "≤‚ ‘",
	            Gender.MALE,
	            birth,
	            "13999999999",
	            Role.HOUSE_KEEPER
				);
		User newUser = new User(
	            -1,
	            "TestChange",
	            "Test2022524?!",
	            "≤‚ ‘",
	            Gender.FEMALE,
	            birth,
	            "13999999999",
	            Role.HOUSE_KEEPER
				);
		
		UserDao dao = UserDaoImpl.getInstance();
		UserManagementService serv = UserManagementServiceImpl.getInstance();
		try {
			System.out.println("Dao crearting...");
			
			dao.loadAllUsersFromFile();
			Table<User> t = dao.getUsersTable();
			serv.clearAllUsers();
			serv.addUser(admin);
			System.out.println(serv.addUser(user));
			System.out.println(serv.addUser(user2));
			System.out.println(serv.removeUser(user));
			System.out.println(serv.editUser(newUser, user2));
			System.out.println(serv.findUserByName("TestChange"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};



}
