package ua.nure.biloborodov.summarytask4.db.entity;

import ua.nure.biloborodov.summarytask4.db.DifficultyLevel;

/**
 * Implements the Test entity.
 */
public class Test extends Entity {

  private String name;
  private int difficulty;
  private int time;
  private int subjectId;
  private int questionsCount;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DifficultyLevel getDifficulty() {
    return DifficultyLevel.define(difficulty);
  }

  public void setDifficulty(DifficultyLevel diff) {
    this.difficulty = diff.ordinal();
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public int getQuestionsCount() {
    return questionsCount;
  }

  public void setQuestionsCount(int questionsCount) {
    this.questionsCount = questionsCount;
  }
}
