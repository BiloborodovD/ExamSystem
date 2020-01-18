package ua.nure.biloborodov.SummaryTask4.web.commands.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.TestRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class GetTestsListCommand extends ActionCommand {

    private static final long serialVersionUID = 6842746569581392956L;
    
    private static final Logger LOG = Logger.getLogger(GetTestsListCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return doGet(request, response);
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {

	HttpSession session = request.getSession(false);
		
	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
	LOG.trace("subject id" + subjectId);
	String order = request.getParameter("order");
	LOG.trace("order " + order);
	List<Test> tests = new TestRepository().findBySubjectId(subjectId, order);
	session.setAttribute(Attributes.CURRENT_SUBJECT_ID, subjectId);
	request.setAttribute(Attributes.CURRENT_TESTS_LIST, tests);
	return PagePath.PAGE_STUDENT_TESTS_LIST;
    }
}
