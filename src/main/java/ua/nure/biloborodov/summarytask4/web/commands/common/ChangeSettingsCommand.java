package ua.nure.biloborodov.summarytask4.web.commands.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.db.entity.User;
import ua.nure.biloborodov.summarytask4.db.repository.UserRepository;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.util.validation.Validators;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

/**
 * Update profile information of user.
 */
public class ChangeSettingsCommand extends ActionCommand {

  private UserRepository userRepository;

  public ChangeSettingsCommand(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession();
    User oldUser = (User) session.getAttribute(Attributes.CURRENT_USER);

    String login = request.getParameter("login");
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String passwordNew = request.getParameter("passwordConfirm");

    if (!password.equals(oldUser.getPassword())) {
      session.setAttribute(Attributes.ERROR, "message.password.mismatch");
      return CommandPath.GET_SETTINGS;
    }

    String invalid = Validators
        .validateRegistrationForm(login, passwordNew, firstName, lastName, email);
    if (invalid != null) {
      session.setAttribute(Attributes.ERROR, invalid);
      return CommandPath.GET_SETTINGS;
    }

    oldUser.setLogin(login);
    oldUser.setFirstName(request.getParameter("first_name"));
    oldUser.setLastName(request.getParameter("last_name"));
    oldUser.setEmail(request.getParameter("email"));
    oldUser.setPassword(request.getParameter("password"));

    userRepository.update(oldUser);

    session.setAttribute(Attributes.CURRENT_USER, userRepository.findById(oldUser.getId()));
    request.setAttribute(Attributes.SUCCESS, "message.success.settings.change");

    return CommandPath.GET_SETTINGS;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return PagePath.PAGE_SETTINGS;
  }

}
