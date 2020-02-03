package ua.nure.biloborodov.summarytask4.web.commands.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

/**
 * Return the registration page.
 */
public class RegistrationPageCommand extends ActionCommand {

  @Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return CommandPath.REGISTRATION_PAGE;
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response) {
    return PagePath.PAGE_STUDENT_REGISTRATION;
  }

}
