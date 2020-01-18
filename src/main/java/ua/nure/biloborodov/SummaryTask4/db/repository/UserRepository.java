package ua.nure.biloborodov.SummaryTask4.db.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.db.Fields;
import ua.nure.biloborodov.SummaryTask4.db.Role;
import ua.nure.biloborodov.SummaryTask4.db.entity.User;
import ua.nure.biloborodov.SummaryTask4.exception.DBException;

public class UserRepository extends AbstractRepository<User> {
    
    protected static final Logger LOG = Logger.getLogger(UserRepository.class);

    private static final String FIND_ALL_USERS = "SELECT * from users";
    private static final String FIND_ALL_USERS_BY_ROLE = "SELECT * from users WHERE role_id=?";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login=?";
    private static final String INSERT_USER = "INSERT INTO users VALUES(default, ?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE users SET login=?, password=?, first_name=?, last_name=?, email=?, role_id=? WHERE id=?";

    @Override
    public List<User> findAll() throws DBException {
	init();
	List<User> users = new ArrayList<>();
	try {
	    users = findAll(FIND_ALL_USERS);
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_get_users", ex);
	} finally {
	    cp.close(connection);
	}
	return users;
    }

    public User findByLogin(String login) throws DBException {
	init();
	User user = null;
	try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
	    ps.setString(1, login);
	    try (ResultSet rs = ps.executeQuery()) {
		if (rs.next()) {
		    user = extract(rs);
		}
	    }
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_find_user_by_login", ex);
	} finally {
	    cp.close(connection);
	}
	return user;
    }

    @Override
    public User findById(int id) throws DBException {
	init();
	User user = null;
	try {
	    user = findById(id, FIND_USER_BY_ID);
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_find_user_by_id", ex);
	} finally {
	    cp.close(connection);
	}
	return user;
    }

    public List<User> findUsersByRole(Role role) throws DBException {
	init();
	List<User> users = new ArrayList<>();
	try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_USERS_BY_ROLE)) {
	    ps.setInt(1, role.ordinal());
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    users.add(extract(rs));
		}
	    }
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_find_users_by_role", ex);
	} finally {
	    cp.close(connection);
	}
	return users;
    }

    @Override
    public void deleteById(int id) throws DBException {
	init();
	try {
	    deleteById(id, DELETE_USER_BY_LOGIN);
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_delete_user_by_id", ex);
	} finally {
	    cp.close(connection);
	}
    }

    @Override
    public User create(User user) throws DBException {
	init();
	try {
	    user = create(user, INSERT_USER);
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_create_user", ex);
	} finally {
	    cp.close(connection);
	}
	return user;
    }

    @Override
    public User update(User user) throws DBException {
	init();
	try {
	    user = update(user, UPDATE_USER);
	    connection.commit();
	} catch (SQLException ex) {
	    cp.rollback(connection);
	    throw new DBException("can_not_update_user", ex);
	} finally {
	    cp.close(connection);
	}
	return user;
    }

    @Override
    protected void prepareForInsert(User user, PreparedStatement ps) throws SQLException {
	ps.setString(1, user.getLogin());
	LOG.trace("get login");
	ps.setString(2, user.getPassword());
	LOG.trace("get password");
	ps.setString(3, user.getFirstName());
	ps.setString(4, user.getLastName());
	ps.setString(5, user.getEmail());
	ps.setInt(6, user.getRole().ordinal());
	LOG.trace("get role");
    }

    @Override
    protected void prepareForUpdate(User user, PreparedStatement ps) throws SQLException {
	prepareForInsert(user, ps);
	ps.setInt(7, user.getId());

    }

    @Override
    protected User extract(ResultSet rs) throws SQLException {
	User user = new User();
	user.setId(rs.getInt(Fields.ENTITY_ID));
	user.setLogin(rs.getString(Fields.USER_LOGIN));
	user.setPassword(rs.getString(Fields.USER_PASSWORD));
	user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
	user.setLastName(rs.getString(Fields.USER_LAST_NAME));
	user.setEmail(rs.getString(Fields.USER_EMAIL));
	user.setRole(Role.define(rs.getInt(Fields.USER_ROLE_ID)));
	return user;
    }

}
