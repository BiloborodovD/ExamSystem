package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.Role;
import ua.nure.biloborodov.SummaryTask4.db.repository.UserRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.User;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class BlockUserCommand extends ActionCommand {
    
    private static final long serialVersionUID = -1311199598055741217L;
  
    private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	LOG.debug("Command starts");

	String login = request.getParameter("login");
	List<User> users = new ArrayList<>();
	UserRepository userRepository = new UserRepository();
	
	User user = userRepository.findByLogin(login);
	if (user.getRole() == Role.STUDENT) {
		user.setRole(Role.BLOCKED);
	    } else if (user.getRole() == Role.BLOCKED) {
		user.setRole(Role.STUDENT);
	    }
	userRepository.update(user);
	
	users = userRepository.findAll();
	request.setAttribute(Attributes.USERS_LIST, users);
	LOG.trace("Set the request attribute: users ");
	LOG.debug("Commands finished");
	return CommandPath.USERS_LIST;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_USERS_LIST;
    }

}
