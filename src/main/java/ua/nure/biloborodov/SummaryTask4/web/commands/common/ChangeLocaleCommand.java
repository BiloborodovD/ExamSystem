package ua.nure.biloborodov.SummaryTask4.web.commands.common;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class ChangeLocaleCommand extends ActionCommand {

	public static final String LANGUAGE = "lang";

	@Override
  protected String doPost(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    return doGet(request, response);
  }

  @Override
  protected String doGet(HttpServletRequest request, HttpServletResponse response)
      throws AppException {
    HttpSession session = request.getSession(false);

    String language = request.getParameter(LANGUAGE);
    session.setAttribute(Attributes.LANGUAGE, new Locale(language));

    return PagePath.PAGE_LOGIN;
  }
}
