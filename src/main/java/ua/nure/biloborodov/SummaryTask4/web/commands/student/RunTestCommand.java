package ua.nure.biloborodov.SummaryTask4.web.commands.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class RunTestCommand extends ActionCommand {

    private static final long serialVersionUID = -4852037283892619814L;

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	HttpSession session = request.getSession(false);
	Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);
	
	session.setAttribute(Attributes.FINISH_TIME, System.currentTimeMillis() + test.getTime()*1000);
	session.setAttribute(Attributes.TIME_TEST_REMIND, test.getTime()*1000);
	
	return CommandPath.RUN_TEST;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	
	HttpSession session = request.getSession(false);
	long finishTime = (long) session.getAttribute(Attributes.FINISH_TIME);
	long time = System.currentTimeMillis();
	session.setAttribute(Attributes.TIME_TEST_REMIND, finishTime - time);

	return PagePath.PAGE_STUDENT_TEST_RUN;
    }
}
