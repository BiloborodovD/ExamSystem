package ua.nure.biloborodov.summarytask4.web.commands.admin;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.Role;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.service.UserService;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BlockUserCommand extends ActionCommand {

	private final UserService service;

	public BlockUserCommand(UserService service) {
		this.service = service;
	}

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    String login = request.getParameter("login");
    List<User> users;

    User user = service.findByLogin(login);
    if (user.getRole() == Role.STUDENT) {
      user.setRole(Role.BLOCKED);
    } else if (user.getRole() == Role.BLOCKED) {
      user.setRole(Role.STUDENT);
    }
    service.update(user);

    users = service.findAll();
    request.setAttribute(Attributes.USERS_LIST, users);
    return CommandPath.USERS_LIST;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
    return PagePath.PAGE_ADMIN_USERS_LIST;
  }

}
