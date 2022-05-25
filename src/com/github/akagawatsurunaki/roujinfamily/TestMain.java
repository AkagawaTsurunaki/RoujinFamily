package com.github.akagawatsurunaki.roujinfamily;

import java.time.LocalDate;
import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoInvalidException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.User;


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
		String[] sA = newUser.toStringArray();
		System.out.println(sA[4]);
		
	};



}
