package ua.nure.biloborodov.summarytask4.exception;

/**
 * An exception that provides information on a database access error.
 */
public class DBException extends AppException {

    public DBException() {
	super();
    }

    public DBException(String message, Throwable cause) {
	super(message, cause);
    }
}
