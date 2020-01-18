package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Question;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class DeleteQuestionCommand extends ActionCommand{

    private static final long serialVersionUID = 4282912297124087664L;
    
    private static final Logger LOG = Logger.getLogger(DeleteQuestionCommand.class);

    @Override 
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	HttpSession session =request.getSession(false);
	QuestionRepository questionRepository = new QuestionRepository();
	Question question = (Question) session.getAttribute(Attributes.CURRENT_QUESTION);
	LOG.trace("question" + question.getId());
	questionRepository.deleteById(question.getId());
	Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);
	session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, questionRepository.findByTest(test));
	session.setAttribute(Attributes.CURRENT_QUESTION, null);
	session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, null);
	return CommandPath.GET_TEST_INFO_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
    }

}
