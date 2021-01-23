package ua.nure.biloborodov.summarytask4.web;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;
import ua.nure.biloborodov.summarytask4.web.commands.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller.
 */

public class Controller extends HttpServlet {

    private static final String COMMAND = "command";
    private static final String EMPTY = "empty";
    private static final String GET = "GET";
    private static final String POST = "POST";

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
        return commandName.isEmpty();
    }
}
