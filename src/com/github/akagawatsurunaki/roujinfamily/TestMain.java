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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDao;
import com.github.akagawatsurunaki.roujinfamily.dao.UserDaoImpl;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public class TestMain {

	public static void main(String[] args) throws UserInfoInvalidException {
		System.out.println("Start!");

		String filePath = "C:\\Users\\96514\\Desktop\\save\\2.json";
		List<User> userGroup = new ArrayList<User>();

		User yajuusenpai = new User(0, 
				"user", 
				"11451419Rp?2", 
				"Ò°ÊÞ", 
				Gender.MALE, 
				LocalDate.of(2022, 5, 22),
				"13951419198",
				Role.ADMINISTRATOR
				);
		User shizukururu = new User(0, 
				"ShizukuRuru", 
				"11111asB?11", 
				"ÓêµãÂ¶Â¶", 
				Gender.FEMALE, 
				LocalDate.of(2000, 1, 1),
				"13956779348",
				Role.HOUSE_KEEPER
				);

		userGroup.add(yajuusenpai);
		userGroup.add(shizukururu);
		
		Table<User> table = new Table<User>(2, userGroup);
		
		
		
	};



}
