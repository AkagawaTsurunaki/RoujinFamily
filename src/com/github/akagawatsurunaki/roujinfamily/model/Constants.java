package com.github.akagawatsurunaki.roujinfamily.model;

import java.time.LocalTime;

/**
 * @author Akagawa Tsurunaki
 *
 */
public class Constants {
	public final static int MIN_USERNAME_LENGYH = 1;
	public final static int MAX_USERNAME_LENGYH = 20;
	public final static int MIN_PASSWORD_LENGTH = 8;
	public final static int MAX_PASSWORD_LENGTH = 20;
	public final static int MIN_REALNAME_LENGTH = 1;
	public final static int MAX_REALNAME_LENGTH = 20;
	public final static int MIN_NOTE_LENGTH = 0;
	public final static int MAX_NOTE_LENGTH = 200;
	public final static int MIN_ROUTECODE_LENGTH = 1;
	public final static int MAX_ROUTECODE_LENGTH = 20;
	public final static int MIN_ROUTENAME_LENGTH = 1;
	public final static int MAX_ROUTENAME_LENGTH = 20;
	public final static LocalTime DEFAULT_TERMINNATE_TIME = LocalTime.of(23, 59);
	public final static int DEFAULT_OBJECT_ID = -1; 
}
