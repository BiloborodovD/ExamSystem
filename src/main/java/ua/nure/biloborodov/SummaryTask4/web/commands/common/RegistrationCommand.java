package ua.nure.biloborodov.SummaryTask4.web.commands.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.Role;
import ua.nure.biloborodov.SummaryTask4.db.entity.User;
import ua.nure.biloborodov.SummaryTask4.db.repository.UserRepository;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.util.validation.Validators;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class RegistrationCommand extends ActionCommand {

  private UserRepository userRepository;

  public RegistrationCommand(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {

    HttpSession session = request.getSession();

    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("passwordConfirm");
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");
    String email = request.getParameter("email");

    User user = userRepository.findByLogin(login);
    if (user != null) {
      session.setAttribute(Attributes.ERROR, "message.user.already.exist");
      return CommandPath.REGISTRATION_PAGE;
    }

    String invalid = Validators
        .validateRegistrationForm(login, password, firstName, lastName, email);
    if (invalid != null) {
      session.setAttribute(Attributes.ERROR, invalid);
      return CommandPath.GET_SETTINGS;
    }

    if (!password.equals(confirmPassword)) {
      throw new AppException("message.password.mismatch");
    }

    User newUser = new User();
    newUser.setLogin(login);
    newUser.setPassword(confirmPassword);
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setEmail(email);
    newUser.setRole(Role.STUDENT);

    userRepository.create(newUser);
    session.setAttribute(Attributes.SUCCESS, "message.success.registration");
    return "login.jsp";
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) {
    return PagePath.PAGE_STUDENT_REGISTRATION;
  }
}
