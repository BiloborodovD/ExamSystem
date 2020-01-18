package ua.nure.biloborodov.SummaryTask4.db;

/**
 * Implements the Role entity
 *
 * @author Dmytro Biloborodov
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
