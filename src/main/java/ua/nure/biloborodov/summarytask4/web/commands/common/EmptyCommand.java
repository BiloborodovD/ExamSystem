package ua.nure.biloborodov.summarytask4.web.commands.common;

import ua.nure.biloborodov.summarytask4.constants.Attributes;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand extends ActionCommand {

    public static final String ERROR_MESSAGE = "message.nosuchcommand";

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        return doGet(request, response);
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(Attributes.ERROR, ERROR_MESSAGE);
        return PagePath.PAGE_ERROR;
    }

}
