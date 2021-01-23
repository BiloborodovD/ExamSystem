package ua.nure.biloborodov.summarytask4.db.repository;

import ua.nure.biloborodov.summarytask4.db.DifficultyLevel;
import ua.nure.biloborodov.summarytask4.db.Fields;
import ua.nure.biloborodov.summarytask4.db.entity.Test;
import ua.nure.biloborodov.summarytask4.exception.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestRepository extends AbstractRepository<Test> {

    private static final String FIND_TESTS_BY_SUBJECT_ID = "SELECT * FROM tests_view WHERE subject_id=?";
    private static final String FIND_TESTS_BY_SUBJ_ORDER_NAME = "SELECT * FROM tests_view WHERE subject_id=? ORDER BY name";
    private static final String FIND_TESTS_BY_SUBJ_ORDER_NAME_DESC = "SELECT * FROM tests_view WHERE subject_id=? ORDER BY name DESC";
    private static final String FIND_TESTS_BY_SUBJ_ORDER_DIFF = "SELECT * FROM tests_view WHERE subject_id=? ORDER BY difficulty_id";
    private static final String FIND_TESTS_BY_SUBJ_ORDER_DIFF_DESC = "SELECT * FROM tests_view WHERE subject_id=? ORDER BY difficulty_id DESC";
    private static final String FIND_TESTS_BY_SUBJ_ORDER_QUES = "SELECT * FROM tests_view WHERE subject_id=? ORDER BY questions_count";
    private static final String FIND_TESTS_BY_SUBJ_ORDER_QUES_DESC = "SELECT * FROM tests_view WHERE subject_id=? ORDER BY questions_count DESC";
    private static final String FIND_ALL_TESTS = "SELECT * FROM tests_view";
    private static final String FIND_TEST_BY_ID = "SELECT * FROM tests_view WHERE id=?";
    private static final String DELETE_TEST_BY_ID = "DELETE FROM tests WHERE id=?";
    private static final String INSERT_TEST = "INSERT INTO tests VALUES (default, ?,?,?,?)";
    private static final String UPDATE_TEST = "UPDATE tests SET name=?, subject_id=?, "
            + "difficulty_id=?, time=? WHERE id=?";

    @Override
    public List<Test> findAll() throws DBException {
        init();
        List<Test> tests = new ArrayList<>();
        try {
            tests = findAll(FIND_ALL_TESTS);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_get_tests", ex);
        } finally {
            cp.close(connection);
        }
        return tests;
    }

    public List<Test> findBySubjectId(int subjectId, String order) throws DBException {
        init();
        List<Test> tests = new ArrayList<>();
        String statemen = null;
        switch (order) {
            case "name":
                statemen = FIND_TESTS_BY_SUBJ_ORDER_NAME;
                break;
            case "nameDesc":
                statemen = FIND_TESTS_BY_SUBJ_ORDER_NAME_DESC;
                break;
            case "difficulty":
                statemen = FIND_TESTS_BY_SUBJ_ORDER_DIFF;
                break;
            case "difficultyDesc":
                statemen = FIND_TESTS_BY_SUBJ_ORDER_DIFF_DESC;
                break;
            case "questions":
                statemen = FIND_TESTS_BY_SUBJ_ORDER_QUES;
                break;
            case "questionsDesc":
                statemen = FIND_TESTS_BY_SUBJ_ORDER_QUES_DESC;
                break;
            default:
                statemen = FIND_TESTS_BY_SUBJECT_ID;
                break;
        }
        try (PreparedStatement ps = connection.prepareStatement(statemen)) {
            ps.setLong(1, subjectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tests.add(extract(rs));
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_find_tests_by_subject", ex);
        } finally {
            cp.close(connection);
        }
        return tests;
    }

    @Override
    public Test findById(int id) throws DBException {
        init();
        Test test = null;
        try {
            test = findById(id, FIND_TEST_BY_ID);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_find_test_by_id", ex);
        } finally {
            cp.close(connection);
        }
        return test;
    }

    @Override
    public void deleteById(int id) throws DBException {
        init();
        try {
            deleteById(id, DELETE_TEST_BY_ID);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_delete_test_by_id", ex);
        } finally {
            cp.close(connection);
        }
    }

    @Override
    public Test create(Test test) throws DBException {
        init();
        try {
            test = create(test, INSERT_TEST);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_create_test_by_id", ex);
        } finally {
            cp.close(connection);
        }
        return test;
    }

    @Override
    public Test update(Test test) throws DBException {
        init();
        try {
            test = update(test, UPDATE_TEST);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_update_test_by_id", ex);
        } finally {
            cp.close(connection);
        }
        return test;
    }

    @Override
    protected void prepareForInsert(Test test, PreparedStatement ps) throws SQLException {
        ps.setString(1, test.getName());
        ps.setInt(2, test.getSubjectId());
        ps.setInt(3, test.getDifficulty().ordinal());
        ps.setInt(4, test.getTime());
    }

    @Override
    protected void prepareForUpdate(Test test, PreparedStatement ps) throws SQLException {
        prepareForInsert(test, ps);
        ps.setLong(5, test.getId());
    }

    @Override
    protected Test extract(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt(Fields.ENTITY_ID));
        test.setName(rs.getString(Fields.NAME));
        test.setSubjectId(rs.getInt(Fields.SUBJECT_ID));
        test.setDifficulty(DifficultyLevel.define(rs.getInt(Fields.DIFFICULTY_ID)));
        test.setTime(rs.getInt(Fields.TIME));
        test.setQuestionsCount(rs.getInt(Fields.QUESTIONS_COUNT));
        return test;
    }
}
