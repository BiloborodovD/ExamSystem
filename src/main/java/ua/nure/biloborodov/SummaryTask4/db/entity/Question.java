package ua.nure.biloborodov.SummaryTask4.db.entity;

/**
 * Implements the Question entity.
 *
 * @author Dmytro Biloborodov
 */
public class Question extends Entity {
      
    private static final long serialVersionUID = 8934663955544484473L;
    
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
