package ua.nure.biloborodov.summarytask4.db.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ua.nure.biloborodov.summarytask4.db.Fields;
import ua.nure.biloborodov.summarytask4.db.entity.Question;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class QuestionRepository extends AbstractRepository<Question> {

  private static final String FIND_QUESTIONS_BY_TEST = "SELECT * FROM questions WHERE test_id=?";
  private static final String FIND_ALL_QUESTIONS = "SELECT * FROM questions";
  private static final String FIND_QUESTION_BY_ID = "SELECT * FROM questions WHERE id=?";
  private static final String DELETE_QUESTION_BY_ID = "DELETE FROM questions WHERE id=?";
  private static final String INSERT_QUESTION = "INSERT INTO questions VALUES(default,?,?)";
  private static final String UPDATE_QUESTION = "UPDATE questions SET content=?, test_id=? WHERE id=?";

  @Override
  public List<Question> findAll() throws DBException {
    init();
    List<Question> questions;
    try {
      questions = findAll(FIND_ALL_QUESTIONS);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_get_questions", ex);
    } finally {
      cp.close(connection);
    }
    return questions;
  }

  public List<Question> findByTest(Test test) throws DBException {
    init();
    List<Question> questions = new LinkedList<>();
    try (PreparedStatement ps = connection.prepareStatement(FIND_QUESTIONS_BY_TEST)) {
      ps.setLong(1, test.getId());
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          questions.add(extract(rs));
        }
      }
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_get_questions_by_test", ex);
    } finally {
      cp.close(connection);
    }
    return questions;
  }

  @Override
  public Question findById(int id) throws DBException {
    init();
    Question question;
    try {
      question = findById(id, FIND_QUESTION_BY_ID);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_get_question_by_id", ex);
    } finally {
      cp.close(connection);
    }
    return question;
  }

  @Override
  public void deleteById(int id) throws DBException {
    init();
    try {
      deleteById(id, DELETE_QUESTION_BY_ID);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_delete_question_by_id", ex);
    } finally {
      cp.close(connection);
    }
  }

  @Override
  public Question create(Question question) throws DBException {
    init();
    try {
      question = create(question, INSERT_QUESTION);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_create_question_by_id", ex);
    } finally {
      cp.close(connection);
    }
    return question;
  }

  @Override
  public Question update(Question question) throws DBException {
    init();
    try {
      question = update(question, UPDATE_QUESTION);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_update_question_by_id", ex);
    } finally {
      cp.close(connection);
    }
    return question;
  }

  protected Question extract(ResultSet rs) throws SQLException {
    Question question = new Question();
    question.setId(rs.getInt(Fields.ENTITY_ID));
    question.setContent(rs.getString(Fields.CONTENT));
    question.setTestId(rs.getInt(Fields.TEST_ID));
    return question;
  }

  @Override
  protected void prepareForInsert(Question question, PreparedStatement ps) throws SQLException {
    ps.setString(1, question.getContent());
    ps.setInt(2, question.getTestId());
  }

  @Override
  protected void prepareForUpdate(Question question, PreparedStatement ps) throws SQLException {
    prepareForInsert(question, ps);
    ps.setLong(3, question.getId());
  }
}
