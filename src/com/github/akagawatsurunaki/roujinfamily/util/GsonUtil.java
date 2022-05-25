package com.github.akagawatsurunaki.roujinfamily.util;

import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.Table;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import com.github.akagawatsurunaki.roujinfamily.exception.UserInfoDataReadingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.github.akagawatsurunaki.roujinfamily.model.User;

public class GsonUtil {
	// #region Regist Global Gson

	// Regist a global gson in order to serialize and deserialize.
	private static Gson glbGson = new GsonBuilder()
	  .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
		@Override
		public JsonElement serialize(LocalDate localDate, Type sourceType, JsonSerializationContext context) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			return new JsonPrimitive(formatter.format(localDate));
		}
	}).registerTypeAdapter(LocalTime.class, new JsonSerializer<LocalTime>() {
		@Override
		public JsonElement serialize(LocalTime localTime, Type sourceType, JsonSerializationContext context) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			return new JsonPrimitive(formatter.format(localTime));
		}
	}).registerTypeAdapter(Gender.class, new JsonSerializer<Gender>() {
		@Override
		public JsonElement serialize(Gender gender, Type sourceType, JsonSerializationContext context) {	
			switch (gender) {
			case MALE: {
				return new JsonPrimitive("male");
			}
			case FEMALE: {
				return new JsonPrimitive("female");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + gender);
			}
		}
	}).registerTypeAdapter(Role.class, new JsonSerializer<Role>() {
		@Override
		public JsonElement serialize(Role role, Type sourceType, JsonSerializationContext context) {	
			switch (role) {
			case ADMINISTRATOR : {
				return new JsonPrimitive("admin");
			}
			case HOUSE_KEEPER: {
				return new JsonPrimitive("houseKeeper");
			}
			case LOGISTICS: {
				return new JsonPrimitive("logistics");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + role);
			}
		}
	}).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
		@Override
		public LocalDate deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");	
			return LocalDate.parse(jsonElement.getAsString(), formatter);
		};
	}).registerTypeAdapter(LocalTime.class, new JsonDeserializer<LocalTime>() {
		@Override
		public LocalTime deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");	
			return LocalTime.parse(jsonElement.getAsString(), formatter);
		};
	}).registerTypeAdapter(Gender.class, new JsonDeserializer<Gender>() {
		@Override
		public Gender deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			String genderStr = jsonElement.getAsString();
			if(genderStr.equals("MALE")) {
				return Gender.MALE;
			}
			else if(genderStr.equals("FEMALE")) {
				return Gender.FEMALE;
			}
			else {
				throw new JsonParseException("AkagawaTsurunaki: Fail to parse \"Gender\".");
			}
		};
	}).registerTypeAdapter(Role.class, new JsonDeserializer<Role>() {
		@Override
		public Role deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			String genderStr = jsonElement.getAsString();
			if(genderStr.equals("ADMINISTRATOR")) {
				return Role.ADMINISTRATOR;
			}
			else if(genderStr.equals("HOUSE_KEEPER")) {
				return Role.HOUSE_KEEPER;
			}
			else if(genderStr.equals("LOGISTICS")) {
				return Role.LOGISTICS;
			}
			else {
				throw new JsonParseException("AkagawaTsurunaki: Fail to parse \"Role\".");
			}
		};
	}).create();
	
	// #endregion

	public static String fromUserTableToJson(Table<User> userTable) {
		return glbGson.toJson(userTable, new TypeToken<Table<User>>() {}.getType());
	}
	
	public static Table<User> fromJsonToUserTable(String json){
		return glbGson.fromJson(json, new TypeToken<Table<User>>() {}.getType());
	}
	
	public static String fromUsersToJson(List<User> userList) {
		return glbGson.toJson(userList, new TypeToken<List<User>>() {}.getType());
	}
	
	public static List<User> fromJsonToUsers(String json) {
		return glbGson.fromJson(json, new TypeToken<List<User>>() {}.getType());
	}
	
	public static String fromUserToJson(User user) {
		return glbGson.toJson(user, new TypeToken<User>() {}.getType());
	}
	
	public static User fromJsonToUser(String json) {
		return glbGson.fromJson(json, new TypeToken<User>() {}.getType());
	}

	

}
