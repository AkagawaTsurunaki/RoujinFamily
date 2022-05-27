package com.github.akagawatsurunaki.roujinfamily.util;

import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.OperatePeriod;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.Role;
import com.github.akagawatsurunaki.roujinfamily.model.RouteType;
import com.github.akagawatsurunaki.roujinfamily.model.Table;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import java.time.DayOfWeek;
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
	}).registerTypeAdapter(DayOfWeek.class, new JsonSerializer<DayOfWeek>() {
		@Override
		public JsonElement serialize(DayOfWeek dayOfWeek, Type sourceType, JsonSerializationContext context) {
			//TODO
			switch(dayOfWeek) {
			case MONDAY:
				return new JsonPrimitive("MONDAY");
			case TUESDAY:
				return new JsonPrimitive("TUESDAY");
			case WEDNESDAY:
				return new JsonPrimitive("WEDNESDAY");
			case THURSDAY:
				return new JsonPrimitive("THURSDAY");
			case FRIDAY:
				return new JsonPrimitive("FRIDAY");
			case SATURDAY:
				return new JsonPrimitive("SATURDAY");
			case SUNDAY:
				return new JsonPrimitive("SUNDAY");
			}
			return new JsonPrimitive("MONDAY");
		}
	}).registerTypeAdapter(OperatePeriod.class, new JsonSerializer<OperatePeriod>() {
		@Override
		public JsonElement serialize(OperatePeriod operatePeriod, Type sourceType, JsonSerializationContext context) {
			//TODO
			switch(operatePeriod) {
			case AM:
				return new JsonPrimitive("AM");
			case PM:
				return new JsonPrimitive("PM");
			}
			return new JsonPrimitive("AM");
		}
	}).registerTypeAdapter(RouteType.class, new JsonSerializer<RouteType>() {
		@Override
		public JsonElement serialize(RouteType routeType, Type sourceType, JsonSerializationContext context) {
			//TODO
			switch(routeType) {
			case INNER_ISLAND:
				return new JsonPrimitive("INNER_ISLAND");
			case OUTER_ISLAND:
				return new JsonPrimitive("OUTER_ISLAND");
			}
			return new JsonPrimitive("INNER_ISLAND");
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
	}).registerTypeAdapter(DayOfWeek.class, new JsonDeserializer<DayOfWeek>() {
		@Override
		public DayOfWeek deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			String genderStr = jsonElement.getAsString();
			if(genderStr.equals("MONDAY")) {
				return DayOfWeek.MONDAY;
			}
			else if(genderStr.equals("TUESDAY")) {
				return DayOfWeek.TUESDAY;
			}
			else if(genderStr.equals("WEDNESDAY")) {
				return DayOfWeek.WEDNESDAY;
			}
			else if(genderStr.equals("THURSDAY")) {
				return DayOfWeek.THURSDAY;
			}
			else if(genderStr.equals("FRIDAY")) {
				return DayOfWeek.FRIDAY;
			}
			else if(genderStr.equals("SATURDAY")) {
				return DayOfWeek.SATURDAY;
			}
			else if(genderStr.equals("SUNDAY")) {
				return DayOfWeek.SUNDAY;
			}
			return null;
		};
	}).registerTypeAdapter(OperatePeriod.class, new JsonDeserializer<OperatePeriod>() {
		@Override
		public OperatePeriod deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			String genderStr = jsonElement.getAsString();
			if(genderStr.equals("AM")) {
				return OperatePeriod.AM;
			}
			else if(genderStr.equals("PM")) {
				return OperatePeriod.PM;
			}
			else {
				throw new JsonParseException("AkagawaTsurunaki: Fail to parse \"OperatePeriod\".");
			}
		};
	}).registerTypeAdapter(RouteType.class, new JsonDeserializer<RouteType>() {
		@Override
		public RouteType deserialize(JsonElement jsonElement, Type sourceType, JsonDeserializationContext context) throws JsonParseException {
			String genderStr = jsonElement.getAsString();
			if(genderStr.equals("INNER_ISLAND")) {
				return RouteType.INNER_ISLAND;
			}
			else if(genderStr.equals("OUTER_ISLAND")) {
				return RouteType.OUTER_ISLAND;
			}
			else {
				throw new JsonParseException("AkagawaTsurunaki: Fail to parse \"RouteType\".");
			}
		};
	})
	  
	  
	  .create();
	
	// #endregion
	
	// #region User, UserList, UserTable -- Json
	
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
	
	// #endregion
	
	// #region Member, MemberList, MemberTable -- Json
	
	public static String fromMemberTableToJson(Table<Member> memberTable) {
		return glbGson.toJson(memberTable, new TypeToken<Table<Member>>() {}.getType());
	}
	
	public static Table<Member> fromJsonToMemberTable(String json){
		return glbGson.fromJson(json, new TypeToken<Table<Member>>() {}.getType());
	}
	
	public static String fromMembersToJson(List<Member> memberList) {
		return glbGson.toJson(memberList, new TypeToken<List<Member>>() {}.getType());
	}
	
	public static List<Member> fromJsonToMembers(String json) {
		return glbGson.fromJson(json, new TypeToken<List<Member>>() {}.getType());
	}
	
	public static String fromMemberToJson(Member member) {
		return glbGson.toJson(member, new TypeToken<Member>() {}.getType());
	}
	
	public static Member fromJsonToMember(String json) {
		return glbGson.fromJson(json, new TypeToken<Member>() {}.getType());
	}
	
	// #endregion

	// #region RegularBus, RegularBusList, RegularBusTable -- Json
	
	public static String fromRegularBusTableToJson(Table<RegularBus> regularBusTable) {
		return glbGson.toJson(regularBusTable, new TypeToken<Table<RegularBus>>() {}.getType());
	}
	
	public static Table<RegularBus> fromJsonToRegularBusTable(String json){
		return glbGson.fromJson(json, new TypeToken<Table<RegularBus>>() {}.getType());
	}
	
	public static String fromRegularBusesToJson(List<RegularBus> regularBusList) {
		return glbGson.toJson(regularBusList, new TypeToken<List<RegularBus>>() {}.getType());
	}
	
	public static List<RegularBus> fromJsonToRegularBuses(String json) {
		return glbGson.fromJson(json, new TypeToken<List<RegularBus>>() {}.getType());
	}
	
	public static String fromRegularBusToJson(RegularBus regularBus) {
		return glbGson.toJson(regularBus, new TypeToken<RegularBus>() {}.getType());
	}
	
	public static RegularBus fromJsonToRegularBus(String json) {
		return glbGson.fromJson(json, new TypeToken<RegularBus>() {}.getType());
	}

	// #endregion
	
}
