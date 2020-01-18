package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.DifficultyLevel;
import ua.nure.biloborodov.SummaryTask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.SummaryTask4.db.repository.TestRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Question;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class EditTestInfoCommand extends ActionCommand {

    private static final long serialVersionUID = 5372531838631371785L;
    
    private static final Logger LOG = Logger.getLogger(EditTestInfoCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return CommandPath.GET_TEST_INFO_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	HttpSession session = request.getSession(false);
	
	Test test = new TestRepository().findById(Integer.parseInt(request.getParameter("test_id")));
	List<Question> questions = new QuestionRepository().findByTest(test);
	session.setAttribute(Attributes.CURRENT_TEST, test);
	LOG.trace("Set session attribute current test" + test);
	session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, questions);
	LOG.trace("Set session attribute current question list" + questions);
	session.setAttribute(Attributes.DIFFICULTIES, DifficultyLevel.values());
	return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
    }

}
