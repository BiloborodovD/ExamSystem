package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class GetTestInfoEditor extends ActionCommand {

    private static final long serialVersionUID = -6477850954305036014L;

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return CommandPath.GET_TEST_INFO_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
    }

}
