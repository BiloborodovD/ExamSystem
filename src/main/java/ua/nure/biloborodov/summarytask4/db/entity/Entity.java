package ua.nure.biloborodov.summarytask4.db.entity;

/**
 * Root of all entities which have identifier field.
 */
public abstract class Entity {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
