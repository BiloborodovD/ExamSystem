package ua.nure.biloborodov.summarytask4.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.summarytask4.exception.DBException;

public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    
    private DataSource ds;
    private static ConnectionPool instance;

    public static synchronized ConnectionPool getInstance() throws DBException {
	if (instance == null) {
	    instance = new ConnectionPool();
	}
	return instance;
    }

    private ConnectionPool() throws DBException {
	try {
	    Context ctx = new InitialContext();
	    ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/exam_system");
	} catch (NamingException ex) {
	    throw new DBException("can_not_obtain_datasource", ex);
	}
    }

    public Connection getConnection() throws DBException {
	Connection con = null;
	try {
	    con = ds.getConnection();
	} catch (SQLException ex) {
	    throw new DBException("can_not_obtain_connection", ex);
	}
	return con;
    }
   
    /**
     * Closes a connection.
     * 
     * @param con Connection to be closed.
     */
    public void close(Connection con) {
	if (con != null) {
	    try {
		con.close();
	    } catch (SQLException ex) {
		LOG.error("Cannot close a connection", ex);
	    }
	}
    }

    /**
     * Rollbacks a connection.
     * 
     * @param con Connection to be rollbacked.
     */
    public void rollback(Connection con) {
	if (con != null) {
	    try {
		con.rollback();
	    } catch (SQLException ex) {
		LOG.error("Cannot rollback transaction", ex);
	    }
	}
    }

}
