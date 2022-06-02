package com.github.akagawatsurunaki.roujinfamily.model;

/**
 * @author Akagawa Tsurunaki
 *
 */
public enum Role {
	ADMINISTRATOR {
		public String toString() {
			return "管理员";
		}
	},
	HOUSE_KEEPER{
		public String toString() {
			return "生活管家";
		}
	},
	LOGISTICS{
		public String toString() {
			return "后勤管理";
		}
	}
	
}
