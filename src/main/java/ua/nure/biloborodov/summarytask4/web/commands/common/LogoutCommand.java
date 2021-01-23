package ua.nure.biloborodov.summarytask4.web.commands.common;

import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends ActionCommand {

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        return doGet(request, response);
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return PagePath.PAGE_LOGIN;
    }
}
