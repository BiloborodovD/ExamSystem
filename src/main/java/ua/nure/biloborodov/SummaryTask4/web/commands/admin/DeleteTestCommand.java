package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.TestRepository;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class DeleteTestCommand extends ActionCommand{

    private static final long serialVersionUID = 8544038919778876521L;

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	HttpSession session = request.getSession(false);
	int testId = Integer.parseInt(request.getParameter("test_id"));
	new TestRepository().deleteById(testId);
	
	return CommandPath.GET_ADMIN_TESTS + "&subject_id=" + (Integer)session.getAttribute(Attributes.CURRENT_SUBJECT_ID);
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_TESTS_LIST;
    }

}
