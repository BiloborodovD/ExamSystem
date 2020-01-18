package ua.nure.biloborodov.SummaryTask4.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.db.ConnectionPool;
import ua.nure.biloborodov.SummaryTask4.db.entity.Entity;
import ua.nure.biloborodov.SummaryTask4.exception.DBException;

public abstract class AbstractRepository<T extends Entity> {

    protected Connection connection;
    protected ConnectionPool cp;

    protected void init() throws DBException {
	try {
	    cp = ConnectionPool.getInstance();
	    connection = cp.getConnection();
	} catch (DBException ex) {
	    throw new DBException("can_not_obtain_connection", ex);
	}

    }

    protected T create(T entity, String sql) throws SQLException {
	try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    prepareForInsert(entity, ps);
	    ps.executeUpdate();
	    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		if (generatedKeys.next()) {
		    entity.setId(generatedKeys.getInt(1));
		}
	    }
	}
	return entity;
    }

    protected List<T> findAll(String sql) throws SQLException {
	List<T> result = new ArrayList<>();
	try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    try (ResultSet resultSet = ps.executeQuery()) {
		while (resultSet.next()) {
		    result.add(extract(resultSet));
		}
	    }
	}
	return result;

    }

    protected T findById(int id, String sql) throws SQLException {
	T result = null;
	try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    ps.setInt(1, id);
	    try (ResultSet resultSet = ps.executeQuery()) {
		while (resultSet.next()) {
		    result = extract(resultSet);
		}
	    }
	}
	return result;
    }

    protected T update(T entity, String sql) throws SQLException {
	try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    prepareForUpdate(entity, ps);
	    ps.executeUpdate();
	}
    return entity;
    }

    protected void deleteById(int id, String sql) throws SQLException {
	try (PreparedStatement ps = connection.prepareStatement(sql)) {
	    ps.setInt(1, id);
	    ps.executeUpdate();
	}
    }

    protected abstract void prepareForInsert(T entity, PreparedStatement ps) throws SQLException;

    protected abstract void prepareForUpdate(T entity, PreparedStatement ps) throws SQLException;

    protected abstract T extract(ResultSet rs) throws SQLException;

    public abstract T update(T entity) throws DBException;

    public abstract T create(T entity) throws DBException;

    public abstract void deleteById(int id) throws DBException;

    public abstract List<T> findAll() throws DBException;

    public abstract T findById(int id) throws DBException;

}
