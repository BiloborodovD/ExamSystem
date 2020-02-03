package ua.nure.biloborodov.summarytask4.db.entity;

/**
 * Implements the UsersTests entity.
 */
public class UsersTests extends Entity {

  private int userId;
  private int testId;
  private String login;
  private String testName;
  private String subjectName;
  private String testTime;
  private int testResult;


  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public int getTestResult() {
    return testResult;
  }

  public void setTestResult(int testResult) {
    this.testResult = testResult;
  }

  public String getTestTime() {
    return testTime;
  }

  public void setTestTime(String testTime) {
    this.testTime = testTime;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getTestId() {
    return testId;
  }

  public void setTestId(int testId) {
    this.testId = testId;
  }
}
