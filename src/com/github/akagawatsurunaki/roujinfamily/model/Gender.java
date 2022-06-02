package com.github.akagawatsurunaki.roujinfamily.model;

/**
 * @author Akagawa Tsurunaki
 *
 */
public enum Gender {
	MALE {
		public String toString() {
			return "ÄÐ";
		}
	},
	FEMALE {
		public String toString() {
			return "Å®";
		}
	},
	UNKNOWN {
		public String toString() {
			return "Î´Öª";
		}
	}
}
