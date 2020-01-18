package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Answer;
import ua.nure.biloborodov.SummaryTask4.db.entity.Question;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class EditQuestionCommand extends ActionCommand {

    
    private static final long serialVersionUID = -3748696713728451500L;
    private static final Logger LOG = Logger.getLogger(EditQuestionCommand.class);
    
    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return CommandPath.GET_QUESTION_CREATOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	HttpSession session = request.getSession(false);

	int questionId = Integer.parseInt(request.getParameter("question"));
	LOG.trace("Get questionId" + questionId);
	
	List<Question> questions = (List<Question>) session.getAttribute(Attributes.CURRENT_QUESTIONS_LIST);
	Question question = questions.get(questionId - 1);
	List<Answer> answers = new AnswerRepository().findByQuestion(question);
	
	session.setAttribute(Attributes.CURRENT_QUESTION, question);
	session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, answers);
	LOG.trace("Set session attribute current answers" + answers);

	return PagePath.PAGE_ADMIN_QUESTION_CREATOR;
    }

}
