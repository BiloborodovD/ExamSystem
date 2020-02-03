package ua.nure.biloborodov.summarytask4.db.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.nure.biloborodov.summarytask4.db.Fields;
import ua.nure.biloborodov.summarytask4.db.entity.Answer;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class AnswerRepository extends AbstractRepository<Answer> {

  private static final String FIND_ANSWERS_BY_QUESTION = "SELECT * FROM answers WHERE questions_id=?";
  private static final String FIND_ANSWERS_BY_ID = "SELECT * FROM answers WHERE id=?";
  private static final String INSERT_ANSWER = "INSERT INTO answers VALUES(default, ?,?,?)";
  private static final String UPDATE_ANSWER = "UPDATE answers SET content=?, correct=? WHERE id=?";
  private static final String DELETE_ANSWER_BY_ID = "DELETE FROM answers WHERE id=?";

  @Override
  public List<Answer> findAll() {
    throw new UnsupportedOperationException();
  }

  public void addList(List<Answer> list, Question question) throws DBException {
    init();
    try {
      for (Answer answer : list) {
        answer.setQuestionsId(question.getId());
        if (answer.getId() == 0) {
          create(answer, INSERT_ANSWER);
        } else {
          update(answer, UPDATE_ANSWER);
        }
      }
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_create_answers", ex);
    } finally {
      cp.close(connection);
    }
  }

  public List<Answer> findByQuestion(Question question) throws DBException {
    init();
    List<Answer> answers = new LinkedList<>();
    try (PreparedStatement ps = connection.prepareStatement(FIND_ANSWERS_BY_QUESTION)) {
      ps.setInt(1, question.getId());
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          answers.add(extract(rs));
        }
      }
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_find_answers_by_question", ex);
    } finally {
      cp.close(connection);
    }
    return answers;
  }

  @Override
  public Answer findById(int id) throws DBException {
    init();
    Answer answer;
    try {
      answer = findById(id, FIND_ANSWERS_BY_ID);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_find_answer_by_id", ex);
    } finally {
      cp.close(connection);
    }
    return answer;
  }

  @Override
  public void deleteById(int id) throws DBException {
    init();
    try {
      deleteById(id, DELETE_ANSWER_BY_ID);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_delete_answer_by_id", ex);
    } finally {
      cp.close(connection);
    }
  }

  @Override
  public Answer create(Answer answer) throws DBException {
    init();
    try {
      answer = create(answer, INSERT_ANSWER);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_create_answer", ex);
    } finally {
      cp.close(connection);
    }
    return answer;
  }

  @Override
  public Answer update(Answer answer) throws DBException {
    init();
    try {
      answer = update(answer, UPDATE_ANSWER);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_update_answer_by_id", ex);
    } finally {
      cp.close(connection);
    }
    return answer;
  }

  @Override
  protected void prepareForInsert(Answer answer, PreparedStatement ps) throws SQLException {
    ps.setString(1, answer.getContent());
    ps.setBoolean(2, answer.isCorrect());
    ps.setLong(3, answer.getQuestionsId());
  }

  @Override
  protected void prepareForUpdate(Answer answer, PreparedStatement ps) throws SQLException {
    ps.setString(1, answer.getContent());
    ps.setBoolean(2, answer.isCorrect());
    ps.setInt(3, answer.getId());
  }

  @Override
  protected Answer extract(ResultSet rs) throws SQLException {
    Answer answer = new Answer();
    answer.setId(rs.getInt(Fields.ENTITY_ID));
    answer.setContent(rs.getString(Fields.CONTENT));
    answer.setCorrect(rs.getBoolean(Fields.CORRECT));
    answer.setQuestionsId(rs.getInt(Fields.QUESTION_ID));
    return answer;
  }

}
