package ua.nure.biloborodov.SummaryTask4.db.entity;


/**
 * Implements the Answer entity.
 *
 * @author Dmytro Biloborodov
 */
public class Answer extends Entity {

    private static final long serialVersionUID = -3109197755865859352L;

    private String content;
    private boolean correct;
    private int questionsId;
    
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public boolean isCorrect() {
        return correct;
    }
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    public int getQuestionsId() {
        return questionsId;
    }
    public void setQuestionsId(int questionsId) {
        this.questionsId = questionsId;
    }
}
