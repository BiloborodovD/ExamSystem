package ua.nure.biloborodov.summarytask4.db.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.nure.biloborodov.summarytask4.db.Fields;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.db.entity.UsersTests;
import ua.nure.biloborodov.summarytask4.exception.DBException;

public class UsersTestsRepository extends AbstractRepository<UsersTests> {

  private static final Logger LOG = Logger.getLogger(UsersTestsRepository.class);

  private static final String FIND_ALL_USERS_TESTS_BY_USER = "SELECT * from users_tests_view WHERE id=?";
  private static final String INSERT_USERS_TESTS = "INSERT INTO users_tests VALUES(default, ?,?,default,?)";

  @Override
  public UsersTests update(UsersTests entity) throws DBException {
    throw new UnsupportedOperationException();
  }

  @Override
  public UsersTests create(UsersTests usersTests) throws DBException {
    init();
    try {
      usersTests = create(usersTests, INSERT_USERS_TESTS);
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_create_users_test", ex);
    } finally {
      cp.close(connection);
    }
    return usersTests;
  }

  @Override
  public void deleteById(int id) throws DBException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UsersTests> findAll() throws DBException {
    throw new UnsupportedOperationException();
  }

  @Override
  public UsersTests findById(int id) throws DBException {
    throw new UnsupportedOperationException();
  }

  public List<UsersTests> findByUser(User user) throws DBException {
    init();
    List<UsersTests> usersTests = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_USERS_TESTS_BY_USER)) {
      LOG.trace("userID" + user.getId());
      ps.setInt(1, user.getId());
      try (ResultSet rs = ps.executeQuery()) {
        LOG.trace("result set was added");
        while (rs.next()) {
          LOG.trace("result set next");
          usersTests.add(extract(rs));
          LOG.trace("users Test was added");
        }
      }
      connection.commit();
    } catch (SQLException ex) {
      cp.rollback(connection);
      throw new DBException("can_not_find_users_tests_for_user", ex);
    } finally {
      cp.close(connection);
    }
    return usersTests;

  }

  @Override
  protected void prepareForInsert(UsersTests userTest, PreparedStatement ps) throws SQLException {
    ps.setInt(1, userTest.getUserId());
    ps.setInt(2, userTest.getTestId());
    ps.setInt(3, userTest.getTestResult());
  }

  @Override
  protected void prepareForUpdate(UsersTests userTest, PreparedStatement ps) throws SQLException {
    throw new UnsupportedOperationException();
  }

  @Override
  protected UsersTests extract(ResultSet rs) throws SQLException {
    UsersTests usersTests = new UsersTests();
    usersTests.setSubjectName(rs.getString(Fields.SUBJECT_NAME));
    LOG.trace("result set subject ");
    usersTests.setTestName(rs.getString(Fields.TEST_NAME));
    LOG.trace("result set test ");
    usersTests.setTestTime(rs.getString(Fields.TEST_TIME));
    LOG.trace("result set time ");
    usersTests.setTestResult(rs.getInt(Fields.RESULT));
    LOG.trace("result set redult ");
    return usersTests;
  }

}
