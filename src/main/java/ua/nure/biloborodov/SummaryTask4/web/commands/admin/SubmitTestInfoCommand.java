package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.DifficultyLevel;
import ua.nure.biloborodov.SummaryTask4.db.repository.TestRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class SubmitTestInfoCommand extends ActionCommand {

    private static final long serialVersionUID = 5372531838631371785L;
    
    private static final Logger LOG = Logger.getLogger(SubmitTestInfoCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
LOG.debug("Command starts");
	
	HttpSession session = request.getSession(false);
	TestRepository testRepository = new TestRepository();
	
	Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);
	if (test == null) {
	    test = new Test();
	}
	LOG.trace("Session attribute: current test name --> " + test.getName());
	
	test.setName(request.getParameter("test_name"));
	test.setSubjectId((Integer)session.getAttribute(Attributes.CURRENT_SUBJECT_ID));
	test.setDifficulty(DifficultyLevel.valueOf(request.getParameter("diff_level").toUpperCase()));
	test.setTime(Integer.parseInt(request.getParameter("test_time"))*60);
	LOG.trace("Update test with new parametes");
	
	if (test.getId() == 0) {
	    test = testRepository.create(test);
	    LOG.trace("New test was added");
	} else {
	   test = testRepository.update(test);
	   LOG.trace("Test was updated");
	}
	LOG.debug("Command finished");
	
	return CommandPath.GET_ADMIN_TESTS + "&subject_id=" + (Integer)session.getAttribute(Attributes.CURRENT_SUBJECT_ID);
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_TESTS_LIST;
    }

}
