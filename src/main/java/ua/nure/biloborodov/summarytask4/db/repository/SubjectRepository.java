package ua.nure.biloborodov.summarytask4.db.repository;

import org.apache.log4j.Logger;
import ua.nure.biloborodov.summarytask4.db.Fields;
import ua.nure.biloborodov.summarytask4.db.Language;
import ua.nure.biloborodov.summarytask4.db.entity.Subject;
import ua.nure.biloborodov.summarytask4.exception.DBException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository extends AbstractRepository<Subject> {

    protected static final Logger LOG = Logger.getLogger(SubjectRepository.class);

    private static final String FIND_ALL_SUBJECTS = "SELECT * from subjects";
    private static final String FIND_SUBJECTS_BY_LANG = "SELECT * from subjects WHERE lang_id=?";
    private static final String FIND_SUBJECT_BY_ID = "SELECT * from subjects WHERE id=?";
    private static final String DELETE_SUBJECT_BY_ID = "DELETE from subjects WHERE id=?";
    private static final String INSERT_SUBJECT = "INSERT INTO subjects VALUES(default, ?,?)";
    private static final String UPDATE_SUBJECT = "UPDATE subjects SET name=?, lang_id=? WHERE id=?";

    @Override
    public List<Subject> findAll() throws DBException {
        init();
        List<Subject> subjects;
        try {
            subjects = findAll(FIND_ALL_SUBJECTS);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_get_subjects_list", ex);
        } finally {
            cp.close(connection);
        }
        return subjects;

    }

    public List<Subject> findSubjectsByLanguage(Language language) throws DBException {
        init();
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_SUBJECTS_BY_LANG)) {
            ps.setInt(1, language.ordinal());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    subjects.add(extract(rs));
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_find_subjects_by_lang", ex);
        } finally {
            cp.close(connection);
        }
        return subjects;
    }

    @Override
    public Subject findById(int id) throws DBException {
        init();
        Subject subject = null;
        try {
            subject = findById(id, FIND_SUBJECT_BY_ID);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_find_subject_by_id", ex);
        } finally {
            cp.close(connection);
        }
        return subject;
    }

    @Override
    public void deleteById(int id) throws DBException {
        init();
        try {
            LOG.trace("subject id" + id);
            deleteById(id, DELETE_SUBJECT_BY_ID);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_delete_subject_by_id", ex);
        } finally {
            cp.close(connection);
        }
    }

    @Override
    public Subject create(Subject subject) throws DBException {
        init();
        try {
            subject = create(subject, INSERT_SUBJECT);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_create_new_subject", ex);
        } finally {
            cp.close(connection);
        }
        return subject;
    }

    @Override
    public Subject update(Subject subject) throws DBException {
        init();
        try {
            subject = update(subject, UPDATE_SUBJECT);
            connection.commit();
        } catch (SQLException ex) {
            cp.rollback(connection);
            throw new DBException("can_not_update_subject_by_id", ex);
        } finally {
            cp.close(connection);
        }
        return subject;
    }

    protected Subject extract(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getInt(Fields.ENTITY_ID));
        subject.setName(rs.getString(Fields.NAME));
        subject.setLangId(Language.define(rs.getInt(Fields.LANG_ID)));
        return subject;
    }

    @Override
    protected void prepareForInsert(Subject subject, PreparedStatement ps) throws SQLException {
        ps.setString(1, subject.getName());
        ps.setInt(2, subject.getLangId().ordinal());
    }

    @Override
    protected void prepareForUpdate(Subject subject, PreparedStatement ps) throws SQLException {
        prepareForInsert(subject, ps);
        ps.setLong(3, subject.getId());
    }
}
