package ua.nure.biloborodov.summarytask4.db.entity;

/**
 * Implements the Question entity.
 */
public class Question extends Entity {

    private String content;
    private int testId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
