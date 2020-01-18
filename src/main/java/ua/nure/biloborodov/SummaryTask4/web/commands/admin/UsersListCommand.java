package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.UserRepository;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class UsersListCommand extends ActionCommand {
    
    private static final long serialVersionUID = 1971243248228370570L;

    private static final Logger LOG = Logger.getLogger(UsersListCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return CommandPath.USERS_LIST;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	LOG.debug("Commands starts");
	
	request.setAttribute(Attributes.USERS_LIST, new UserRepository().findAll());
	LOG.trace("Set the request attribute: users ");
	LOG.debug("Commands finished");
	
	return PagePath.PAGE_ADMIN_USERS_LIST;
    }
}
