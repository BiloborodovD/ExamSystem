package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.TestRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

/**
 * Command returns list of tests for the subject.
 *
 * @author Dmytro Biloborodov
 */
public class GetTestsCommand extends ActionCommand {
    
    private static final long serialVersionUID = 799527448287741792L;

    private static final Logger LOG = Logger.getLogger(GetTestsCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return CommandPath.GET_ADMIN_TESTS;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	
	LOG.debug("Command starts");
	
	HttpSession session = request.getSession(false);
	
	int subjectId = Integer.parseInt(request.getParameter("subject_id"));
	LOG.trace("Request parameter: subject_id --> " + subjectId);
	List<Test> tests = new TestRepository().findBySubjectId(subjectId, "name");
	
	if (tests == null ) {
	    session.setAttribute(Attributes.ERROR,
		    "message.testserror");
	    LOG.error("Found in DB: tests --> " + null);
		return PagePath.PAGE_ADMIN_SUBJECT_EDITOR;
	}
	LOG.trace("Found in DB: tests --> " + tests.toString());
	
	session.setAttribute(Attributes.TESTS_LIST, tests);
	session.setAttribute(Attributes.CURRENT_SUBJECT_ID, subjectId);
	LOG.trace("Set the request attribute: subjects");
	LOG.debug("Commands finished");
	return PagePath.PAGE_ADMIN_TESTS_LIST;
    }
}
