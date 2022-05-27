package com.github.akagawatsurunaki.roujinfamily.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.github.akagawatsurunaki.roujinfamily.exception.FileReadingException;
import com.github.akagawatsurunaki.roujinfamily.exception.FileWritingException;
import com.github.akagawatsurunaki.roujinfamily.exception.CanNotMatchException;
import com.github.akagawatsurunaki.roujinfamily.exception.ObjectNotFoundException;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.util.FileUtil;
import com.github.akagawatsurunaki.roujinfamily.util.GsonUtil;

public class UserDaoImpl implements UserDao {

	private static UserDao instance = new UserDaoImpl();
	private static final String filePath = "C:\\Users\\96514\\Desktop\\save\\Test.json";
	private static User loginUser;
	private Table<User> userTable;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	@Override
	public void initialize() throws FileReadingException {
		instance.loadAllUsersFromFile();
	}

	private UserDaoImpl() {
	}

	@Override
	public Role login(String userName, String password) throws ObjectNotFoundException {
		loginUser = findUserByUserName(userName);
		if (loginUser.getPassword().equals(password)) {
			return loginUser.getRole();
		}
		return null;
	}

	@Override
	public void loadAllUsersFromFile() throws FileReadingException {
		try {
			String str = FileUtil.readFile(filePath);
			this.userTable = GsonUtil.fromJsonToUserTable(str);
		} catch (IOException e) {
			throw new FileReadingException("用户信息文件无法读取。", "读取文件失败", "该错误是由数据层发起的。");
		}
	}

	@Override
	public void saveAllUsersToFile() throws FileWritingException {
		String dataStr = GsonUtil.fromUserTableToJson(userTable);
		try {
			FileUtil.writeFile(filePath, dataStr);
		} catch (IOException e) {
			throw new FileWritingException("会员信息文件无法保存。", "写入文件失败", "该错误是由数据层发起的。");
		}
	}

	@Override
	public Table<User> getUserTable() {
		return this.userTable;
	}

	@Override
	public boolean addUser(User newUser) throws FileWritingException, CanNotMatchException {

		List<User> userList = getUserTable().getData();
		if (userList == null || userList.isEmpty()) {

			for (User user : userList) {
				if (user.getId() == newUser.getId()) {
					user.setUserName(newUser.getUserName());
					user.setPassword(newUser.getPassword());
					user.setRealName(newUser.getRealName());
					user.setRole(newUser.getRole());
					user.setTelNumber(newUser.getTelNumber());
					
					user.setGender(newUser.getGender());
					user.setBirthday(newUser.getBirthday());
					saveAllUsersToFile();
					return true;
				}
			}
		}
		newUser.setId(userTable.getIdCount() + 1);
		userTable.addDataSeg(newUser);
		saveAllUsersToFile();

		return true;
	}

	@Override
	public boolean clearUserTable() throws FileWritingException {
		userTable.clear();
		saveAllUsersToFile();
		return true;
	}

	@Override
	public boolean removeUser(int id) throws FileWritingException, ObjectNotFoundException {
		userTable.removeDataSeg(findUserById(id));
		saveAllUsersToFile();
		return true;

	}

	@Override
	public User findUserByUserName(String userName) throws ObjectNotFoundException {
		List<User> userList = getUserTable().getData();
		for (User user : userList) {
			if (userName.equals(user.getUserName())) {
				return user;
			}
		}
		throw new ObjectNotFoundException("不存在用户名为“" + userName + "”的用户。", "找不到对象", "该错误是由数据层发起的。");

	}

	@Override
	public User findUserById(int id) throws ObjectNotFoundException {
		List<User> userList = getUserTable().getData();
		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new ObjectNotFoundException("不存在ID为“" + id + "”的用户。", "找不到对象", "该错误是由数据层发起的。");

	}

	@Override
	public List<User> findUsersByRole(Role role) {
		List<User> userList = getUserTable().getData();
		List<User> ret = new ArrayList<User>();
		for (User user : userList) {
			if (user.getRole().equals(role)) {
				ret.add(user);
			}
		}
		return ret;
	}

	@Override
	public User findUserByRealName(String realName) {
		List<User> userList = getUserTable().getData();
		for (User user : userList) {
			if (user.getRealName().equals(realName)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public int findUserIdByPassword(String password) {
		List<User> userList = getUserTable().getData();
		for (User user : userList) {
			if (user.getPassword().equals(password)) {
				return user.getId();
			}
		}
		return -1;
	}

}
