package ua.nure.biloborodov.summarytask4.db;

/**
 * Implements the Role entity
 */
public enum Role {
	ADMIN,
	STUDENT, 
	BLOCKED;
	
	public static Role define(int roleId) {
	    Role[] roles = values();
		if (roleId >= roles.length || roleId < 0) {
		    throw new IllegalArgumentException("Invalid role id");
		}
		return roles[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
