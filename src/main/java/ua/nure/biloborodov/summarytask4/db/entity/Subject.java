package ua.nure.biloborodov.summarytask4.db.entity;

import ua.nure.biloborodov.summarytask4.db.Language;

public class Subject extends Entity {

    private String name;
    private int langId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLangId() {
        return Language.define(langId);
    }

    public void setLangId(Language language) {
        this.langId = language.ordinal();
    }
}
