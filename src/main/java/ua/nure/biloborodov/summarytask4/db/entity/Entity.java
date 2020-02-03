package ua.nure.biloborodov.summarytask4.db.entity;

import java.io.Serializable;

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
