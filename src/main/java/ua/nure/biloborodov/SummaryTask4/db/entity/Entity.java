package ua.nure.biloborodov.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author Dmytro Biloborodov
 */
public abstract class Entity implements Serializable {
    
    private static final long serialVersionUID = -2238738601563600839L;

    private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
