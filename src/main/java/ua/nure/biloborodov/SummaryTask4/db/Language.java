package ua.nure.biloborodov.SummaryTask4.db;

public enum Language {
    EN, 
    RU;

    public static Language define(int langId) {
	Language[] langs = values();
	if (langId >= langs.length || langId < 0) {
	    throw new IllegalArgumentException("Invalid language id");
	}
	return langs[langId];
    }

    public String getName() {
	return name().toLowerCase();
    }
}
