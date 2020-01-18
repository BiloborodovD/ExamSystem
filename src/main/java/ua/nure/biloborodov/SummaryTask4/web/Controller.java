package ua.nure.biloborodov.SummaryTask4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;
import ua.nure.biloborodov.SummaryTask4.web.commands.CommandContainer;

/**
 * Main servlet controller.
 */

public class Controller extends HttpServlet {

	private static final String COMMAND = "command";
	public static final String EMPTY = "empty";
	public static final String GET = "GET";
	public static final String POST = "POST";

	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    process(request, response);
  }

  private void process(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    String commandName = request.getParameter(COMMAND);

    if (isEmpty(commandName)) {
      commandName = EMPTY;
    }

    ActionCommand command = CommandContainer.get(commandName);

    String forward = PagePath.PAGE_ERROR;
    try {
      forward = command.execute(request, response);
    } catch (AppException ex) {
      request.setAttribute(Attributes.ERROR, ex.getMessage());
      request.getRequestDispatcher(forward).forward(request, response);
      return;
    }
    if (forward != null) {
      if (request.getMethod().equals(GET)) {
        request.getRequestDispatcher(forward).forward(request, response);
      } else if (request.getMethod().equals(POST)) {
        response.sendRedirect(forward);
      }
    }
  }

	private boolean isEmpty(String commandName) {
		return commandName.isEmpty() || commandName == null;
	}
}
