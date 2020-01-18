package ua.nure.biloborodov.SummaryTask4.db.entity;

import ua.nure.biloborodov.SummaryTask4.db.Language;

public class Subject extends Entity {

    private static final long serialVersionUID = -6362580020260650413L;

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
