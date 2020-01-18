package ua.nure.biloborodov.SummaryTask4.web.commands;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biloborodov.SummaryTask4.exception.AppException;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author Dmytro Biloborodov
 * 
 */
public abstract class ActionCommand implements Serializable {
	private static final long serialVersionUID = 8879403039606311780L;
	
	/**
	 * Execution method for command. Depends of request method return page path for forwarding
	 * or command path for redirect.
	 * 	
	 * @return Address to go once the command is executed.
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException, AppException {
	    
	    String forward = null;
		if (request.getMethod().equals("GET")) {
			forward = doGet(request, response);
		} else if (request.getMethod().equals("POST")){
			forward = doPost(request, response);
		}
		return forward;
	    
	}
	
	/**
	 * Execute POST request return string with command path
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws AppException
	 */
	protected abstract String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException;


	/** 
	 *  Execute GET request return string with page path
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws AppException
	 */
	protected abstract String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException;


	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}