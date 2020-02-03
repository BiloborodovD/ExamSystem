package ua.nure.biloborodov.summarytask4.web.commands.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.constants.Parameters;
import ua.nure.biloborodov.summarytask4.db.Role;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.db.repository.UserRepository;
import ua.nure.biloborodov.summarytask4.db.repository.UsersTestsRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

/**
 * Login command
 */
public class LoginCommand extends ActionCommand {


  private UserRepository userRepository;
  private UsersTestsRepository usersTestsRepository;

  public LoginCommand(UserRepository userRepository, UsersTestsRepository usersTestsRepository) {
    this.userRepository = userRepository;
    this.usersTestsRepository = usersTestsRepository;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession();

    String login = request.getParameter(Parameters.LOGIN);

    String password = request.getParameter(Parameters.PASSWORD);

    if (login == null || password == null || login.isEmpty()
        || password.isEmpty()) {
      session.setAttribute(Attributes.ERROR, "message.login.empty");
      return PagePath.PAGE_LOGIN;
    }

    User user = userRepository.findByLogin(login);

    if (user == null) {
      session.setAttribute(Attributes.ERROR, "message.login.error");
      return PagePath.PAGE_LOGIN;
    }
    if (!password.equals(user.getPassword())) {
      session.setAttribute(Attributes.ERROR, "message.login.error");
      return PagePath.PAGE_LOGIN;
    }

    String commandPath = checkRole(user);

    session.setAttribute(Attributes.CURRENT_USERS_TESTS, usersTestsRepository.findByUser(user));
    session.setAttribute(Attributes.CURRENT_USER, user);
    return commandPath;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) {
    request.setAttribute("errorMessage", "error.invalid.request");
    return PagePath.PAGE_ERROR;
  }

  private String checkRole(User user) throws AppException {
    Role userRole = user.getRole();
    String path = PagePath.PAGE_LOGIN;
    if (userRole == Role.ADMIN) {
      path = CommandPath.PROFILE_ADMIN;
    } else if (userRole == Role.STUDENT) {
      path = CommandPath.PROFILE_STUDENT;
    } else if (userRole == Role.BLOCKED) {
      throw new AppException("message.user.is.blocked");
    }
    return path;
  }
}
