package ua.nure.biloborodov.summarytask4.db;

public enum DifficultyLevel {
    ELEMENTARY,
    INTERMEDIATE,
    ADVANCED;
	
	public static DifficultyLevel define(int diffId) {
	    DifficultyLevel[] levels = values();
		if (diffId >= levels.length || diffId < 0) {
		    throw new IllegalArgumentException("Invalid difficulty id");
		}
		return levels[diffId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
