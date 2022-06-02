package com.github.akagawatsurunaki.roujinfamily.model;

/**
 * @author Akagawa Tsurunaki
 *
 */
public enum Gender {
	MALE {
		public String toString() {
			return "��";
		}
	},
	FEMALE {
		public String toString() {
			return "Ů";
		}
	},
	UNKNOWN {
		public String toString() {
			return "δ֪";
		}
	}
}
