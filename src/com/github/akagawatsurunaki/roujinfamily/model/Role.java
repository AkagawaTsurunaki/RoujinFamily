package com.github.akagawatsurunaki.roujinfamily.model;

/**
 * @author Akagawa Tsurunaki
 *
 */
public enum Role {
	ADMINISTRATOR {
		public String toString() {
			return "����Ա";
		}
	},
	HOUSE_KEEPER{
		public String toString() {
			return "����ܼ�";
		}
	},
	LOGISTICS{
		public String toString() {
			return "���ڹ���";
		}
	}
	
}
