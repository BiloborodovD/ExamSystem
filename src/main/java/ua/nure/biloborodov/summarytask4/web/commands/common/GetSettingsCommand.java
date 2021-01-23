package ua.nure.biloborodov.summarytask4.web.commands.common;

import ua.nure.biloborodov.summarytask4.constants.CommandPath;
import ua.nure.biloborodov.summarytask4.constants.PagePath;
import ua.nure.biloborodov.summarytask4.exception.AppException;
import ua.nure.biloborodov.summarytask4.web.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSettingsCommand extends ActionCommand {

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        return CommandPath.GET_SETTINGS;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response)
            throws AppException {
        return PagePath.PAGE_SETTINGS;
    }
}
