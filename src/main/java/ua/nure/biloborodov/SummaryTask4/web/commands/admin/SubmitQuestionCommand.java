package ua.nure.biloborodov.SummaryTask4.web.commands.admin;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.biloborodov.SummaryTask4.constants.Attributes;
import ua.nure.biloborodov.SummaryTask4.constants.CommandPath;
import ua.nure.biloborodov.SummaryTask4.constants.PagePath;
import ua.nure.biloborodov.SummaryTask4.db.repository.AnswerRepository;
import ua.nure.biloborodov.SummaryTask4.db.repository.QuestionRepository;
import ua.nure.biloborodov.SummaryTask4.db.entity.Answer;
import ua.nure.biloborodov.SummaryTask4.db.entity.Question;
import ua.nure.biloborodov.SummaryTask4.db.entity.Test;
import ua.nure.biloborodov.SummaryTask4.exception.AppException;
import ua.nure.biloborodov.SummaryTask4.web.commands.ActionCommand;

public class SubmitQuestionCommand extends ActionCommand {

    private static final long serialVersionUID = 7103378762833386816L;

    private static final Logger LOG = Logger.getLogger(SubmitQuestionCommand.class);

    @Override
    protected String doPost(HttpServletRequest request, HttpServletResponse response) throws AppException {
	HttpSession session = request.getSession(false);
	Test test = (Test) session.getAttribute(Attributes.CURRENT_TEST);

	List<Answer> answers = new LinkedList<>();
	String[] answersId = request.getParameterValues("answer_id");
	String[] answersContents = request.getParameterValues("answer_content");
	String[] answersCorrect = request.getParameterValues("correct");

	if (answersContents == null || answersCorrect == null) {
	    LOG.trace("question caт not be created with empty answers");
	    return CommandPath.GET_QUESTION_CREATOR;
	}

	updateAnswersList(answersId, answersContents, answersCorrect, answers);

	Question question = (Question) session.getAttribute(Attributes.CURRENT_QUESTION);
	question.setContent(request.getParameter("question_content"));
	question.setTestId(test.getId());
	LOG.trace("question content" + question.getContent());
	QuestionRepository questionRepository = new QuestionRepository();
	if (question.getId() == 0) {
	    question = questionRepository.create(question);
	    LOG.trace("new question was add");
	} else {
	    question = questionRepository.update(question);
	    LOG.trace(" question was updated");
	}

	new AnswerRepository().addList(answers, question);
	session.setAttribute(Attributes.CURRENT_QUESTIONS_LIST, questionRepository.findByTest(test));
	session.setAttribute(Attributes.CURRENT_ANSWERS_LIST, null);
	session.setAttribute(Attributes.CURRENT_QUESTION, null);
	return CommandPath.GET_TEST_INFO_EDITOR;
    }

    @Override
    protected String doGet(HttpServletRequest request, HttpServletResponse response) throws AppException {
	return PagePath.PAGE_ADMIN_TEST_INFO_EDITOR;
    }

    private void updateAnswersList(String[] answersId, String[] answersContents, String[] answersCorrect,
	    List<Answer> answers) {

	for (int i = 0; i < answersContents.length; i++) {
	    Answer answer = new Answer();
	    answer.setId(Integer.parseInt(answersId[i]));
	    answer.setContent(answersContents[i]);
	    for (String string : answersCorrect) {
		if (Integer.parseInt(string) == i) {
		    answer.setCorrect(true);
		}
	    }
	    answers.add(answer);
	}
    }

}
